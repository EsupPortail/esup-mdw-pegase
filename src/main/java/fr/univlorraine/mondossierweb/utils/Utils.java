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

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.CheminDTO;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.ObjetMaquetteDTO;
import fr.univlorraine.pegase.chc.model.CursusDCA;
import fr.univlorraine.pegase.chc.model.LignePedagogiqueDCA;
import fr.univlorraine.pegase.coc.model.Chemin;
import fr.univlorraine.pegase.coc.model.Controle;
import fr.univlorraine.pegase.insext.model.CibleInscription;
import fr.univlorraine.pegase.insext.model.InscriptionComplete;
import fr.univlorraine.pegase.insext.model.ObjetFormationOuGroupement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
	public static final Object DETAIL_INS_NON_AFFICHE = "false";
	public static final Object DETAIL_INS_VIA_BOUTON = "button";
	public static final Object DETAIL_INS_AFFICHE = "true";
	public static final String STATUT_INSCRIPTION_VALIDE = "VALIDE";
	public static final String DOSSIER_APPRENANT = "dossierApprenant";
	public static final String DOSSIER_CONSULTE_APPRENANT = "dossierConsulteApprenant";
	public static final String LARGEUR_LOGO = "34px";
	public static final String HAUTEUR_LOGO = "34px";
	public static final String EXT_PDF = ".pdf";
	private static final String SEPARATEUR_CHEMIN = ">";

	private Utils() {
		throw new IllegalStateException("Utility class");
	}




	/** formatage d'une date en chaine pour un affichage européen */
	public static String formatStringDateToDisplay(String date) {
		if(StringUtils.hasText(date)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			if(date.contains("/")) {
				formatter = new SimpleDateFormat("dd/MM/yyyy");
			}
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
		return LocalDate.parse(date, formatter);
	}

	/** Retour le code période de l'inscription */
	public static String getCodePeriode(InscriptionComplete inscription) {
		log.info("code periode : {} => {}",getCodeChemin(inscription.getCible()), inscription.getCible().getPeriode().getCode() );
		return inscription.getCible().getPeriode().getCode();
	}

	/** Retour le code voeu de l'inscription */
	public static String getCodeVoeu(InscriptionComplete inscription) {
		log.info("code chemin :"+getCodeChemin(inscription.getCible()));
		return getCodeChemin(inscription.getCible())+"@"+inscription.getCible().getPeriode().getCode();
	}

	/** Converti une liste de ObjetMaquetteExtension en hiérarchie de ObjetMaquetteDTO */
	public static List<ObjetMaquetteDTO> convertObjetMaquetteListToDTO(List<CursusDCA> listeCursus, String codeFormation, String codeRacinePeda, String codePeriode, String codeEtab) {
		List<ObjetMaquetteDTO> list = new LinkedList<>();
		boolean listObjMaquetteTrouvee = false;
		if(listeCursus != null) {
			for(CursusDCA cursus : listeCursus) {
				// Si on a pas déjà trouvé la liste des objets de maquette recherchés dans un élément précédent de la liste
				if(!listObjMaquetteTrouvee) {
					// On teste si on est sur la racine recherchée
					listObjMaquetteTrouvee = cursusIdentifie(cursus,codeEtab, codePeriode, codeFormation, codeRacinePeda);
					// Si on est sur un objet concerné par la racine
					if(listObjMaquetteTrouvee) {
						LignePedagogiqueDCA racine = cursus.getRacinePedagogique();

						// Ajout de la racine dans la liste
						ObjetMaquetteDTO objRacine = createObjetMaquetteDTO(null,racine);
						list.add(objRacine);
						log.debug("Racine {} insérée", objRacine.getCodeChemin());
						
						ajouterLesEnfants(objRacine, racine.getEnfants());

					}
				}
			}
		}
		return list;
	}

	private static void ajouterLesEnfants(ObjetMaquetteDTO objParent, List<LignePedagogiqueDCA> enfants) {
		if(enfants != null && !enfants.isEmpty()) {
			// On parcourt les enfants de la racine
			for(LignePedagogiqueDCA enfant : enfants) {
				ObjetMaquetteDTO o = createObjetMaquetteDTO(objParent.getCodeChemin(), enfant);
				objParent.getChildObjects().add(o);
				log.debug("Insertion de {} dans l'arborescence...", o.getCodeChemin());

				ajouterLesEnfants(o, enfant.getEnfants());
			}
		}
	}




	private static boolean cursusIdentifie(CursusDCA cursus, String codeEtab, String codePeriode, String codeFormation, String codeRacinePeda) {
		// Si le cursus et ses attributs sont non null
		if(cursus!=null && cursus.getCodeStructure() != null 
			&& cursus.getFormation()!=null && cursus.getFormation().getCode()!=null
			&& cursus.getPeriode() != null && cursus.getPeriode().getCode()!=null
			&& cursus.getRacinePedagogique() != null && cursus.getRacinePedagogique().getCodeObjetMaquette() !=null) {
			// Si tous les identifiant concordent
			if(cursus.getCodeStructure().equals(codeEtab) 
				&& cursus.getFormation().getCode().equals(codeFormation)
				&& cursus.getPeriode().getCode().equals(codePeriode)
				&& cursus.getRacinePedagogique().getCodeObjetMaquette().equals(codeRacinePeda) ) {
				return true;
			}
		}
		return false;
	}



/*
	private static boolean insertInList(List<ObjetMaquetteDTO> list, String cheminParent, ObjetMaquetteDTO o) {
		// On recherche l'élément parent de la liste.
		for(ObjetMaquetteDTO parent : list) {
			// Si c'est le parent de l'objet en cours
			if(parent != null && parent.getCodeChemin().equals(cheminParent)) {
				//Ajout au parent
				parent.getChildObjects().add(o);
				log.info("Element inséré.");
				return true;
			}
			// Si l'élément a des enfants et que son chemin est moins profond que celui ce l'élément à insérer
			if(parent != null && parent.getChildObjects() != null && !parent.getChildObjects().isEmpty() && parent.getCodeChemin().length() < cheminParent.length()) {
				boolean insertInChild = insertInList(parent.getChildObjects(),cheminParent,o);
				// Si l'élément a été inséré dans les enfants
				if(insertInChild) {
					return true;
				}
			}
		}
		return false;
	}
*/

	private static ObjetMaquetteDTO createObjetMaquetteDTO(String cheminParent, LignePedagogiqueDCA obj) {
		ObjetMaquetteDTO o = new ObjetMaquetteDTO();
		o.setCode(obj.getCodeObjetMaquette());
		o.setCodeChemin((cheminParent != null ? cheminParent + SEPARATEUR_CHEMIN : "") + obj.getCodeObjetMaquette());
		o.setLibelle(obj.getLibelleLongObjetMaquette());
		o.setObjet(obj);
		o.setChildObjects(new LinkedList<>());

		if(obj.getAcquisCapitalise() != null) {
			o.setAcquis(true);
		}
		if(Boolean.TRUE.equals(obj.getEstObligatoire())) {
			o.setAffecte(true);
		}
		return o;
	}

	/*
	private static ObjetMaquetteDTO createObjetMaquetteDTO(ObjetMaquetteExtension obj) {
		ObjetMaquetteDTO o = new ObjetMaquetteDTO();
		o.setCode(obj.getObjetFormation() != null ? obj.getObjetFormation().getCode() : obj.getCodeChemin());
		o.setCodeChemin(obj.getCodeChemin());
		// Si c'est un objet de formation
		if(obj.getObjetFormation() != null) {
			// Récupération du libellé court de l'objet de formation
			o.setLibelle(obj.getObjetFormation().getLibelleCourt());
		} else {
			// Si c'est un groupement, récupération du libellé court du groupement
			o.setLibelle(obj.getGroupement() != null ? obj.getGroupement().getLibelleCourt() : null);
		}

		o.setObjet(obj);
		o.setChildObjects(new LinkedList<>());

		if(Boolean.TRUE.equals(obj.getTemoinAcquis())) {
			o.setAcquis(true);
		}
		if(Boolean.TRUE.equals(obj.getTemoinAffecte())) {
			o.setAffecte(true);
		}
		if(Boolean.TRUE.equals(obj.getTemoinIAValide())) {
			o.setIaValide(true);
		}
		return o;
	}*/

	public static List<CheminDTO> convertCheminToDTO(List<Chemin> listObj, String codeCheminRacine, boolean avecControle) {
		List<CheminDTO> list = new LinkedList<>();
		if(listObj != null) {
			for(Chemin obj : listObj) {
				// Si on est sur un objet concerné par la racine
				if(obj != null && obj.getCodeChemin().contains(codeCheminRacine)) {
					CheminDTO o = createCheminDTO(obj, avecControle);
					// S'il s'agit de la racine
					if(obj.getCodeChemin().equals(codeCheminRacine)) {
						list.add(o);
						log.debug("Racine {} insérée", codeCheminRacine);
					} else {
						boolean insere = false;
						String cheminParent = o.getCodeChemin();
						log.debug("Insertion de {} dans l'arborescence...", cheminParent);
						// tant qu'on n'a pas inséré l'élément dans l'arborescence ou que le chemin contient des éléments à ignorer
						while(!insere && cheminParent.contains(SEPARATEUR_CHEMIN)) {
							// On supprime le dernier élément du chemin
							cheminParent = cheminParent.substring(0, cheminParent.lastIndexOf(SEPARATEUR_CHEMIN));
							log.debug("Recherche du parent : {}...", cheminParent);
							insere = insertInList(list, cheminParent, o);
						}
						// Si element non insere
						if(!insere) {
							// injecter l'élément à la fin de la liste
							list.add(o);
							log.debug("Element {} inséré en fin de liste car sans parent", o.getCodeChemin());
						}
					}
				}
			}
		}
		return list;
	}

	private static CheminDTO createCheminDTO(Chemin obj, boolean avecControle) {
		CheminDTO o = new CheminDTO();
		o.setCode(obj.getCodeChemin());
		o.setCodeChemin(obj.getCodeChemin());
		// Récupération du libellé court de l'objet feuille
		o.setLibelle(obj.getObjetFeuille().getLibelleCourt());

		o.setObjet(obj);
		o.setChildObjects(new LinkedList<>());

		// Si l'objet a des contrôles et qu'on doit les afficher
		if(avecControle && !obj.getListeControle().isEmpty()) {
			// Pour chaque contrôle
			for(Controle c : obj.getListeControle() ) {
				// Si le contrôle est porteur d'information
				if(c.getNote()!=null || c.getResultat()!=null || c.getAbsence()!=null) {
					// Ajout du contrôle en tant qu'enfant de l'objet en cours
					o.getChildObjects().add(createCheminDTO(c, obj));
				}
			}
		}
		return o;
	}

	private static CheminDTO createCheminDTO(Controle c, Chemin pere) {
		CheminDTO o = new CheminDTO();
		o.setCode(c.getCode() + "_" + c.getNumeroSession());
		o.setCodeChemin(pere.getCodeChemin() + ">" + c.getCode() + "_" + c.getNumeroSession());
		o.setLibelle(c.getLibelle());
		o.setControle(c);
		o.setObjet(pere);
		o.setChildObjects(new LinkedList<>());
		return o;
	}


	private static boolean insertInList(List<CheminDTO> list, String cheminParent, CheminDTO o) {
		// On recherche l'élément parent de la liste.
		for(CheminDTO parent : list) {
			// Si c'est le parent de l'objet en cours
			if(parent.getCodeChemin().equals(cheminParent)) {
				//Ajout au parent
				parent.getChildObjects().add(o);
				log.debug("Element inséré.");
				return true;
			}
			// Si l'élément a des enfants et que son chemin est moins profond que celui ce l'élément à insérer
			if(parent.getChildObjects() != null && !parent.getChildObjects().isEmpty() && parent.getCodeChemin().length() < cheminParent.length()) {
				boolean insertInChild = insertInList(parent.getChildObjects(),cheminParent,o);
				// Si l'élément a été inséré dans les enfants
				if(insertInChild) {
					return true;
				}
			}
		}
		return false;
	}

	public static String displayNote(BigDecimal note, Integer bareme, Boolean avecBareme) {
		String n = displayBigDecimal(note);

		if(StringUtils.hasText(n) && bareme != null && avecBareme != null && avecBareme.booleanValue()) {
			n += "/" + bareme;
		}
		return n;
	}

	public static String displayBigDecimal(BigDecimal bg) {
		String n = ""+bg;

		//Formatage de la note pour supprimer les zéros ou les points inutiles
		while(n.contains(".") && n.endsWith("0")) {
			n = n.substring(0, n.length()-1);
		}
		if(n.endsWith(".")) {
			n = n.substring(0, n.length()-1);
		}

		return n;
	}

	public static String getCodeChemin(CibleInscription cible) {
		// la racine est le code de la formation
		StringBuilder chemin = new StringBuilder();
		chemin.append(cible.getFormation().getCode());
		if(cible.getChemin()!=null && !cible.getChemin().isEmpty()) {
			for(ObjetFormationOuGroupement c : cible.getChemin()) {
				// Ajout des éléments au chemin
				chemin.append(SEPARATEUR_CHEMIN + c.getCode());
			}
		}
		log.debug("Chemin : {} pour Cible {} {}", chemin, cible.getFormation(), cible.getChemin());
		return chemin.toString() ;
	}

	public static void notifierSucces(String message) {
		Notification notification= Notification.show(message);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
	}

	public static void notifierAnomalie(String message) {
		Notification notification= Notification.show(message);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
	}

	public static String getFileName(String fileName) {
		int index = fileName.lastIndexOf('.');
		if(index > 0) {
			String name = fileName.substring(0, index);
			log.info("File name of {} is {}",fileName, name);
			return name;
		}
		return null;
	}




	public static String getFileExtension(String fileName) {
		int index = fileName.lastIndexOf('.');
		if(index > 0) {
			String extension = fileName.substring(index);
			log.info("File extension of {} is {}",fileName, extension);
			return extension;
		}
		return null;
	}


}
