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
package fr.univlorraine.mondossierweb.ui.view.etatcivil;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

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
import com.vaadin.flow.router.RouteAlias;

import fr.univlorraine.mondossierweb.controllers.MainController;
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
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class EtatCivilView extends VerticalLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver, HasUrlParameter<String> {

	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient MainController etudiantController;

	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private final Card identiteCard = new Card(VaadinIcon.USER.create(),"", false);
	private final Card naissanceCard = new Card(VaadinIcon.GLOBE.create(),"", false);
	private final VerticalLayout etatcivilLayout = new VerticalLayout(identiteCard, naissanceCard);

	private final TextField nomFamille=new TextField();
	private final TextField nomUsage=new TextField();
	private final TextField prenom=new TextField();
	private final TextField prenom2=new TextField();
	private final TextField prenom3=new TextField();


	private final TextField dateNaissance=new TextField();
	private final TextField paysNaissance=new TextField();
	private final TextField communeNaissance=new TextField();
	private final TextField nationaliteNaissance=new TextField();
	private final TextField nationaliteNaissance2=new TextField();



	@PostConstruct
	private void init() {
		setSizeFull();
		initIdentite();
		initNaissance();
		etatcivilLayout.setWidthFull();
		etatcivilLayout.getStyle().set("max-width", "52em");
		etatcivilLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
		//etatcivilLayout.setFlexWrap(FlexWrap.WRAP);
		//etatcivilLayout.setFlexBasis("28em", identiteLayout, naissanceLayout);
		add(etatcivilLayout);
		updateStyle();
	}

	private void initIdentite() {

		FormLayout identiteLayout = new FormLayout();
		identiteLayout.getStyle().set("margin", "0");
		
		nomFamille.setReadOnly(true);
		identiteLayout.add(nomFamille);

		nomUsage.setReadOnly(true);
		identiteLayout.add(nomUsage);

		prenom.setReadOnly(true);
		identiteLayout.add(prenom);

		prenom2.setReadOnly(true);
		identiteLayout.add(prenom2);

		prenom3.setReadOnly(true);
		identiteLayout.add(prenom3);
		
		identiteCard.add(identiteLayout);

		CmpUtils.setLongTextField(nomFamille);

		CmpUtils.setLongTextField(nomUsage);

		CmpUtils.setModerateTextField(prenom);

		CmpUtils.setModerateTextField(prenom2);

		CmpUtils.setModerateTextField(prenom3);

	}

	private void initNaissance() {
		FormLayout naissanceLayout = new FormLayout();
		naissanceLayout.addClassName("card-layout");
		naissanceLayout.getStyle().set("margin", "0");

		dateNaissance.setReadOnly(true);
		naissanceLayout.add(dateNaissance);

		paysNaissance.setReadOnly(true);
		naissanceLayout.add(paysNaissance);

		communeNaissance.setReadOnly(true);
		naissanceLayout.add(communeNaissance);

		nationaliteNaissance.setReadOnly(true);
		naissanceLayout.add(nationaliteNaissance);

		nationaliteNaissance2.setReadOnly(true);
		naissanceLayout.add(nationaliteNaissance2);
		
		naissanceCard.add(naissanceLayout);

		CmpUtils.formatTextField(dateNaissance);

		CmpUtils.setLongTextField(paysNaissance);

		CmpUtils.setLongTextField(communeNaissance);

		CmpUtils.setModerateTextField(nationaliteNaissance);

		CmpUtils.setModerateTextField(nationaliteNaissance2);

	}


	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("etatcivil.title"));

		identiteCard.getTitre().setText(getTranslation("identite.titre"));
		nomFamille.setLabel(getTranslation("identite.nomfamille"));
		nomUsage.setLabel(getTranslation("identite.nomusage"));
		prenom.setLabel(getTranslation("identite.prenom"));
		prenom2.setLabel(getTranslation("identite.prenom2"));
		prenom3.setLabel(getTranslation("identite.prenom3"));

		naissanceCard.getTitre().setText(getTranslation("naissance.titre"));
		dateNaissance.setLabel(getTranslation("naissance.date"));
		paysNaissance.setLabel(getTranslation("naissance.pays"));
		communeNaissance.setLabel(getTranslation("naissance.commune"));
		nationaliteNaissance.setLabel(getTranslation("naissance.nationalite"));
		nationaliteNaissance2.setLabel(getTranslation("naissance.nationalite2"));
	}


	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

	//@Override
	protected void updateStyle() {
		identiteCard.updateStyle();
		identiteCard.addClassName("card-with-separator");
		naissanceCard.updateStyle();
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
	}

	/**
	 * Reset toutes les données affichées
	 * @param apprenant
	 */
	private void resetData() {
		nomFamille.setValue("");
		nomUsage.setValue("");
		prenom.setValue("");
		prenom2.setValue("");
		prenom3.setValue("");

		dateNaissance.setValue("");
		paysNaissance.setValue("");
		communeNaissance.setValue("");
		nationaliteNaissance.setValue("");
		nationaliteNaissance2.setValue("");
	}
	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	private void updateData(Apprenant apprenant) {
		resetData();
		if(apprenant != null) {
			// Mise à jour de l'état-civil
			CmpUtils.valueAndVisibleIfNotNull(nomFamille,apprenant.getEtatCivil().getNomDeNaissance());
			CmpUtils.valueAndVisibleIfNotNull(nomUsage,apprenant.getEtatCivil().getNomUsuel());
			CmpUtils.valueAndVisibleIfNotNull(prenom,apprenant.getEtatCivil().getPrenom());
			CmpUtils.valueAndVisibleIfNotNull(prenom2,apprenant.getEtatCivil().getDeuxiemePrenom());
			CmpUtils.valueAndVisibleIfNotNull(prenom3,apprenant.getEtatCivil().getTroisiemePrenom());

			// Mise à jour des données de naissance
			dateNaissance.setValue(Utils.formatStringDateToDisplay(apprenant.getNaissance().getDateDeNaissance()));
			paysNaissance.setValue(apprenant.getNaissance().getLibellePaysDeNaissance());
			if(apprenant.getNaissance().getPaysDeNaissance().equals(Utils.CODE_PAYS_FRANCE)) {
				CmpUtils.valueAndVisibleIfNotNull(communeNaissance,apprenant.getNaissance().getLibelleCommuneDeNaissance());
			} else {
				CmpUtils.valueAndVisibleIfNotNull(communeNaissance,apprenant.getNaissance().getCommuneDeNaissanceEtranger());
			}
			CmpUtils.valueAndVisibleIfNotNull(nationaliteNaissance,apprenant.getNaissance().getLibelleNationalite());
			CmpUtils.valueAndVisibleIfNotNull(nationaliteNaissance2,apprenant.getNaissance().getLibelleDeuxiemeNationalite());

		}
	}

}
