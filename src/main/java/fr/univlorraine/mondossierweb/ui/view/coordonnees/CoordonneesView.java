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
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.WrapMode;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.ui.component.AdaptSizeLayout;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.layout.HasCodeEtuUrlParameterView;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import lombok.Getter;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class CoordonneesView extends AdaptSizeLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
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
	
	private final FlexLayout coordLayout = new FlexLayout(mailLayout, telLayout,mailSecoursLayout,telSecoursLayout, adresseFixeLayout, adresseAnnuelleLayout);
	
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
	
	@PostConstruct
	private void init() {
		setSizeFull();
		initMail();
		initTel();
		initMailSecours();
		initTelSecours();
		initAdresseFixe();
		initAdresseAnnuelle();
		coordLayout.setWidthFull();
		add(coordLayout);

	}

	private void initMail() {
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
		
	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

	@Override
	protected void adaptSize(final Boolean isMobile) {
		coordLayout.setWrapMode(WrapMode.WRAP);
		telLayout.updateStyle(isMobile);
		mailLayout.updateStyle(isMobile);
		telSecoursLayout.updateStyle(isMobile);
		mailSecoursLayout.updateStyle(isMobile);
		adresseFixeLayout.updateStyle(isMobile);
		adresseAnnuelleLayout.updateStyle(isMobile);
	}
	

}
