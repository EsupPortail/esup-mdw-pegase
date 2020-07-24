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
package fr.univlorraine.mondossierweb.ui.view.recherche;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.model.ldap.entity.LdapPerson;
import fr.univlorraine.mondossierweb.service.RechercheEtudiantService;
import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.service.RechercheEtudiantService.RechercheEtudiantFilter;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Secured({SecurityUtils.ROLE_SUPERADMIN, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class RechercheView extends VerticalLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	private static int MIN_SEARCH = 2;

	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient RechercheEtudiantService rechercheEtudiantService;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();
	private ListDataProvider<LdapPerson> personDataProvider;

	private final Grid<LdapPerson> etuGrid = new Grid<LdapPerson>();

	/* Colonne nom */
	private final Column<LdapPerson> codeColumn = etuGrid.addColumn(LdapPerson::getCodeApprenant)
		.setSortProperty("codetu")
		.setFlexGrow(1)
		.setAutoWidth(true)
		.setFrozen(true);

	/* Colonne nom */
	private final Column<LdapPerson> nomColumn = etuGrid.addColumn(LdapPerson::getDisplayName)
		.setSortProperty("displayName")
		.setFlexGrow(1)
		.setAutoWidth(true)
		.setFrozen(true);

	/* Colonne mail */
	private final Column<LdapPerson> mailColumn = etuGrid.addColumn(LdapPerson::getMail)
		.setSortProperty("mail")
		.setFlexGrow(1)
		.setAutoWidth(true)
		.setFrozen(true);

	// Layout contenant les critères de sélection
	private final HorizontalLayout selectorLayout = new HorizontalLayout();

	// Champ de recherche
	private final TextField filterTf = new TextField();


	@PostConstruct
	private void init() {
		setSizeFull();

		initRecherche();
		initGrid();
	}


	private void initRecherche() {
		// Ajout du champ de recherche par nom
		filterTf.setClearButtonVisible(true);
		filterTf.setWidthFull();
		filterTf.setMaxWidth("25em");
		filterTf.getStyle().set("margin", "auto");
		filterTf.setValue(securityService.getSearch().orElse(""));
		filterTf.addValueChangeListener(e -> refresh());
		selectorLayout.setMinHeight("4em");
		selectorLayout.setWidthFull();
		selectorLayout.add(filterTf);

		add(selectorLayout);
	}

	private void initGrid() {
		/*etuGrid.setHeightByRows(false);
		etuGrid.setHeightFull();
		etuGrid.setWidthFull();*/
		etuGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		etuGrid.setSelectionMode(SelectionMode.SINGLE);

		//Si on a une recherche enregistrée
		if(securityService.getResultatRecherche()!=null) {
			etuGrid.setItems(securityService.getResultatRecherche());
		} else {
			etuGrid.setItems(rechercheEtudiantService.getHistorique());
		}

		GridContextMenu<LdapPerson> gridMenu = etuGrid.addContextMenu();

		gridMenu.setOpenOnClick(true);
		gridMenu.addItem(getTranslation("go.etudiant.etatcivil"), e -> {
			if(e!=null && e.getItem().isPresent()) {
				log.info("acces dossier {}", e.getItem().get().getCodeApprenant());
				rechercheEtudiantService.accesDossier(e.getItem().get());
			} else {
				Notification.show(getTranslation("go.etudiant.error"));
			}
		});

		add(etuGrid);
	}


	/**
	 * Rafraichi les user
	 */
	private void refresh() {
		securityService.setResultatRecherche(null);
		if(StringUtils.isBlank(filterTf.getValue())) {
			securityService.saveSearch(null);
			etuGrid.setItems(rechercheEtudiantService.getHistorique());
		} else {
			if (filterTf.getValue().length() < MIN_SEARCH) {
				filterTf.setInvalid(true);
				filterTf.setErrorMessage(getTranslation("recherche.search.error", new Object[] { MIN_SEARCH }));
				return;
			} else {
				filterTf.setInvalid(false);
			}
			personDataProvider = rechercheEtudiantService.createLdapPersonDataProvider(getFilter());
			etuGrid.setDataProvider(personDataProvider);
			securityService.setResultatRecherche(personDataProvider.getItems());
		}
	}

	private RechercheEtudiantFilter getFilter() {
		securityService.saveSearch(filterTf.getValue());
		return new RechercheEtudiantFilter(filterTf.getValue());
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("recherche.title"));

		// Grid résultat
		codeColumn.setHeader(getTranslation("search.etudiant.id"));
		nomColumn.setHeader(getTranslation("search.etudiant.nom"));
		mailColumn.setHeader(getTranslation("search.etudiant.mail"));
	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

}
