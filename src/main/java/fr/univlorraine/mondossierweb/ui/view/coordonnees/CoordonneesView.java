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
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import fr.univlorraine.pegase.model.insgestion.Apprenant;
import fr.univlorraine.pegase.model.insgestion.ContactComplet;
import lombok.Getter;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class CoordonneesView extends VerticalLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver, HasUrlParameter<String> {

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


	private final Card mailLayout = new Card("", false);
	private final Card telLayout = new Card("", false);
	private final Card mailSecoursLayout = new Card("", true);
	private final Card telSecoursLayout = new Card("", true);
	private final Card adresseFixeLayout = new Card("", true);
	private final Card adresseAnnuelleLayout = new Card("", true);

	private final FlexLayout coordPersoLayout = new FlexLayout(mailLayout, telLayout,mailSecoursLayout,telSecoursLayout, adresseFixeLayout, adresseAnnuelleLayout);

	private final TextField mailEtab=new TextField();
	private final TextField mail=new TextField();
	private final TextField tel=new TextField();
	private final TextField mailSecours=new TextField();
	private final TextField nomMailSecours=new TextField();
	private final TextField telSecours=new TextField();
	private final TextField nomTelSecours=new TextField();

	private final TextField nomAdFixe=new TextField();
	private final TextField paysAdFixe=new TextField();
	private final TextField compl1AdFixe=new TextField();
	private final TextField compl2AdFixe=new TextField();
	private final TextField numVoieAdFixe=new TextField();
	private final TextField lieuServAdFixe=new TextField();
	private final TextField codePostalAdFixe=new TextField();

	private final TextField paysAdAnu=new TextField();
	private final TextField compl1AdAnu=new TextField();
	private final TextField compl2AdAnu=new TextField();
	private final TextField numVoieAdAnu=new TextField();
	private final TextField lieuServAdAnu=new TextField();
	private final TextField codePostalAdAnu=new TextField();

	@PostConstruct
	private void init() {
		setSizeFull();
		initMail();
		initTel();
		initMailSecours();
		initTelSecours();
		initAdresseFixe();
		initAdresseAnnuelle();

		coordPersoLayout.getStyle().set("margin-top", "0");
		coordPersoLayout.setWidthFull();
		coordPersoLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
		coordPersoLayout.setFlexWrap(FlexWrap.WRAP);
		coordPersoLayout.setFlexBasis("28em", mailLayout, telLayout,mailSecoursLayout,telSecoursLayout, adresseFixeLayout, adresseAnnuelleLayout);

		add(coordPersoLayout);
		updateStyle();
	}

	private void initMail() {
		// Si on affiche le mail issu du LDAP
		if(afficherMailLdap) {
			mailEtab.setReadOnly(true);
			mailLayout.add(mailEtab);
			CmpUtils.setLongTextField(mailEtab);
		}
		mail.setReadOnly(true);
		mailLayout.add(mail);
		CmpUtils.setLongTextField(mail);
	}

	private void initTel() {
		tel.setReadOnly(true);
		telLayout.add(tel);
		CmpUtils.setModerateTextField(tel);
	}

	private void initMailSecours() {

		nomMailSecours.setReadOnly(true);
		mailSecoursLayout.addAlt(nomMailSecours);
		CmpUtils.setLongTextField(nomMailSecours);

		mailSecours.setReadOnly(true);
		mailSecoursLayout.addAlt(mailSecours);
		CmpUtils.setLongTextField(mailSecours);
	}

	private void initTelSecours() {

		nomTelSecours.setReadOnly(true);
		telSecoursLayout.addAlt(nomTelSecours);
		CmpUtils.setLongTextField(nomTelSecours);

		telSecours.setReadOnly(true);
		telSecoursLayout.addAlt(telSecours);
		CmpUtils.setModerateTextField(telSecours);
	}

	private void initAdresseFixe() {

		nomAdFixe.setReadOnly(true);
		adresseFixeLayout.addAlt(nomAdFixe);
		CmpUtils.setLongTextField(nomAdFixe);

		paysAdFixe.setReadOnly(true);
		adresseFixeLayout.addAlt(paysAdFixe);
		CmpUtils.setLongTextField(paysAdFixe);

		compl1AdFixe.setReadOnly(true);
		adresseFixeLayout.addAlt(compl1AdFixe);
		CmpUtils.setLongTextField(compl1AdFixe);

		compl2AdFixe.setReadOnly(true);
		adresseFixeLayout.addAlt(compl2AdFixe);
		CmpUtils.setLongTextField(compl2AdFixe);

		numVoieAdFixe.setReadOnly(true);
		adresseFixeLayout.addAlt(numVoieAdFixe);
		CmpUtils.setLongTextField(numVoieAdFixe);

		lieuServAdFixe.setReadOnly(true);
		adresseFixeLayout.addAlt(lieuServAdFixe);
		CmpUtils.setLongTextField(lieuServAdFixe);

		codePostalAdFixe.setReadOnly(true);
		adresseFixeLayout.addAlt(codePostalAdFixe);
		CmpUtils.setLongTextField(codePostalAdFixe);
	}

	private void initAdresseAnnuelle() {

		paysAdAnu.setReadOnly(true);
		adresseAnnuelleLayout.addAlt(paysAdAnu);
		CmpUtils.setLongTextField(paysAdAnu);

		compl1AdAnu.setReadOnly(true);
		adresseAnnuelleLayout.addAlt(compl1AdAnu);
		CmpUtils.setLongTextField(compl1AdAnu);

		compl2AdAnu.setReadOnly(true);
		adresseAnnuelleLayout.addAlt(compl2AdAnu);
		CmpUtils.setLongTextField(compl2AdAnu);

		numVoieAdAnu.setReadOnly(true);
		adresseAnnuelleLayout.addAlt(numVoieAdAnu);
		CmpUtils.setLongTextField(numVoieAdAnu);

		lieuServAdAnu.setReadOnly(true);
		adresseAnnuelleLayout.addAlt(lieuServAdAnu);
		CmpUtils.setLongTextField(lieuServAdAnu);

		codePostalAdAnu.setReadOnly(true);
		adresseAnnuelleLayout.addAlt(codePostalAdAnu);
		CmpUtils.setLongTextField(codePostalAdAnu);
	}


	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("coordonnees.title"));

		telLayout.getTitre().setText(getTranslation("tel.titre"));
		tel.setLabel(getTranslation("tel.libelle"));

		mailLayout.getTitre().setText(getTranslation("mail.titre"));
		mailEtab.setLabel(getTranslation("mailetab.libelle"));
		mail.setLabel(getTranslation("mail.libelle"));

		telSecoursLayout.getTitre().setText(getTranslation("telsecours.titre"));
		telSecours.setLabel(getTranslation("tel.secours.libelle"));
		nomTelSecours.setLabel(getTranslation("tel.secours.nom"));

		mailSecoursLayout.getTitre().setText(getTranslation("mailsecours.titre"));
		mailSecours.setLabel(getTranslation("mail.secours.libelle"));
		nomMailSecours.setLabel(getTranslation("mail.secours.nom"));

		adresseFixeLayout.getTitre().setText(getTranslation("adresse.fixe.titre"));
		nomAdFixe.setLabel(getTranslation("adresse.fixe.nom"));
		paysAdFixe.setLabel(getTranslation("adresse.fixe.pays"));
		compl1AdFixe.setLabel(getTranslation("adresse.fixe.compl1"));
		compl2AdFixe.setLabel(getTranslation("adresse.fixe.compl2"));
		numVoieAdFixe.setLabel(getTranslation("adresse.fixe.numvoie"));
		lieuServAdFixe.setLabel(getTranslation("adresse.fixe.lieuservice"));
		codePostalAdFixe.setLabel(getTranslation("adresse.fixe.codepostal"));




		adresseAnnuelleLayout.getTitre().setText(getTranslation("adresse.annuelle.titre"));
		paysAdAnu.setLabel(getTranslation("adresse.annuelle.pays"));
		compl1AdAnu.setLabel(getTranslation("adresse.annuelle.compl1"));
		compl2AdAnu.setLabel(getTranslation("adresse.annuelle.compl2"));
		numVoieAdAnu.setLabel(getTranslation("adresse.annuelle.numvoie"));
		lieuServAdAnu.setLabel(getTranslation("adresse.annuelle.lieuservice"));
		codePostalAdAnu.setLabel(getTranslation("adresse.annuelle.codepostal"));

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
		mailEtab.setValue("");
		mail.setValue("");
		tel.setValue("");
		mailSecours.setValue("");
		nomMailSecours.setValue("");
		telSecours.setValue("");
		nomTelSecours.setValue("");

		nomAdFixe.setValue("");
		paysAdFixe.setValue("");
		compl1AdFixe.setValue("");
		compl2AdFixe.setValue("");
		numVoieAdFixe.setValue("");
		lieuServAdFixe.setValue("");
		codePostalAdFixe.setValue("");

		paysAdAnu.setValue("");
		compl1AdAnu.setValue("");
		compl2AdAnu.setValue("");
		numVoieAdAnu.setValue("");
		lieuServAdAnu.setValue("");
		codePostalAdAnu.setValue("");
	}

	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	private void updateData(Apprenant apprenant) {
		resetData();
		if(apprenant != null && apprenant.getContacts()!=null && !apprenant.getContacts().isEmpty()) {
			for(ContactComplet c : apprenant.getContacts()) {
				// TODO Mise à jour des infos
				
			}
		}
	}

	//@Override
	protected void updateStyle() {
		telLayout.updateStyle();
		mailLayout.updateStyle();
		telSecoursLayout.updateStyle();
		mailSecoursLayout.updateStyle();
		adresseFixeLayout.updateStyle();
		adresseAnnuelleLayout.updateStyle();
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
