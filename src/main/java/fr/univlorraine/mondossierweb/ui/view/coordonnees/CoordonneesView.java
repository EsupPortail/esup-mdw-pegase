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
package fr.univlorraine.mondossierweb.ui.view.coordonnees;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.controllers.EtudiantController;
import fr.univlorraine.mondossierweb.service.LdapService;
import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import fr.univlorraine.pegase.model.insgestion.Apprenant;
import fr.univlorraine.pegase.model.insgestion.ContactAdresseComplet;
import fr.univlorraine.pegase.model.insgestion.ContactComplet;
import fr.univlorraine.pegase.model.insgestion.ContactMelComplet;
import fr.univlorraine.pegase.model.insgestion.ContactTelephoneComplet;
import fr.univlorraine.pegase.model.insgestion.DemandeDeContactSimple;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class CoordonneesView extends VerticalLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver, HasUrlParameter<String> {

	private static final String NOM_TEL = "nomTel_";

	private static final String TEL = "tel_";

	private static final String NOM_MAIL = "nomMail_";

	private static final String MAIL = "mail_";

	private static final String CONTACT = "contact_";

	private static final String NOM_ADRESSE = "nomAdresse_";

	private static final String PAYS_ADRESSE = "paysAdresse_";

	private static final String COMP1_ADRESSE = "compl1Adresse_";

	private static final String COMP2_ADRESSE = "compl2Adresse_";

	private static final String NUM_VOIE_ADRESSE = "numVoieAdresse_";

	private static final String LIEU_SERV_ADRESSE = "lieuServAdresse_";

	private static final String CODE_POSTAL_ADRESSE = "codePostalAdresse_";

	private static final String COMMUNE_ADRESSE = "communeAdresse_";

	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient EtudiantController etudiantController;
	@Autowired
	protected transient LdapService ldapService;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Value("${etudiant.mail.ldap}")
	private transient Boolean afficherMailLdap;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private final VerticalLayout coordPersoLayout = new VerticalLayout();

	// Carte pour les contacts (mail, tel) personnels
	private Card contacts;
	// Carte pour les contacts (mail, tel) d'urgence
	private Card contactsUrgence;

	@PostConstruct
	private void init() {
		setSizeFull();

		//coordPersoLayout.getStyle().set("margin-top", "0");
		coordPersoLayout.setWidthFull();
		coordPersoLayout.getStyle().set("max-width", "52em");
		coordPersoLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
		//coordPersoLayout.setFlexWrap(FlexWrap.WRAP);

		add(coordPersoLayout);
	}


	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		log.info("localeChange");
		setViewTitle(getTranslation("coordonnees.title"));

		coordPersoLayout.getChildren().forEach(c -> updateCardLocale(c) );
		// Si on a une carte de contacts personnels
		if(contacts!=null) {
			// On met à jour son titre
			contacts.getTitre().setText(getTranslation("contacts.perso"));
		}
		// Si on a une carte de contacts d'urgence
		if(contactsUrgence!=null) {
			// On met à jour son titre
			contactsUrgence.getTitre().setText(getTranslation("contacts.urgence"));
		}

	}

	private void updateCardLocale(Component c) {
		//log.info("Traitement des messages de la card : {}", c);
		c.getChildren().forEach(l -> updateLayoutLocale(l));
	}

	private void updateLayoutLocale(Component c) {
		//log.info("Traitement des layout de la card : {}", c);
		c.getChildren().forEach(fl -> updateFormLayoutLocale(fl));
	}

	private void updateFormLayoutLocale(Component c) {
		//log.info("Traitement des formlayout de la card : {}", c);
		c.getChildren().forEach(tf -> updateTextFieldLocale(tf));
	}

	private void updateTextFieldLocale(Component c) {
		//log.info("Traitement des messages du composant : {}", c);
		TextField t = null;

		try {
			t = (TextField) c;
		} catch (ClassCastException e) {
			log.debug("Le composant n'est pas un TextField");
		}

		if(t!=null && t.getId().isPresent()) {
			String id = t.getId().orElse("");
			String type = id.split("_")[0] + "_";
			//log.info("Recherche du message pour : {}", type);
			switch(type) {
			case NOM_TEL :
				t.setLabel(getTranslation("tel.nom"));
				break;
			case TEL :
				t.setLabel(getTranslation("tel.libelle"));
				break;
			case NOM_MAIL :
				t.setLabel(getTranslation("mail.nom"));
				break;
			case MAIL :
				t.setLabel(getTranslation("mail.libelle"));
				break;
			case NOM_ADRESSE :
				t.setLabel(getTranslation("adresse.nom"));
				break;
			case PAYS_ADRESSE :
				t.setLabel(getTranslation("adresse.pays"));
				break;
			case COMP1_ADRESSE :
				t.setLabel(getTranslation("adresse.compl1"));
				break;
			case COMP2_ADRESSE :
				t.setLabel(getTranslation("adresse.compl2"));
				break;
			case NUM_VOIE_ADRESSE :
				t.setLabel(getTranslation("adresse.numvoie"));
				break;
			case LIEU_SERV_ADRESSE :
				t.setLabel(getTranslation("adresse.lieuservice"));
				break;
			case CODE_POSTAL_ADRESSE :
				t.setLabel(getTranslation("adresse.codepostal"));
				break;
			case COMMUNE_ADRESSE :
				t.setLabel(getTranslation("adresse.commune"));
				break;

			}
		}

	}


	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

	/**
	 * Reset toutes les données affichées
	 * @param apprenant
	 */
	private void resetData() {
		coordPersoLayout.removeAll();
		// Si on a une carte de contacts personnels
		if(contacts!=null) {
			// On supprime le contenu
			contacts.removeAll();
			contacts=null;
		}
		// Si on a une carte de contacts d'urgence
		if(contactsUrgence!=null) {
			// On supprime le contenu
			contactsUrgence.removeAll();
			contactsUrgence=null;
		}
	}

	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	private void updateData(Apprenant apprenant) {
		resetData();
		log.info("updateDate CoordonneesView...");
		if(apprenant != null && apprenant.getContacts()!=null && !apprenant.getContacts().isEmpty()) {
			int cpt=0;
			// Pour chaque contact
			for(ContactComplet c : apprenant.getContacts()) {
				cpt++;
				// En fonction du type de contact
				switch(c.getCanalCommunication().getValue()) {
				case Utils.CANAL_CONTACT_ADRESSE :
					ajouterAdresse(c, cpt);
					break;
				case Utils.CANAL_CONTACT_MAIL :
					// Si mail de contact d'urgence
					if(StringUtils.hasText(c.getProprietaire())) {
						ajouterInfoContactUrgence(c, cpt,getTranslation("mail.libelle"));
					} else {
						// mail de l'étudiant
						ajouterInfoContactPerso(c, cpt);
					}
					break;
				case Utils.CANAL_CONTACT_TEL :
					// Si numéro du contact d'urgence
					if(StringUtils.hasText(c.getProprietaire())) {
						ajouterInfoContactUrgence(c, cpt, getTranslation("tel.libelle2"));
					} else {
						ajouterInfoContactPerso(c, cpt);
					}
					break;
				}

			}
			if(afficherMailLdap) {
				// récupération du mail dans le ldap
				String mail = ldapService.getStudentMailByCodeApprenant(apprenant.getCode());
				// Si on a récupéré un mail
				if(StringUtils.hasText(mail)) {
					cpt++;
					// Création d'un contact correspondant au mail établissement
					ContactMelComplet c = new ContactMelComplet();
					DemandeDeContactSimple dmc = new DemandeDeContactSimple();
					dmc.setLibelleAffichage(getTranslation("coordonnees.mail.etablissement"));
					c.setDemandeDeContact(dmc);
					c.setMail(mail);
					// Ajout du mail dans la vue
					//ajouterMailContact(c, cpt);
					ajouterInfoContactPerso(c, cpt);
				}
			}
			updateStyle();
		}
		// Si on a une carte de contacts d'urgence
		if(contactsUrgence!=null) {
			// On met à jour le titre
			contactsUrgence.getTitre().setText(getTranslation("contacts.urgence"));
			// On l'ajoute en haut de la vue
			coordPersoLayout.addComponentAsFirst(contactsUrgence);
		}
		// Si on a une carte de contacts personnels
		if(contacts!=null) {
			// On met à jour le titre
			contacts.getTitre().setText(getTranslation("contacts.perso"));
			// On l'ajoute en haut de la vue
			coordPersoLayout.addComponentAsFirst(contacts);
		}

		log.info("updateDate CoordonneesView DONE");
	}

	private void ajouterInfoContactPerso(ContactComplet c, int n) {
		// Si on la carte de contact personnels est null
		if(contacts == null) {
			// On initialise la carte
			contacts = new Card(VaadinIcon.USER.create(),"", true);
		}

		FormLayout contactLayout = new FormLayout();
		contactLayout.getStyle().set("margin", "0");
		contacts.addAlt(contactLayout);

		TextField contact=new TextField();
		contact.setReadOnly(true);
		contactLayout.add(contact);
		contact.setLabel(c.getDemandeDeContact().getLibelleAffichage());
		CmpUtils.valueAndVisibleIfNotNull(contact,getValue(c));
		CmpUtils.setLongTextField(contact);

		contacts.displayAlt();
	}

	private void ajouterInfoContactUrgence(ContactComplet c, int n, String libelle) {
		// Si on la carte de contact d'urgence est null
		if(contactsUrgence == null) {
			// On initialise la carte
			contactsUrgence = new Card(VaadinIcon.BELL.create(),"", true);
		}

		FormLayout contactLayout = new FormLayout();
		contactLayout.getStyle().set("margin", "0");
		contactsUrgence.addAlt(contactLayout);

		TextField contact=new TextField();
		contact.setReadOnly(true);
		contactLayout.add(contact);
		contact.setLabel(libelle+ " ("+c.getProprietaire()+")");
		CmpUtils.valueAndVisibleIfNotNull(contact,getValue(c));
		CmpUtils.setLongTextField(contact);

		contactsUrgence.displayAlt();
	}

	private String getValue(ContactComplet c) {
		// En fonction du type de contact
		switch(c.getCanalCommunication().getValue()) {
		case Utils.CANAL_CONTACT_MAIL :
			ContactMelComplet cmc= (ContactMelComplet) c;
			return cmc.getMail();
		case Utils.CANAL_CONTACT_TEL :
			ContactTelephoneComplet ctc= (ContactTelephoneComplet) c;
			return ctc.getTelephone();
		}
		return null;
	}


	/**
	 * Ancienne méthode d'ajout d'un mail de contact dans une carte dédiée
	 * @param c
	 * @param n
	 */
	private void ajouterMailContact(ContactComplet c, int n) {

		ContactMelComplet cmc= (ContactMelComplet) c;

		Card mailCard = new Card(VaadinIcon.ENVELOPE_O.create(),"", true);
		mailCard.getTitre().setText(cmc.getDemandeDeContact().getLibelleAffichage());

		FormLayout mailLayout = new FormLayout();
		mailLayout.getStyle().set("margin", "0");
		mailCard.addAlt(mailLayout);

		TextField nomMail=new TextField();
		nomMail.setId(NOM_MAIL + n);
		nomMail.setReadOnly(true);
		mailLayout.add(nomMail);
		CmpUtils.valueAndVisibleIfNotNull(nomMail, cmc.getProprietaire());
		CmpUtils.setLongTextField(nomMail);


		TextField mail=new TextField();
		mail.setId(MAIL + n);
		mail.setReadOnly(true);
		mailLayout.add(mail);
		CmpUtils.valueAndVisibleIfNotNull(mail,cmc.getMail());
		CmpUtils.setLongTextField(mail);

		coordPersoLayout.addComponentAsFirst(mailCard);

		mailCard.displayAlt();
	}

	/**
	 * Ancienne méthode d'ajout d'une tel de contact dans une carte dédiée
	 * @param c
	 * @param n
	 */
	private void ajouterTelContact(ContactComplet c,int n) {
		ContactTelephoneComplet ctc= (ContactTelephoneComplet) c;

		Card telCard = new Card(VaadinIcon.PHONE.create(),"", true);
		telCard.getTitre().setText(ctc.getDemandeDeContact().getLibelleAffichage());

		FormLayout telLayout = new FormLayout();
		telLayout.getStyle().set("margin", "0");
		telCard.addAlt(telLayout);


		TextField nomTel=new TextField();
		nomTel.setId(NOM_TEL + n);
		nomTel.setReadOnly(true);
		telLayout.add(nomTel);
		CmpUtils.valueAndVisibleIfNotNull(nomTel,ctc.getProprietaire());
		CmpUtils.setLongTextField(nomTel);

		TextField tel=new TextField();
		tel.setId(TEL + n);
		tel.setReadOnly(true);
		telLayout.add(tel);
		CmpUtils.valueAndVisibleIfNotNull(tel,ctc.getTelephone());
		CmpUtils.setModerateTextField(tel);

		coordPersoLayout.addComponentAsFirst(telCard);

		telCard.displayAlt();
	}

	private void ajouterAdresse(ContactComplet c,int n) {
		ContactAdresseComplet cac= (ContactAdresseComplet) c;

		Card adresseCard = new Card(VaadinIcon.LOCATION_ARROW_CIRCLE.create(),"", true);
		adresseCard.getTitre().setText(cac.getDemandeDeContact().getLibelleAffichage());

		FormLayout adresseLayout = new FormLayout();
		adresseLayout.getStyle().set("margin", "0");
		adresseCard.addAlt(adresseLayout);

		TextField nomAdresse=new TextField();
		nomAdresse.setId(NOM_ADRESSE + n);
		nomAdresse.setReadOnly(true);
		adresseLayout.add(nomAdresse);
		CmpUtils.valueAndVisibleIfNotNull(nomAdresse,cac.getProprietaire());
		CmpUtils.setLongTextField(nomAdresse);


		TextField paysAdresse=new TextField();
		paysAdresse.setId(PAYS_ADRESSE + n);
		paysAdresse.setReadOnly(true);
		adresseLayout.add(paysAdresse);
		CmpUtils.valueAndVisibleIfNotNull(paysAdresse,cac.getPays());
		CmpUtils.setLongTextField(paysAdresse);


		TextField compl1Adresse=new TextField();
		compl1Adresse.setId(COMP1_ADRESSE + n);
		compl1Adresse.setReadOnly(true);
		adresseLayout.add(compl1Adresse);
		CmpUtils.valueAndVisibleIfNotNull(compl1Adresse,cac.getLigne3OuVoie());
		CmpUtils.setLongTextField(compl1Adresse);


		TextField compl2Adresse=new TextField();
		compl2Adresse.setId(COMP2_ADRESSE + n);
		compl2Adresse.setReadOnly(true);
		adresseLayout.add(compl2Adresse);
		CmpUtils.valueAndVisibleIfNotNull(compl2Adresse,cac.getLigne4OuComplement());
		CmpUtils.setLongTextField(compl2Adresse);



		TextField numVoieAdresse=new TextField();
		numVoieAdresse.setId(NUM_VOIE_ADRESSE + n);
		numVoieAdresse.setReadOnly(true);
		adresseLayout.add(numVoieAdresse);
		CmpUtils.valueAndVisibleIfNotNull(numVoieAdresse,cac.getLigne1OuEtage());
		CmpUtils.setLongTextField(numVoieAdresse);



		TextField lieuServAdresse=new TextField();
		lieuServAdresse.setId(LIEU_SERV_ADRESSE + n);
		lieuServAdresse.setReadOnly(true);
		adresseLayout.add(lieuServAdresse);
		CmpUtils.valueAndVisibleIfNotNull(lieuServAdresse,cac.getLigne2OuBatiment());
		CmpUtils.setLongTextField(lieuServAdresse);


		TextField codePostalAdresse=new TextField();
		codePostalAdresse.setId(CODE_POSTAL_ADRESSE + n);
		codePostalAdresse.setReadOnly(true);
		adresseLayout.add(codePostalAdresse);
		CmpUtils.setLongTextField(codePostalAdresse);

		TextField communeAdresse=new TextField();
		communeAdresse.setId(COMMUNE_ADRESSE + n);
		communeAdresse.setReadOnly(true);
		adresseLayout.add(communeAdresse);
		CmpUtils.setLongTextField(communeAdresse);


		if(cac.getPays()!=null && cac.getPays().equals(Utils.CODE_PAYS_FRANCE)) {
			CmpUtils.valueAndVisibleIfNotNull(codePostalAdresse,cac.getCodePostal());
			CmpUtils.valueAndVisibleIfNotNull(communeAdresse,cac.getCommune());
		} else {
			CmpUtils.valueAndVisibleIfNotNull(codePostalAdresse,cac.getCodePostal());
			CmpUtils.valueAndVisibleIfNotNull(communeAdresse,cac.getLigne5Etranger());
		}

		coordPersoLayout.add(adresseCard);

		adresseCard.displayAlt();
	}


	protected void updateStyle() {
		// Si on a une carte de contact
		if(contacts!=null) {
			// On met à jour son style css
			contacts.updateStyle();
		}
		// Si on a une carte de contact d'urgence
		if(contactsUrgence!=null) {
			// On met à jour son style css
			contactsUrgence.updateStyle();
		}

		List<Component> listComp = coordPersoLayout.getChildren().collect(Collectors.toList());
		int cpt=0;
		for(Component c : listComp) {
			cpt++;
			Card coordCard = (Card) c; 
			coordCard.updateStyle();
			if(cpt<listComp.size()) {
				coordCard.addClassName("card-with-separator");
			}
		}

	}

	@Override
	public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String codeApprenant) {
		// Sécurisation de l'accès au dossier en paramètre
		if(!securityService.secureAccess(codeApprenant)) {
			Notification.show(getTranslation("error.accesdossierrefuse"));
		}
		// Vérification que les informations nécessaires à la vue (dossier) ont été récupérées
		etudiantController.checkDossier();
		// Mise à jour de l'affichage
		updateData(etudiantController.getDossier()!=null ? etudiantController.getDossier().getApprenant() : null);
		//Force la maj des label
		localeChange(null);
	}	

}
