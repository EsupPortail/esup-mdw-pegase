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
package fr.univlorraine.mondossierweb.ui.view.parametres;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.service.CurrentUiService;
import fr.univlorraine.mondossierweb.service.PreferencesService;
import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.PrefUtils;
import fr.univlorraine.mondossierweb.utils.ReactiveUtils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import lombok.Getter;

@Secured(SecurityUtils.ROLE_SUPERADMIN)
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class ParametresView extends Div implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	@Autowired
	private transient CurrentUiService currentUiService;
	@Autowired
	private transient PreferencesService prefService;
	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	
	@Value("${doc.url:}")
	private transient String docUrl;
	@Value("${help.url:}")
	private transient String helpUrl;

	@Value("${connexion.info.actif}")
	private transient boolean affichagePopupInfo;
	@Value("${connexion.info.pref}")
	private transient boolean popupInfoDesactivable;

	@Value("${etudiant.resume.actif}")
	private transient boolean affichageResumeEtudiant;
	
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	VerticalLayout parameterLayout = new VerticalLayout();
	private final Checkbox darkModeCB = new Checkbox();
	private final TextField docUrlTF = new TextField();
	private final TextField assistanceUrlTF = new TextField();
	
	private final Button buttonTN = new Button();


	@PostConstruct
	private void init() {
		getStyle().set("padding", "1em");
		getStyle().set("display", "flex");
		getStyle().set("flex-direction", "column");
		
		initMessageWIP();
		
		//initSetDarkMode();
		
		initBasicParameters();

	}
	
	private void initMessageWIP() {
		Label infoLabel = new Label("Vue en cours de développement");
		infoLabel.getStyle().set("margin", "auto");
		add(infoLabel);
	};
	
	private void initBasicParameters() {
		
		docUrlTF.setValue(docUrl);
		docUrlTF.setReadOnly(true);
		parameterLayout.add(docUrlTF);
		
		assistanceUrlTF.setValue(helpUrl);
		assistanceUrlTF.setReadOnly(true);
		parameterLayout.add(assistanceUrlTF);
		
		add(parameterLayout);
	}
	
	private void initSetDarkMode() {
		ReactiveUtils.subscribeWhenAttached(this,
			currentUiService.getDarkModeFlux().map(darkMode -> () -> darkModeCB.setValue(darkMode)));
		
		darkModeCB.addValueChangeListener(event -> {
			currentUiService.setDarkMode(event.getValue());
			
			securityService.getUsername().ifPresent(username -> {
				prefService.saveUserPref(username, PrefUtils.DARK_MODE, event.getValue());
			});
		});
		
		add(darkModeCB);
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("parametres.title"));

		docUrlTF.setLabel(getTranslation("parametres.doc-url"));
		assistanceUrlTF.setLabel(getTranslation("parametres.assistance-url"));
		darkModeCB.setLabel(getTranslation("parametres.dark-mode-label"));
		buttonTN.setText(getTranslation("parametres.button-test-notification"));
	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

}
