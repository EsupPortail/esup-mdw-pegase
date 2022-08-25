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

import java.nio.charset.StandardCharsets;
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

	private String algo = null;
	private byte[] cle = null;
	
	
	@Value("${app.secretkey}")
	private String secretKey;	

	private boolean attributesok  = false;

	public JPACryptoConverter() {
		super();
	}


	private void checkParam() {
		if(!attributesok) {
			algo = "AES/ECB/PKCS5Padding";
			
			cle = secretKey.getBytes();

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
		Key key = new SecretKeySpec(cle, "AES");
		try {
			final Cipher c = Cipher.getInstance(algo);
			c.init(Cipher.ENCRYPT_MODE, key);
			return new String(Base64.getEncoder().encode(c.doFinal(sensitive.getBytes())), StandardCharsets.UTF_8);
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
		Key key = new SecretKeySpec(cle, "AES");
		try {
			final Cipher c = Cipher.getInstance(algo);
			c.init(Cipher.DECRYPT_MODE, key);
			return new String(c.doFinal(Base64.getDecoder().decode(sensitive.getBytes(StandardCharsets.UTF_8))));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

