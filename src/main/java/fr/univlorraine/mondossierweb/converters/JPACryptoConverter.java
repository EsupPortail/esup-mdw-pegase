/**
 *
 *  ESUP-Portail ESUP-MONDOSSIERWEB-PEGASE - Copyright (c) 2021 ESUP-Portail consortium
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package fr.univlorraine.mondossierweb.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

@Converter
@Scope("singleton")
@Component
@Slf4j
public class JPACryptoConverter implements AttributeConverter<String, String> {

	private static final String ALGORITHM = "AES/GCM/NoPadding";
	private static final int TAG_LENGTH_BIT = 128;
	private static final int IV_LENGTH_BYTE = 12;
	private static final int ITERATIONS = 310_000; // OWASP 2024 recommande 210k-600k pour PBKDF2-SHA256
	private static final int KEY_LENGTH = 256;
	private static final String SECRET_KEY_FACTORY = "PBKDF2WithHmacSHA256";

	@Value("${app.secretkey}")
	private String secretKey;

	// volatile garantit la visibilité entre threads (pattern double-checked locking)
	private volatile SecretKey derivedKey;

	private void initialize() {
		if (derivedKey == null) {
			synchronized (this) {
				if (derivedKey == null) {
					try {
						// Sel dérivé de la secretKey via SHA-256
						MessageDigest digest = MessageDigest.getInstance("SHA-256");
						byte[] fullHash = digest.digest(secretKey.getBytes(StandardCharsets.UTF_8));
						// On prend les 16 premiers bytes du hash comme sel
						byte[] salt = Arrays.copyOf(fullHash, 16);

						KeySpec spec = new PBEKeySpec(
								secretKey.toCharArray(),
								salt,
								ITERATIONS,
								KEY_LENGTH
						);
						SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY);
						derivedKey = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
						log.info("SecureJPACryptoConverter initialized");
					} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
						throw new RuntimeException("Failed to initialize crypto converter", e);
					}
				}
			}
		}
	}

	@Override
	public String convertToDatabaseColumn(String sensitive) {
		if (!StringUtils.hasText(sensitive)) {
			return null;
		}
		initialize();
		try {
			// IV aléatoire par chiffrement : indispensable avec GCM
			byte[] iv = new byte[IV_LENGTH_BYTE];
			new SecureRandom().nextBytes(iv);

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, derivedKey, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
			byte[] ciphertext = cipher.doFinal(sensitive.getBytes(StandardCharsets.UTF_8));

			// Format stocké : [IV (12 bytes)][ciphertext + GCM tag (16 bytes)]
			byte[] output = new byte[IV_LENGTH_BYTE + ciphertext.length];
			System.arraycopy(iv, 0, output, 0, IV_LENGTH_BYTE);
			System.arraycopy(ciphertext, 0, output, IV_LENGTH_BYTE, ciphertext.length);

			return Base64.getEncoder().encodeToString(output);
		} catch (Exception e) {
			// Ne pas logger 'sensitive' évidemment
			throw new RuntimeException("Encryption failed", e);
		}
	}

	@Override
	public String convertToEntityAttribute(String encrypted) {
		if (!StringUtils.hasText(encrypted)) {
			return null;
		}
		initialize();
		try {
			byte[] decoded = Base64.getDecoder().decode(encrypted);

			if (decoded.length < IV_LENGTH_BYTE + 16) { // 16 = taille minimale du tag GCM
				throw new IllegalArgumentException("Invalid encrypted data length");
			}

			byte[] iv = new byte[IV_LENGTH_BYTE];
			byte[] ciphertext = new byte[decoded.length - IV_LENGTH_BYTE];
			System.arraycopy(decoded, 0, iv, 0, IV_LENGTH_BYTE);
			System.arraycopy(decoded, IV_LENGTH_BYTE, ciphertext, 0, ciphertext.length);

			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, derivedKey, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			return new String(cipher.doFinal(ciphertext), StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException("Decryption failed", e);
		}
	}
}
