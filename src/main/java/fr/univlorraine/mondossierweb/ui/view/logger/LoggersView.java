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
package fr.univlorraine.mondossierweb.ui.view.logger;

import static fr.univlorraine.mondossierweb.utils.GridUtils.createFilterTextField;
import static fr.univlorraine.mondossierweb.utils.GridUtils.initGrids;
import static fr.univlorraine.mondossierweb.utils.GridUtils.localeChangeGrids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.grid.GridSortOrder;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.HeaderRow.HeaderCell;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.services.CookieService;
import fr.univlorraine.mondossierweb.services.LoggersService;
import fr.univlorraine.mondossierweb.services.LoggersService.LoggerLevel;
import fr.univlorraine.mondossierweb.services.LoggersService.LoggingEvent;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import lombok.Getter;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

/**
 * Vue des loggers.
 *
 * @author Matthieu Manginot
 */
@Secured(SecurityUtils.ROLE_SUPERADMIN)
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class LoggersView extends VerticalLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	@Autowired
	private transient CookieService cookieService;
	@Autowired
	private transient LoggersService loggersService;

	private final transient Collection<Disposable> disposables = new ArrayList<>();

	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final HorizontalLayout header = new HorizontalLayout();
	private final TextHeader headerTitle = new TextHeader();
	private final Label infoLabel = new Label();

	/* Grid */
	private Grid<LoggerLevel> gridLoggers = new Grid<>();
	private static final String MSG_PREFIX_COLUMN = "loggers.column.";
	private final TextField loggerNameHeaderTF = createFilterTextField("loggerNameHeaderTF");
	private final TextField configureLevelHeaderTF = createFilterTextField("configureLevelHeaderTF");
	private final TextField effectiveLevelHeaderTF = createFilterTextField("effectiveLevelHeaderTF");
	/* Liste des loggers */
	private transient List<LoggerLevel> listLoggers = new ArrayList<>();
	@Getter
	private ListDataProvider<LoggerLevel> loggersDataProvider = DataProvider.ofCollection(listLoggers);
	/* Logger sélectionné */
	@Getter
	private final transient ReplayProcessor<Set<LoggerLevel>> selectedLoggers = ReplayProcessor.cacheLastOrDefault(Collections.emptySet());
	/* Changement posible si logger sélectionné */
	@Getter
	private final transient Flux<Boolean> canChangeLevels = selectedLoggers.map(selection -> !selection.isEmpty());

	@PostConstruct
	public void init() {
		/* Initialise le dataProvider */
		refreshLoggers();

		/* Barre de titre */
		header.setAlignItems(Alignment.BASELINE);
		header.setWidthFull();
		header.setSpacing(false);
		header.add(headerTitle);

		setSizeFull();

		/* Buttons */
		final Div buttonsDiv = new Div();
		buttonsDiv.setWidthFull();
		loggersService.getAllLevels().stream().forEach(logLevel -> buttonsDiv.add(createLevelButton(logLevel)));
		add(buttonsDiv);
		getCanChangeLevels().subscribe(buttonsDiv::setEnabled);

		/* Info label */
		add(infoLabel);
		
		/* Grid */
		gridLoggers.setId("gridLoggers");
		gridLoggers.setDataProvider(loggersDataProvider);
		gridLoggers.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		/* Freeze selection column */
		((GridMultiSelectionModel<?>) gridLoggers.setSelectionMode(Grid.SelectionMode.MULTI)).setSelectionColumnFrozen(true);
		gridLoggers.addSelectionListener(event -> selectedLoggers.onNext(event.getAllSelectedItems()));
		selectedLoggers.subscribe(gridLoggers.asMultiSelect()::setValue);
		add(gridLoggers);

		/* Colonnes */
		Column<LoggerLevel> nameColumn = gridLoggers.addColumn(LoggerLevel::getLoggerName).setKey("loggerName").setHeader("").setWidth("15em").setFlexGrow(1);
		Column<LoggerLevel> configureLevelColumn = gridLoggers.addColumn(LoggerLevel::getConfiguredLevel).setKey("configuredLevel").setWidth("15em").setFlexGrow(0);
		Column<LoggerLevel> effectiveLevelColumn = gridLoggers.addColumn(LoggerLevel::getEffectiveLevel).setKey("effectiveLevel").setWidth("15em").setFlexGrow(0);
		/* Trie sur le nom par défaut */
		gridLoggers.sort(Arrays.asList(new GridSortOrder<>(nameColumn, SortDirection.ASCENDING)));

		/* Filtres */
		HeaderRow filterRow = gridLoggers.appendHeaderRow();
		/* Filtre logger name */
		final HeaderCell nameHeaderCell = filterRow.getCell(nameColumn);
		nameHeaderCell.setComponent(loggerNameHeaderTF);
		/* Filtre configure level */
		final HeaderCell configureLevelHeaderCell = filterRow.getCell(configureLevelColumn);
		configureLevelHeaderCell.setComponent(configureLevelHeaderTF);
		/* Filtre effective level */
		final HeaderCell effectiveLevelHeaderCell = filterRow.getCell(effectiveLevelColumn);
		effectiveLevelHeaderCell.setComponent(effectiveLevelHeaderTF);

		loggerNameHeaderTF.addValueChangeListener(this::onFilterChange);
		configureLevelHeaderTF.addValueChangeListener(this::onFilterChange);
		effectiveLevelHeaderTF.addValueChangeListener(this::onFilterChange);

		/* Init des grids */
		initGrids(true, true, true, gridLoggers);
		/* Cookies sur les grids */
		cookieService.cookies(gridLoggers);
	}

	/**
	 * Appelé quand le filtre des headers change.
	 *
	 * @param event
	 *            event
	 */
	private void onFilterChange(final ComponentValueChangeEvent<TextField, String> event) {
		getLoggersDataProvider().setFilter(logger -> {
			boolean loggerNameMatch = caseInsensitiveContains(logger.getLoggerName(), loggerNameHeaderTF.getValue());
			boolean configureLevelMatch = caseInsensitiveContains(logger.getConfiguredLevel(), configureLevelHeaderTF.getValue());
			boolean effectiveLevelMatch = caseInsensitiveContains(logger.getEffectiveLevel(), effectiveLevelHeaderTF.getValue());
			return loggerNameMatch && configureLevelMatch && effectiveLevelMatch;
		});
	}

	/**
	 * Création d'un bouton associé à un niveau de log.
	 *
	 * @param logLevel
	 *            logLevel
	 * @return Button
	 */
	private Button createLevelButton(final LogLevel logLevel) {
		String level = logLevel.name();
		Button button = new Button(VaadinIcon.TOOLS.create());
		button.setId(level.toLowerCase() + "Button");
		button.setText(level);
		button.getElement().getStyle().set("margin-right", "1em");
		button.addClickListener(event -> loggersService.setLoggerLevel(logLevel, selectedLoggers.blockFirst()));
		return button;
	}

	/** Rafraichit la liste des loggers. */
	private void refreshLoggers() {
		listLoggers.clear();
		listLoggers.addAll(loggersService.getAllLoggers());
		loggersDataProvider.refreshAll();
	}

	/**
	 * Case Insensitive Contains with two strings.
	 *
	 * @param where
	 *            contains what
	 * @param what
	 *            what to search
	 * @return true if contains without matching case
	 */
	private Boolean caseInsensitiveContains(final String where, final String what) {
		return where.toLowerCase().contains(what.toLowerCase());
	}

	/** @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent) */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("loggers.title"));
		infoLabel.setText(getTranslation("loggers.info"));
		localeChangeGrids(MSG_PREFIX_COLUMN, gridLoggers);
	}

	/**
	 * Set view title.
	 *
	 * @param viewTitle
	 *            viewTitle
	 */
	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		headerTitle.setText(viewTitle);
	}

	/** @see com.vaadin.flow.component.Component#onAttach(com.vaadin.flow.component.AttachEvent) */
	@Override
	protected void onAttach(final AttachEvent attachEvent) {
		super.onAttach(attachEvent);

		/* Surveille les LoggerLevels. */
		disposables.add(loggersService.getLoggerLevelEvents().subscribe(event -> attachEvent.getUI().access(() -> {
			/* Refresh */
			if (event.getAction().equals(LoggingEvent.Action.CHANGED)) {
				refreshLoggers();
			}
		})));
	}

	/** @see com.vaadin.flow.component.Component#onDetach(com.vaadin.flow.component.DetachEvent) */
	@Override
	protected void onDetach(final DetachEvent detachEvent) {
		disposables.forEach(Disposable::dispose);
		disposables.clear();

		super.onDetach(detachEvent);
	}
}
