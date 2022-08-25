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
package fr.univlorraine.mondossierweb.ui.view.apropos;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import lombok.Getter;

@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class AProposView extends Div implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient BuildProperties buildProperties;

	private final TextField username = new TextField();
	private final Paragraph message = new Paragraph();
	private final TextField roles = new TextField();
	private final Span info = new Span();
	
	@PostConstruct
	private void init() {
		getStyle().set(CSSColorUtils.PADDING, "1em");

		initAppInfo();
		initMessageInfo();
		initUserInfo();
	}

	private void initAppInfo() {
		H2 nameComp = new H2(buildProperties.getName());
		nameComp.getStyle().set("display", "inline");
		add(nameComp);

		add(new Span(" v" + buildProperties.getVersion()));

		Element buildTimeElement = new Element("small");
		ZonedDateTime buildTime = buildProperties.getTime().atZone(ZoneId.systemDefault());
		buildTimeElement.setText(' ' + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(buildTime));
		buildTimeElement.getStyle().set(CSSColorUtils.COLOR, "var(--lumo-secondary-text-color)");
		getElement().appendChild(buildTimeElement);

		Paragraph descriptionComp = new Paragraph(buildProperties.get("description"));
		descriptionComp.getStyle().set(CSSColorUtils.FONT_STYLE, CSSColorUtils.ITALIC);
		add(descriptionComp);

	}
	
	private void initMessageInfo() {
		add(info);
		
		add(message);
		message.getStyle().set(CSSColorUtils.PADDING, "1em");
		message.getStyle().set(CSSColorUtils.BACKGROUND_COLOR, "var(--lumo-contrast-5pct)");
		message.getStyle().set(CSSColorUtils.BORDER_RADIUS, "1em");
		
		
	}

	private void initUserInfo() {

		securityService.getUsername().ifPresent(username::setValue);
		username.setReadOnly(true);

		FormLayout userForm = new FormLayout(username);
		add(userForm);
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("apropos.title"));

		username.setLabel(getTranslation("apropos.field.username"));
		roles.setLabel(getTranslation("apropos.field.roles"));
		message.setText(getTranslation("apropos.message"));
		info.getElement().setProperty("innerHTML", getTranslation("connexion.info"));
	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

}
