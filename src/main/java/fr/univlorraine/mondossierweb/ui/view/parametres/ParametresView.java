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
package fr.univlorraine.mondossierweb.ui.view.parametres;

import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
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
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.PrefUtils;
import fr.univlorraine.mondossierweb.utils.ReactiveUtils;
import lombok.Getter;

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
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	
	private final Checkbox darkModeCB = new Checkbox();
	
	private final TextField colorTF = new TextField();
	
	private final Button buttonTN = new Button();


	@PostConstruct
	private void init() {
		getStyle().set("padding", "1em");
		
		//initSetBaseColor();
		initSetDarkMode();
		//initTestNotif();

	}

	private void initSetBaseColor() {
		/* Pour changer la couleur de base du theme, injecter le bean
		 * currentUIService et utiliser sa méthode setAppColor([couleur CSS]).
		 *
		 * Pour changer la couleur d'une vue seulement, y ajouter un composant
		 * AppColorStyle, ex: add(new AppColorStyle("rgb(211, 47, 47)")); */
		ReactiveUtils.subscribeWhenAttached(this,
			currentUiService.getAppColorFlux().map(appColor -> () -> colorTF.setValue(appColor)));
		colorTF.addValueChangeListener(event -> {
			String color = event.getValue();
			if (CSSColorUtils.isSupportedColor(color)) {
				colorTF.setInvalid(false);
				currentUiService.setAppColor(color);
			} else {
				colorTF.setInvalid(true);
				colorTF.setErrorMessage(getTranslation("parametres.error.invalid-color"));
			}
		});
		colorTF.setValueChangeMode(ValueChangeMode.EAGER);
		add(colorTF);
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

	private void initTestNotif() {
		buttonTN.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		buttonTN.addClickListener(event -> notifyClicked());
		add(buttonTN);
	}

	private void notifyClicked() {
		Notification.show(getTranslation("parametres.clicked", LocalTime.now()));
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("parametres.title"));

		colorTF.setLabel(getTranslation("parametres.color-label"));
		darkModeCB.setLabel(getTranslation("parametres.dark-mode-label"));
		buttonTN.setText(getTranslation("parametres.button-test-notification"));
	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

}
