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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

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
	private transient PageTitleFormatter pageTitleFormatter;
	@Value("${etudiant.mail.ldap}")
	private transient Boolean afficherMailLdap;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private final FlexLayout coordPersoLayout = new FlexLayout();

	@PostConstruct
	private void init() {
		setSizeFull();

		coordPersoLayout.getStyle().set("margin-top", "0");
		coordPersoLayout.setWidthFull();
		coordPersoLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
		coordPersoLayout.setFlexWrap(FlexWrap.WRAP);
		//coordPersoLayout.setFlexBasis("28em", mailLayout, telLayout,mailSecoursLayout,telSecoursLayout, adresseFixeLayout, adresseAnnuelleLayout);

		add(coordPersoLayout);
	}


	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("coordonnees.title"));

		coordPersoLayout.getChildren().forEach(c -> updateCardLocale(c) );

	}

	private void updateCardLocale(Component c) {
		log.info("Traitement des messages de la card : {}", c);
		c.getChildren().forEach(l -> updateLayoutLocale(l));
	}

	private void updateLayoutLocale(Component c) {
		log.info("Traitement des layout de la card : {}", c);
		c.getChildren().forEach(tf -> updateTextFieldLocale(tf));
	}

	private void updateTextFieldLocale(Component c) {
		log.info("Traitement des messages du composant : {}", c);
		TextField t = null;

		try {
			t = (TextField) c;
		} catch (ClassCastException e) {
			log.info("Le composant n'est pas un TextField");
		}

		if(t!=null && t.getId().isPresent()) {
			String id = t.getId().orElse("");
			String type = id.split("_")[0] + "_";
			log.info("Recherche du message pour : {}", type);
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
	}

	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	private void updateData(Apprenant apprenant) {
		resetData();

		if(afficherMailLdap) {
			// TODO ajouter mail ldap
		}
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
					ajouterMail(c, cpt);
					break;
				case Utils.CANAL_CONTACT_TEL :
					ajouterTel(c, cpt);
					break;
				}

			}
		}
	}

	private void ajouterMail(ContactComplet c, int n) {

		ContactMelComplet cmc= (ContactMelComplet) c;

		Card mailCard = new Card("", true);
		mailCard.getTitre().setText(cmc.getDemandeDeContact().getLibelleAffichage());

		if(cmc.getProprietaire()!=null) {
			TextField nomMail=new TextField();
			nomMail.setId(NOM_MAIL + n);
			nomMail.setReadOnly(true);
			mailCard.addAlt(nomMail);
			nomMail.setValue(cmc.getProprietaire());
			CmpUtils.setLongTextField(nomMail);
		}

		TextField mail=new TextField();
		mail.setId(MAIL + n);
		mail.setReadOnly(true);
		mailCard.addAlt(mail);
		mail.setValue(cmc.getMail());
		CmpUtils.setLongTextField(mail);

		coordPersoLayout.addComponentAsFirst(mailCard);
		coordPersoLayout.setFlexBasis("28em", mailCard);
		mailCard.updateStyle();
		//mailCard.displayAlt();
	}

	private void ajouterTel(ContactComplet c,int n) {
		ContactTelephoneComplet ctc= (ContactTelephoneComplet) c;

		Card telCard = new Card("", true);
		telCard.getTitre().setText(ctc.getDemandeDeContact().getLibelleAffichage());

		if(ctc.getProprietaire()!=null) {
			TextField nomTel=new TextField();
			nomTel.setId(NOM_TEL + n);
			nomTel.setReadOnly(true);
			telCard.addAlt(nomTel);
			nomTel.setValue(ctc.getProprietaire());
			CmpUtils.setLongTextField(nomTel);
		}

		TextField tel=new TextField();
		tel.setId(TEL + n);
		tel.setReadOnly(true);
		telCard.addAlt(tel);
		tel.setValue(ctc.getTelephone());
		CmpUtils.setModerateTextField(tel);

		coordPersoLayout.addComponentAsFirst(telCard);
		coordPersoLayout.setFlexBasis("28em", telCard);
		telCard.updateStyle();
		//telCard.displayAlt();
	}

	private void ajouterAdresse(ContactComplet c,int n) {
		ContactAdresseComplet cac= (ContactAdresseComplet) c;

		Card adresseCard = new Card("", true);
		adresseCard.getTitre().setText(cac.getDemandeDeContact().getLibelleAffichage());

		TextField nomAdresse=new TextField();
		nomAdresse.setId(NOM_ADRESSE + n);
		nomAdresse.setReadOnly(true);
		adresseCard.addAlt(nomAdresse);
		nomAdresse.setValue(cac.getProprietaire());
		CmpUtils.setLongTextField(nomAdresse);

		TextField paysAdresse=new TextField();
		paysAdresse.setId(PAYS_ADRESSE + n);
		paysAdresse.setReadOnly(true);
		adresseCard.addAlt(paysAdresse);
		paysAdresse.setValue(cac.getPays());
		CmpUtils.setLongTextField(paysAdresse);

		if(cac.getLigne3OuVoie() != null) {
			TextField compl1Adresse=new TextField();
			compl1Adresse.setId(COMP1_ADRESSE + n);
			compl1Adresse.setReadOnly(true);
			adresseCard.addAlt(compl1Adresse);
			compl1Adresse.setValue(cac.getLigne3OuVoie());
			CmpUtils.setLongTextField(compl1Adresse);
		}

		if(cac.getLigne4OuComplement()!=null) {
			TextField compl2Adresse=new TextField();
			compl2Adresse.setId(COMP2_ADRESSE + n);
			compl2Adresse.setReadOnly(true);
			adresseCard.addAlt(compl2Adresse);
			compl2Adresse.setValue(cac.getLigne4OuComplement());
			CmpUtils.setLongTextField(compl2Adresse);
		}

		if(cac.getLigne1OuEtage()!=null) {
			TextField numVoieAdresse=new TextField();
			numVoieAdresse.setId(NUM_VOIE_ADRESSE + n);
			numVoieAdresse.setReadOnly(true);
			adresseCard.addAlt(numVoieAdresse);
			numVoieAdresse.setValue(cac.getLigne1OuEtage());
			CmpUtils.setLongTextField(numVoieAdresse);
		}

		if(cac.getLigne2OuBatiment()!=null) {
			TextField lieuServAdresse=new TextField();
			lieuServAdresse.setId(LIEU_SERV_ADRESSE + n);
			lieuServAdresse.setReadOnly(true);
			adresseCard.addAlt(lieuServAdresse);
			lieuServAdresse.setValue(cac.getLigne2OuBatiment());
			CmpUtils.setLongTextField(lieuServAdresse);
		}

		TextField codePostalAdresse=new TextField();
		codePostalAdresse.setId(CODE_POSTAL_ADRESSE + n);
		codePostalAdresse.setReadOnly(true);
		adresseCard.addAlt(codePostalAdresse);
		CmpUtils.setLongTextField(codePostalAdresse);

		TextField communeAdresse=new TextField();
		communeAdresse.setId(COMMUNE_ADRESSE + n);
		communeAdresse.setReadOnly(true);
		adresseCard.addAlt(communeAdresse);
		CmpUtils.setLongTextField(communeAdresse);


		if(cac.getPays()!=null && cac.getPays().equals(Utils.CODE_PAYS_FRANCE)) {
			codePostalAdresse.setValue(cac.getCodePostal());
			communeAdresse.setValue(cac.getCommune());
		} else {
			codePostalAdresse.setValue(cac.getCodePostal());
			communeAdresse.setValue(cac.getLigne5Etranger());
		}

		coordPersoLayout.add(adresseCard);
		coordPersoLayout.setFlexBasis("28em", adresseCard);
		adresseCard.updateStyle();
	}


	@Override
	public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String codeApprenant) {
		// Sécurisation de l'accès au dossier en paramètre
		if(!securityService.secureAccess(codeApprenant)) {
			Notification.show(getTranslation("error.accesdossierrefuse"));
		}
		// Vérification que les informations nécessaires à la vue (dossier) ont été récupérées
		securityService.checkDossier();
		// Mise à jour de l'affichage
		updateData(securityService.getDossier()!=null ? securityService.getDossier().getApprenant() : null);
	}

}
