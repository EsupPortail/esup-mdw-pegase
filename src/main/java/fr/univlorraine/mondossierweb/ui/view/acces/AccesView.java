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
package fr.univlorraine.mondossierweb.ui.view.acces;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
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
@SuppressWarnings("serial")
@Slf4j
public class AccesView extends HasCodeApprenantUrlParameterView implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	@Getter
	private final TextHeader header = new TextHeader();
	// label d'erreur
	private final NativeLabel errorLabel = new NativeLabel();
	private final Card bacCard = new Card(VaadinIcon.DIPLOMA_SCROLL.create(),"", false);
	private final Card anneesCard = new Card(VaadinIcon.INSTITUTION.create(),"", false);
	private final VerticalLayout parcoursLayout = new VerticalLayout(bacCard, anneesCard);
	private final TextLabel anneeBac=new TextLabel();
	private final TextLabel typeBac=new TextLabel();
	private final TextLabel mentionBac=new TextLabel();
	private final TextLabel paysEtbBac=new TextLabel();
	private final TextLabel departementEtbBac=new TextLabel();
	private final TextLabel codeIneBac=new TextLabel();
	private final TextLabel anneeSupFr=new TextLabel();
	private final TextLabel anneeUnivFr=new TextLabel();
	private final TextLabel anneeEtablissement=new TextLabel();
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
		initBac();
		initAnnees();
		parcoursLayout.setWidthFull();
		parcoursLayout.getStyle().set("max-width", "52em");
		parcoursLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
		add(parcoursLayout);
		updateStyle();
	}

	private void initBac() {
		FormLayout bacLayout = new FormLayout();
		bacLayout.getStyle().set(CssUtils.MARGIN, "0");
		bacCard.add(bacLayout);

		bacLayout.add(codeIneBac);
		bacLayout.add(anneeBac);
		bacLayout.add(typeBac);
		bacLayout.add(mentionBac);
		bacLayout.add(paysEtbBac);
		bacLayout.add(departementEtbBac);

		CmpUtils.formatTextLabel(anneeBac);

		CmpUtils.setModerateTextLabel(typeBac);

		CmpUtils.formatTextLabel(mentionBac);

		CmpUtils.setModerateTextLabel(paysEtbBac);

		CmpUtils.setModerateTextLabel(departementEtbBac);

		CmpUtils.setModerateTextLabel(codeIneBac);

	}

	private void initAnnees() {

		FormLayout anneesLayout = new FormLayout();
		anneesLayout.getStyle().set(CssUtils.MARGIN, "0");
		anneesCard.add(anneesLayout);
		
		anneesLayout.add(anneeSupFr);
		anneesLayout.add(anneeUnivFr);
		anneesLayout.add(anneeEtablissement);

		CmpUtils.setModerateTextLabel(anneeSupFr);

		CmpUtils.formatTextLabel(anneeUnivFr);

		CmpUtils.formatTextLabel(anneeEtablissement);

	}




	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("acces.title"));

		errorLabel.setText(getTranslation("error.unknown"));
		
		bacCard.getTitre().setText(getTranslation("bac.titre"));
		anneeBac.setLabel(getTranslation("bac.annee"));
		typeBac.setLabel(getTranslation("bac.type"));
		mentionBac.setLabel(getTranslation("bac.mention"));
		paysEtbBac.setLabel(getTranslation("bac.pays"));
		departementEtbBac.setLabel(getTranslation("bac.departement"));
		codeIneBac.setLabel(getTranslation("bac.codeine"));

		anneesCard.getTitre().setText(getTranslation("annees.titre"));
		anneeSupFr.setLabel(getTranslation("annees.anneesupfr"));
		anneeUnivFr.setLabel(getTranslation("annees.anneeunivfr"));
		anneeEtablissement.setLabel(getTranslation("annee.anneetablissement"));
	}


	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

	//@Override
	protected void updateStyle() {
		bacCard.updateStyle();
		bacCard.addClassName("card-with-separator");
		anneesCard.updateStyle();
	}


	/**
	 * Reset toutes les données affichées
	 * @param apprenant
	 */
	private void resetData() {
		anneeBac.setValue("");
		typeBac.setValue("");
		mentionBac.setValue("");
		paysEtbBac.setValue("");
		departementEtbBac.setValue("");

		codeIneBac.setValue("");
		anneeSupFr.setValue("");
		anneeUnivFr.setValue("");
		anneeEtablissement.setValue("");
	}
	@Override
	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	public void updateData(ApprenantEtInscriptions dossier) {
		resetData();
		if(dossier == null || dossier.getApprenant() == null ) {
			this.removeAll();
			add(errorLabel);
		} else {
			Apprenant apprenant = dossier.getApprenant();
			// Mise à jour des infos sur le bac
			CmpUtils.valueAndVisibleIfNotNull(anneeBac,apprenant.getBac().getAnneeObtention());
			CmpUtils.valueAndVisibleIfNotNull(typeBac,apprenant.getBac().getLibelleSerie());
			CmpUtils.valueAndVisibleIfNotNull(mentionBac,apprenant.getBac().getLibelleMention());
			CmpUtils.valueAndVisibleIfNotNull(paysEtbBac,apprenant.getBac().getLibellePays());
			if(apprenant.getBac()!=null && apprenant.getBac().getPays()!=null && apprenant.getBac().getPays().equals(Utils.CODE_PAYS_FRANCE)) {
				CmpUtils.valueAndVisibleIfNotNull(departementEtbBac,apprenant.getBac().getLibelleDepartement());
			} else {
				CmpUtils.valueAndVisibleIfNotNull(departementEtbBac,null);
			}
			CmpUtils.valueAndVisibleIfNotNull(codeIneBac,apprenant.getBac().getIne());
			CmpUtils.valueAndVisibleIfNotNull(anneeSupFr,apprenant.getPremieresInscriptions().getAnneeEnseignementSuperieur());
			CmpUtils.valueAndVisibleIfNotNull(anneeUnivFr,apprenant.getPremieresInscriptions().getAnneeUniversite());
			CmpUtils.valueAndVisibleIfNotNull(anneeEtablissement,apprenant.getPremieresInscriptions().getAnneeEtablissement());
			
		}
	}


}
