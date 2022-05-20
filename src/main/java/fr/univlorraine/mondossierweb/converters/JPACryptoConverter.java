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

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Converter
@Scope("singleton")
@Component
@Slf4j
public class JPACryptoConverter implements AttributeConverter<String, String> {

	private static String ALGORITHM = null;
	private static byte[] KEY = null;
	
	
	@Value("${app.secretkey}")
	private transient String secretKey;	

	private boolean attributesok  = false;

	public JPACryptoConverter() {
		super();
	}


	private void checkParam() {
		if(!attributesok) {
			ALGORITHM = "AES/ECB/PKCS5Padding";
			
			KEY = secretKey.getBytes();

			attributesok = true;

			log.info("JPACryptoConverter initialized");

		}
	}


	@Override
	public String convertToDatabaseColumn(String sensitive) {
		if(!StringUtils.hasText(sensitive)) {
			return null;
		}
		checkParam();
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			final String encrypted = new String(Base64.getEncoder().encode(c
					.doFinal(sensitive.getBytes())), "UTF-8");
			//log.info(sensitive+ " -> "+encrypted);
			return encrypted;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String sensitive) {
		if(!StringUtils.hasText(sensitive)) {
			return null;
		}
		checkParam();
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			final String decrypted = new String(c.doFinal(Base64.getDecoder()
					.decode(sensitive.getBytes("UTF-8"))));
			//log.info(sensitive+ " -> "+decrypted);
			return decrypted;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

