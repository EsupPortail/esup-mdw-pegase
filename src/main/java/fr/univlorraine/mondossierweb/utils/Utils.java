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
import java.util.LinkedList;
import java.util.List;

import org.flywaydb.core.internal.util.StringUtils;

import fr.univlorraine.mondossierweb.ui.view.inscriptions.ObjetMaquetteDTO;
import fr.univlorraine.pegase.model.chc.ObjetMaquetteExtension;
import fr.univlorraine.pegase.model.insgestion.InscriptionComplete;
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
	private static final String SEPARATEUR_CHEMIN = "→";


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

	/** Retour le code période de l'inscription */
	public static String getCodePeriode(InscriptionComplete inscription) {
		log.info("code periode : {} => {}",inscription.getCible().getCodeChemin(), inscription.getCible().getPeriode().getCode() );
		return inscription.getCible().getPeriode().getCode();
	}

	/** Retour le code voeu de l'inscription */
	public static String getCodeVoeu(InscriptionComplete inscription) {
		log.info("code chemin :"+inscription.getCible().getCodeChemin());
		return inscription.getCible().getCodeChemin()+"@"+inscription.getCible().getPeriode().getCode();
	}

	/** Converti une liste de ObjetMaquetteExtension en hiérarchie de ObjetMaquetteDTO */
	public static List<ObjetMaquetteDTO> convertObjetMaquetteListToDTO(List<ObjetMaquetteExtension> listObj, String codeCheminRacine) {
		List<ObjetMaquetteDTO> list = new LinkedList<ObjetMaquetteDTO>();
		if(listObj != null) {
			for(ObjetMaquetteExtension obj : listObj) {
				// Si on est sur un objet concerné par la racine
				if(obj!=null && obj.getCodeChemin()!=null && obj.getCodeChemin().contains(codeCheminRacine)) {
					ObjetMaquetteDTO o = createObjetMaquetteDTO(obj);
					// S'il s'agit de la racine
					if(obj.getCodeChemin().equals(codeCheminRacine)) {
						list.add(o);
					} else {
						// On recherche l'élément parent de la liste.
						for(ObjetMaquetteDTO parent : list) {
							// Si c'est le parent de l'objet en cours
							if(parent != null && (parent.getCodeChemin() + SEPARATEUR_CHEMIN + o.getCode()).equals(o.getCodeChemin())) {
								//Ajout au parent
								parent.getChildObjects().add(o);
							}
						}
					}
				}
			}
		}
		return list;
	}

	private static ObjetMaquetteDTO createObjetMaquetteDTO(ObjetMaquetteExtension obj) {
		ObjetMaquetteDTO o = new ObjetMaquetteDTO();
		o.setCode(obj.getObjetFormation() != null ? obj.getObjetFormation().getCode() : obj.getCodeChemin());
		o.setCodeChemin(obj.getCodeChemin());
		o.setLibelle(obj.getObjetFormation() != null ? obj.getObjetFormation().getLibelleCourt() : null);
		o.setObjet(obj);
		o.setChildObjects(new LinkedList<ObjetMaquetteDTO>());
		return o;
	}


}
