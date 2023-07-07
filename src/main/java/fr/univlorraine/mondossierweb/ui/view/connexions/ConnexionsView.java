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
package fr.univlorraine.mondossierweb.ui.view.connexions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.services.UiService;
import fr.univlorraine.mondossierweb.services.UiService.UiInfo;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.ReactiveUtils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import jakarta.annotation.PostConstruct;
import lombok.Getter;

@Secured(SecurityUtils.ROLE_SUPERADMIN)
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class ConnexionsView extends Grid<UiInfo> implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	@Autowired
	private transient UiService uiService;

	private final SerializableFunction<UiInfo, String> currentUIClassNameGenerator =
		uiInfo -> getUI().map(System::identityHashCode)
			.filter(uiId -> uiId == uiInfo.getId())
			.map(uiId -> "row-highlighted")
			.orElse(null);

	private final Column<UiInfo> usernameColumn = addColumn(UiInfo::getUsername)
		.setSortable(true)
		.setWidth("8rem")
		.setFlexGrow(0)
		.setClassNameGenerator(currentUIClassNameGenerator);
	private final Column<UiInfo> ipColumn = addColumn(UiInfo::getIp)
		.setSortable(true)
		.setTextAlign(ColumnTextAlign.END)
		.setWidth("9rem")
		.setFlexGrow(0)
		.setClassNameGenerator(currentUIClassNameGenerator);
	private final Column<UiInfo> browserColumn = addColumn(UiInfo::getBrowser)
		.setSortable(true)
		.setWidth("8rem")
		.setFlexGrow(0)
		.setClassNameGenerator(currentUIClassNameGenerator);
	private final Column<UiInfo> locationColumn = addColumn(UiInfo::getLocation)
		.setSortable(true)
		.setFlexGrow(1)
		.setClassNameGenerator(currentUIClassNameGenerator);

	@PostConstruct
	private void init() {
		addThemeVariants(GridVariant.LUMO_NO_BORDER);
		setSizeFull();
		setSelectionMode(SelectionMode.NONE);

		ReactiveUtils.subscribeWhenAttached(this,
			uiService.getUiInfosFlux().map(uiInfos -> () -> {
				setItems(uiInfos);
				setViewTitle(getTranslation("connexions.header", uiInfos.size()));
			}));
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("connexions.header", uiService.getUiInfos().size()));

		usernameColumn.setHeader(getTranslation("connexions.column.username"));
		ipColumn.setHeader(getTranslation("connexions.column.ip"));
		browserColumn.setHeader(getTranslation("connexions.column.browser"));
		locationColumn.setHeader(getTranslation("connexions.column.location"));
	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

}
