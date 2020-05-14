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
import org.springframework.util.StringUtils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.WrapMode;
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

import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.AdaptSizeLayout;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.layout.HasCodeEtuUrlParameterView;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class EtatCivilView extends AdaptSizeLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver, HasUrlParameter<String> {

	@Autowired
	private transient SecurityService securityService;
	
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private final VerticalLayout identiteLayout = new VerticalLayout();
	private final VerticalLayout naissanceLayout = new VerticalLayout();
	private final FlexLayout etatcivilLayout = new FlexLayout(identiteLayout, naissanceLayout);
	

	private final TextHeader titreIdentite = new TextHeader();
	private final TextHeader titreNaissance = new TextHeader();
	
	private final TextField sexe=new TextField();
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
		add(etatcivilLayout);
	}

	private void initIdentite() {
		
		//formatCard(identiteLayout);

		identiteLayout.add(titreIdentite);
		
		sexe.setReadOnly(true);
		identiteLayout.add(sexe);
		
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

		//identiteLayout.addClassName("mdw-card");
		formatTextField(sexe);
		
		setLongTextField(nomFamille);
		
		setLongTextField(nomUsage);
		
		setModerateTextField(prenom);
		
		setModerateTextField(prenom2);
		
		setModerateTextField(prenom3);
		
	}
	
	private void initNaissance() {
		
		//formatCard(naissanceLayout);

		naissanceLayout.add(titreNaissance);
		
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
		
		//naissanceLayout.addClassName("mdw-card");
		
		formatTextField(dateNaissance);
		
		setLongTextField(paysNaissance);
		
		setLongTextField(communeNaissance);
		
		setModerateTextField(nationaliteNaissance);
		
		setModerateTextField(nationaliteNaissance2);
		
	}
	
	private void formatTextField(TextField tf) {
		tf.getStyle().set("margin", "0em");
	}
	
	private void setLongTextField(TextField tf) {
		formatTextField(tf);
		tf.setWidthFull();
		tf.setMaxWidth("25em");
	}
	
	private void setModerateTextField(TextField tf) {
		formatTextField(tf);
		tf.setWidthFull();
		tf.setMaxWidth("20em");
	}

	private void formatCard(VerticalLayout card, boolean isMobile) {
		//card.getStyle().set("box-shadow", "0 5px 15px rgba(101,101,102,.15)");
		card.getStyle().set("margin", "1em");
		card.getStyle().set("border-radius", "4px");
		card.getStyle().set("border", "0.1em solid");
		card.getStyle().set("border-color", "var(--lumo-contrast-5pct)");
		
		card.setWidth(isMobile ? "100%" :"50%");
		
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("etatcivil.title"));

		titreIdentite.setText(getTranslation("identite.titre"));
		sexe.setLabel(getTranslation("identite.sexe"));
		nomFamille.setLabel(getTranslation("identite.nomfamille"));
		nomUsage.setLabel(getTranslation("identite.nomusage"));
		prenom.setLabel(getTranslation("identite.prenom"));
		prenom2.setLabel(getTranslation("identite.prenom2"));
		prenom3.setLabel(getTranslation("identite.prenom3"));
		
		titreNaissance.setText(getTranslation("naissance.titre"));
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

	@Override
	protected void adaptSize(final Boolean isMobile) {
		etatcivilLayout.setWrapMode(isMobile? WrapMode.WRAP : WrapMode.NOWRAP);
		formatCard(identiteLayout, isMobile);
		formatCard(naissanceLayout, isMobile);
	}
	
	@Override
	public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String codetu) {
		if(StringUtils.hasText(codetu)) {
			// Si l'utilisateur est autorisé à accéder au dossier en paramètre
			if(securityService.isAccessGrantedForStudent(codetu)) {
				securityService.setDossierConsulte(codetu);
				log.info(getTranslation("action.acces.dossier", codetu));
			} else {
				Notification.show(getTranslation("error.accesdossierrefuse"));
			}
		}
	}

}
