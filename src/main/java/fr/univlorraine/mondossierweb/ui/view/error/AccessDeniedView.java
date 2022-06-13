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
package fr.univlorraine.mondossierweb.ui.view.error;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import lombok.Getter;

@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class AccessDeniedView extends VerticalLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	@Autowired
	private transient ConfigController configController;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private final Label label = new Label();

	//@Value("${help.url:}")
	private transient String helpUrl;
	private final Label helpLabel = new Label();
	private final Button helpButton = new Button(new Icon(VaadinIcon.LIFEBUOY));

	@PostConstruct
	public void init() {
		helpUrl = configController.getHelpUrl();
		add(label);

		if (!helpUrl.isBlank()) {
			add(new Div(helpLabel, makeHelpLink()));
		}
	}

	private Anchor makeHelpLink() {
		helpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		Anchor helpLink = new Anchor(helpUrl, helpButton);
		helpLink.setTarget("_blank");
		/* cf. https://stackoverflow.com/a/17711167/2477444 */
		helpLink.getElement().setAttribute("rel", "noopener noreferrer");
		return helpLink;
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("error.accessdenied"));

		label.setText(getTranslation("error.accessdenied.detail"));
		helpLabel.setText(getTranslation("error.accessdenied.help"));
		helpButton.setText(getTranslation("menu.help"));
	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

}
