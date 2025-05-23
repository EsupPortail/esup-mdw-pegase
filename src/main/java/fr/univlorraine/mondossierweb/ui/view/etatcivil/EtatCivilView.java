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
package fr.univlorraine.mondossierweb.ui.view.etatcivil;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.component.TextLabel;
import fr.univlorraine.mondossierweb.ui.layout.HasCodeApprenantUrlParameterView;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.CssUtils;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import fr.univlorraine.pegase.insext.model.Apprenant;
import fr.univlorraine.pegase.insext.model.ApprenantEtInscriptions;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_GESTIONNAIRE})
@Route(layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class EtatCivilView extends HasCodeApprenantUrlParameterView implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	@Getter
	private final TextHeader header = new TextHeader();
	// label d'erreur
	private final NativeLabel errorLabel = new NativeLabel();
	private final Card identiteCard = new Card(VaadinIcon.USER.create(),"", false);
	private final Card naissanceCard = new Card(VaadinIcon.GLOBE.create(),"", false);
	private final VerticalLayout etatcivilLayout = new VerticalLayout(identiteCard, naissanceCard);
	private final TextLabel nomFamille=new TextLabel();
	private final TextLabel nomUsage=new TextLabel();
	private final TextLabel prenom=new TextLabel();
	private final TextLabel prenom2=new TextLabel();
	private final TextLabel prenom3=new TextLabel();
	private final TextLabel dateNaissance=new TextLabel();
	private final TextLabel paysNaissance=new TextLabel();
	private final TextLabel communeNaissance=new TextLabel();
	private final TextLabel nationaliteNaissance=new TextLabel();
	private final TextLabel nationaliteNaissance2=new TextLabel();
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";

	@PostConstruct
	private void init() {
		setSizeFull();
		addClassName("view");
		getStyle().set("padding","0");
		errorLabel.getStyle().set("padding", "1em");
		initIdentite();
		initNaissance();
		etatcivilLayout.setWidthFull();
		etatcivilLayout.getStyle().set("max-width", "52em");
		etatcivilLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
		add(etatcivilLayout);
		updateStyle();
	}

	private void initIdentite() {

		FormLayout identiteLayout = new FormLayout();
		identiteLayout.getStyle().set(CssUtils.MARGIN, "0");
		
		identiteLayout.add(nomFamille);
		identiteLayout.add(nomUsage);
		identiteLayout.add(prenom);
		identiteLayout.add(prenom2);
		identiteLayout.add(prenom3);
		
		identiteCard.add(identiteLayout);

		CmpUtils.setModerateTextLabel(nomFamille);

		CmpUtils.setModerateTextLabel(nomUsage);

		CmpUtils.setModerateTextLabel(prenom);

		CmpUtils.setModerateTextLabel(prenom2);

		CmpUtils.setModerateTextLabel(prenom3);

	}

	private void initNaissance() {
		FormLayout naissanceLayout = new FormLayout();
		naissanceLayout.addClassName("card-layout");
		naissanceLayout.getStyle().set(CssUtils.MARGIN, "0");

		naissanceLayout.add(dateNaissance);
		naissanceLayout.add(paysNaissance);
		naissanceLayout.add(communeNaissance);
		naissanceLayout.add(nationaliteNaissance);
		naissanceLayout.add(nationaliteNaissance2);
		
		naissanceCard.add(naissanceLayout);

		CmpUtils.formatTextLabel(dateNaissance);

		CmpUtils.setModerateTextLabel(paysNaissance);

		CmpUtils.setModerateTextLabel(communeNaissance);

		CmpUtils.setModerateTextLabel(nationaliteNaissance);

		CmpUtils.setModerateTextLabel(nationaliteNaissance2);

	}


	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("etatcivil.title"));

		errorLabel.setText(getTranslation("error.unknown"));
		
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

	protected void updateStyle() {
		identiteCard.updateStyle();
		identiteCard.addClassName("card-with-separator");
		naissanceCard.updateStyle();
	}

	/**
	 * Reset toutes les données affichées
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
	@Override
	/**
	 * Mise à jour des données affichées
	 * @param dossier
	 */
	public void updateData(ApprenantEtInscriptions dossier) {
		resetData();
		if(dossier == null || dossier.getApprenant() == null ) {
			this.removeAll();
			add(errorLabel);
		} else {
			Apprenant apprenant = dossier.getApprenant();
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
