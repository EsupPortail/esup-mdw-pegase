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
package fr.univlorraine.mondossierweb.utils;

import org.springframework.util.StringUtils;

/**
 * Utilitaire de masquage des données sensibles pour les logs.
 */
public final class LogMaskingUtil {

	private static final String MASK = "****";
	private static final int DEFAULT_MASK_PREFIX_LENGTH = 2;
	private static final int DEFAULT_MASK_SUFFIX_LENGTH = 2;
	private static final int DEFAULT_MIN_LENGTH = 8;

	private LogMaskingUtil() {
		// Utilitaire, pas d'instanciation
	}

	/**
	 * Masque une valeur sensible : garde les {@code prefixLength} premiers et
	 * les {@code suffixLength} derniers caractères, séparés par "...".
	 * Si la valeur est trop courte, retourne "****".
	 *
	 * @param value        la valeur à masquer
	 * @param prefixLength nombre de caractères à garder au début
	 * @param suffixLength nombre de caractères à garder à la fin
	 * @return la valeur masquée, ou chaîne vide si null/vide
	 */
	public static String mask(final String value, final int prefixLength, final int suffixLength) {
		if (!StringUtils.hasText(value)) {
			return "";
		}
		final int minLength = prefixLength + suffixLength + 3;
		if (value.length() <= minLength) {
			return MASK;
		}
		return value.substring(0, prefixLength) + "..." + value.substring(value.length() - suffixLength);
	}

	/**
	 * Masque une valeur sensible avec les longueurs par défaut (2 premiers / 2 derniers).
	 *
	 * @param value la valeur à masquer
	 * @return la valeur masquée
	 */
	public static String mask(final String value) {
		return mask(value, DEFAULT_MASK_PREFIX_LENGTH, DEFAULT_MASK_SUFFIX_LENGTH);
	}

	/**
	 * Masque spécifiquement un token : garde 4 premiers et 4 derniers caractères.
	 *
	 * @param token le token à masquer
	 * @return le token masqué
	 */
	public static String maskToken(final String token) {
		return mask(token, 4, 4);
	}

	/**
	 * Masque une adresse e-mail : masque la partie locale (avant le @),
	 * conserve le domaine.
	 *
	 * @param email l'email à masquer
	 * @return l'email masqué
	 */
	public static String maskEmail(final String email) {
		if (!StringUtils.hasText(email) || !email.contains("@")) {
			return mask(email);
		}
		final String[] parts = email.split("@", 2);
		final String local = parts[0];
		final String domain = parts[1];
		final String maskedLocal = local.length() <= 3
			? MASK
			: local.substring(0, 2) + "..." + local.substring(local.length() - 1);
		return maskedLocal + "@" + domain;
	}

}
