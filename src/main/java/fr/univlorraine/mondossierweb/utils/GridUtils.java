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
package fr.univlorraine.mondossierweb.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.springframework.util.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.HeaderRow.HeaderCell;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.VaadinService;

import reactor.core.publisher.ReplayProcessor;

/**
 * Grid utils class.
 *
 * @author Matthieu Manginot
 */
public final class GridUtils {

	public static final int COLUMN_SMALL_WIDTH = 150;

	private GridUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Création d'un header (champs texte).
	 *
	 * @param <T>
	 *            Type de l'élément dans la grid
	 * @param <F>
	 *            Type du filtre
	 * @param filterRow
	 *            HeaderRow des filtres de la grid
	 * @param column
	 *            colonne concernée
	 * @param filterValue
	 *            filtre en cours
	 * @param getter
	 *            getter de la valeur du filtre
	 * @param setter
	 *            setter de la valeur du filtre
	 * @param proc
	 *            processor
	 * @return HeaderCell
	 */
	public static <T, F> HeaderCell createHeader(final HeaderRow filterRow, final Column<T> column, final F filterValue, final Function<? super F, ? extends String> getter,
			final BiConsumer<? super F, String> setter, final ReplayProcessor<F> proc) {
		return createHeader(filterRow, column, filterValue, getter, setter, proc, "", "");
	}

	/**
	 * Création d'un header (champs texte).
	 *
	 * @param <T>
	 *            Type de l'élément dans la grid
	 * @param <F>
	 *            Type du filtre
	 * @param filterRow
	 *            HeaderRow des filtres de la grid
	 * @param column
	 *            colonne concernée
	 * @param filterValue
	 *            filtre en cours
	 * @param getter
	 *            getter de la valeur du filtre
	 * @param setter
	 *            setter de la valeur du filtre
	 * @param proc
	 *            processor
	 * @param placeholder
	 *            texte à positionner en placeholder du champs
	 * @param description
	 *            texte à positionner en description du champs
	 * @return HeaderCell
	 */
	public static <T, F> HeaderCell createHeader(final HeaderRow filterRow, final Column<T> column, final F filterValue, final Function<? super F, ? extends String> getter,
			final BiConsumer<? super F, String> setter, final ReplayProcessor<F> proc, final String placeholder, final String description) {
		final HeaderCell headerCell = filterRow.getCell(column);
		TextField headerTF = createFilterTextField("");
		headerTF.setPlaceholder(placeholder);
		headerTF.getElement().setAttribute("tooltip", description);
		proc.subscribe(c -> headerTF.setValue(getter.apply(filterValue)));
		headerTF.addValueChangeListener(event -> {
			setter.accept(filterValue, event.getValue());
			proc.onNext(filterValue);
		});
		headerCell.setComponent(headerTF);
		return headerCell;
	}

	/**
	 * Création d'un header pour champs date.
	 *
	 * @param <T>
	 *            Type de l'élément dans la grid
	 * @param <F>
	 *            Type du filtre
	 * @param filterRow
	 *            HeaderRow des filtres de la grid
	 * @param column
	 *            colonne concernée
	 * @param filterValue
	 *            filtre en cours
	 * @param getter
	 *            getter de la valeur du filtre
	 * @param setter
	 *            setter de la valeur du filtre
	 * @param proc
	 *            processor
	 * @return HeaderCell
	 */
	public static <T, F> HeaderCell createDateHeader(final HeaderRow filterRow, final Column<T> column, final F filterValue, final Function<? super F, ? extends LocalDate> getter,
			final BiConsumer<? super F, LocalDate> setter, final ReplayProcessor<F> proc) {
		final HeaderCell headerCell = filterRow.getCell(column);
		DatePicker headerDP = createFilterDatePicker("");
		proc.subscribe(c -> headerDP.setValue(getter.apply(filterValue)));
		headerDP.addValueChangeListener(event -> {
			setter.accept(filterValue, event.getValue());
			proc.onNext(filterValue);
		});
		headerCell.setComponent(headerDP);
		return headerCell;
	}

	/**
	 * Création d'un TextField.
	 *
	 * @param idField
	 *            id du composant
	 * @return TextField
	 */
	public static TextField createFilterTextField(final String idField) {
		final TextField tfFilter = new TextField();
		tfFilter.setId(idField);
		tfFilter.setWidthFull();
		tfFilter.setValueChangeMode(ValueChangeMode.EAGER);
		tfFilter.setClearButtonVisible(true);
		return tfFilter;
	}

	/**
	 * Création d'un DatePicker.
	 *
	 * @param idField
	 *            id du composant
	 * @return DatePicker
	 */
	public static DatePicker createFilterDatePicker(final String idField) {
		final DatePicker datFilter = new DatePicker();
		datFilter.setId(idField);
		datFilter.setWidthFull();
		datFilter.setClearButtonVisible(true);
		return datFilter;
	}

	/**
	 * Init des grids.
	 *
	 * @param sortable
	 *            if null unchanged
	 * @param resizable
	 *            if null unchanged
	 * @param reorderingAllowed
	 *            if null unchanged
	 * @param grids
	 *            grids to init
	 */
	public static void initGrids(final Boolean sortable, final Boolean resizable, final Boolean reorderingAllowed, final Grid<?>... grids) {
		Arrays.asList(grids).forEach(g -> {
			g.getColumns().forEach(c -> {
				/* Sort property */
				c.setSortProperty(c.getKey());
				/* Sortable */
				if (sortable != null) {
					c.setSortable(true);
				}
				/* Resizable */
				if (resizable != null) {
					c.setResizable(true);
				}
			});
			/* Toutes les colonnes sont déplacables */
			if (reorderingAllowed != null) {
				g.setColumnReorderingAllowed(true);
			}
		});
	}

	/**
	 * Init/Changement des messages en header.
	 *
	 * @param msgPrefix
	 *            msgPrefix
	 * @param grids
	 *            grids to init
	 */
	public static void localeChangeGrids(final String msgPrefix, final Grid<?>... grids) {
		Arrays.asList(grids).forEach(g -> g.getColumns().stream().filter(c -> StringUtils.hasText(c.getKey())).forEach(c ->
		/* Titre */
		c.setHeader(VaadinService.getCurrent().getInstantiator().getI18NProvider().getTranslation(msgPrefix + c.getKey(), null, Locale.getDefault()))));
	}

	/**
	 * Menu permettant de choisir la visibilité des colonnes pour les grids.
	 *
	 * @param msgPrefix
	 *            msgPrefix
	 * @param grids
	 *            grids to add the menu
	 */
	public static void addColumnsVisibilityMenu(final String msgPrefix, final Grid<?>... grids) {
		Arrays.asList(grids).forEach(g -> {
			Button columnsButton = new Button(VaadinIcon.COG_O.create());
			ContextMenu contextMenu = new ContextMenu(columnsButton);
			contextMenu.setOpenOnClick(true);
			for (Column<?> column : g.getColumns()) {
				Checkbox check = new Checkbox(VaadinService.getCurrent().getInstantiator().getI18NProvider().getTranslation(msgPrefix + column.getKey(), null, Locale.getDefault()));
				check.setValue(column.isVisible());
				check.addValueChangeListener(event -> column.setVisible(event.getValue()));
				contextMenu.add(check);
			}
			g.getElement().getParentNode().appendChild(columnsButton.getElement());
		});
	}
}
