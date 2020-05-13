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
package fr.univlorraine.mondossierweb.ui.layout;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.UIScope;

import fr.univlorraine.mondossierweb.service.CurrentUiService;
import fr.univlorraine.mondossierweb.utils.ReactiveUtils;

@Component
@UIScope
@SuppressWarnings("serial")
public class AppTitle extends HorizontalLayout implements LocaleChangeObserver {

	@Value("${logo.light.src}")
	private transient String srcLogo;
	@Value("${logo.dark.src}")
	private transient String srcLogoDark;

	@Autowired
	private transient CurrentUiService currentUiService;
	@Autowired
	private transient BuildProperties buildProperties;

	private final Image logo = new Image();

	@PostConstruct
	private void init() {
		setAlignItems(Alignment.END);
		getStyle().set("margin", "0.75rem 0.75rem 0.75rem 1.5rem");

		ReactiveUtils.subscribeWhenAttached(this,
			currentUiService.getDarkModeFlux()
				.map(darkMode -> darkMode ? srcLogoDark : srcLogo)
				.map(logoSrc -> () -> logo.setSrc(logoSrc)));
		add(logo);

		Div appNameTitle = new Div(new Text(buildProperties.getName()));
		appNameTitle.getElement().getStyle().set("font-size", "var(--lumo-font-size-xl)");
		add(appNameTitle);
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		logo.setAlt(getTranslation("menu.alt-logo"));
	}

}
