/**
 *
 *  ESUP-Portail MONDOSSIERWEB - Copyright (c) 2020 ESUP-Portail consortium
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.flywaydb.core.internal.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Outils pour la gestion des variables static
 * @author Charlie Dubois
 */
@Slf4j
public final class Utils {

	public static final String CODE_PAYS_FRANCE = "100";
	public static final String CANAL_CONTACT_ADRESSE = "ContactAdresseComplet";
	public static final String CANAL_CONTACT_MAIL = "ContactMelComplet";
	public static final String CANAL_CONTACT_TEL = "ContactTelephoneComplet";
	public static final String TEM_INS_VALIDE = "valide";
	public static final Object TEM_INS_PAYEE = "valide";


	/** formatage d'une date en chaine pour un affichage européen */
	public static String formatStringDateToDisplay(String date) {
		if(StringUtils.hasText(date)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = formatter.parse(date);
				return formatDateToDisplay(d);
			} catch (ParseException e) {
				log.error("formatStringDateToDisplay from date : " + date, e);
				return date;
			}
		}
		return date;
	}

	/** formatage d'une date pour un affichage européen */
	public static String formatDateToDisplay(final Date d) {
		if (d != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(d);
		} 
		return "";
	}

	/** retourne vrai si la date en parametre est passée */
	public static boolean estPassee(String date) {
		LocalDate jour = LocalDate.now();
		return jour.isAfter(getLocalDateFromJsonDate(date));
	}

	/** Convertit la date JSON en LocalDate */
	private static LocalDate getLocalDateFromJsonDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
	}

}
