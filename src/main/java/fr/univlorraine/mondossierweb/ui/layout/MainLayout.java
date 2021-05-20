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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.shared.ui.Transport;

import fr.univlorraine.mondossierweb.config.SecurityConfig;
import fr.univlorraine.mondossierweb.controllers.MainController;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateur;
import fr.univlorraine.mondossierweb.model.app.entity.Utilisateur;
import fr.univlorraine.mondossierweb.service.CurrentUiService;
import fr.univlorraine.mondossierweb.service.PreferencesService;
import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.AppColorStyle;
import fr.univlorraine.mondossierweb.ui.view.apropos.AProposView;
import fr.univlorraine.mondossierweb.ui.view.connexions.ConnexionsView;
import fr.univlorraine.mondossierweb.ui.view.coordonnees.CoordonneesView;
import fr.univlorraine.mondossierweb.ui.view.etatcivil.EtatCivilView;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.InscriptionsView;
import fr.univlorraine.mondossierweb.ui.view.parcours.ParcoursView;
import fr.univlorraine.mondossierweb.ui.view.recherche.RechercheView;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.PrefUtils;
import fr.univlorraine.mondossierweb.utils.ReactiveUtils;
import fr.univlorraine.pegase.model.insgestion.Apprenant;
import lombok.extern.slf4j.Slf4j;

@Push(transport = Transport.WEBSOCKET_XHR)
@JsModule("./src/set-dark-mode.js")
@JsModule("./src/font-open-sans.js")
@CssImport(value = "./styles/mdw.css")
@CssImport(value = "./styles/lumo-font-family.css")
@CssImport(value = "./styles/vaadin-app-layout.css", themeFor = "vaadin-app-layout")
@CssImport(value = "./styles/vaadin-button-pointer.css", themeFor = "vaadin-button")
@CssImport(value = "./styles/vaadin-button-pointer.css", themeFor = "vaadin-drawer-toggle")
@CssImport(value = "./styles/vaadin-button-pointer.css", themeFor = "vaadin-menu-bar-button")
@CssImport(value = "./styles/vaadin-menu-bar-user.css", themeFor = "vaadin-menu-bar")
@CssImport(value = "./styles/vaadin-context-menu-list-box-pointer.css", themeFor = "vaadin-context-menu-list-box")
@CssImport(value = "./styles/vaadin-checkbox-pointer.css", themeFor = "vaadin-checkbox")
@CssImport(value = "./styles/vaadin-text-field-pointer.css", themeFor = "vaadin-text-field")
@CssImport(value = "./styles/vaadin-grid.css", themeFor = "vaadin-grid")
@CssImport(value = "./styles/vaadin-tab.css", themeFor = "vaadin-tab")
@CssImport(value = "./styles/vaadin-drawer-toggle.css", themeFor = "vaadin-drawer-toggle")
@SuppressWarnings("serial")
@Slf4j
public class MainLayout extends AppLayout implements PageConfigurator, BeforeEnterObserver, LocaleChangeObserver {

	@Autowired
	private transient CurrentUiService currentUiService;
	@Autowired
	private transient AppTitle appTitle;
	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient PreferencesService prefService;
	@Autowired
	private transient MainController mainController;

	@Value("${doc.url:}")
	private transient String docUrl;
	@Value("${help.url:}")
	private transient String helpUrl;

	@Value("${connexion.info.actif}")
	private transient boolean affichagePopupInfo;
	
	@Value("${etudiant.resume.actif}")
	private transient boolean affichageResumeEtudiant;

	private final Tabs tabs = new Tabs();
	private final Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();

	private final Div navBarHeader = new Div();
	private MenuItem userMenuAproposItem;
	//private MenuItem userMenuEtatCivilItem;
	//private MenuItem userMenuCoordonneesItem;
	private MenuItem userMenuParametresItem;
	private MenuItem userMenuLogoutItem;
	private Label nomPrenom = new Label();
	private Label numeroDossier = new Label();

	@PostConstruct
	public void init() {

		/* Theme: Mode sombre */
		ReactiveUtils.subscribeWhenAttached(this,
			currentUiService.getDarkModeFlux()
			.map(darkMode -> () -> getElement().executeJs("setDarkMode($0)", darkMode)));

		/* Theme: Couleur principale */
		AppColorStyle appColorStyle = new AppColorStyle();
		ReactiveUtils.subscribeWhenAttached(this,
			currentUiService.getAppColorFlux()
			.map(appColor -> () -> appColorStyle.setColor(appColor)));
		getElement().appendChild(appColorStyle.getElement());

		/* Menu au-dessus de la barre d'application */
		setPrimarySection(Section.DRAWER);

		/* Titre du menu */
		addToDrawer(appTitle);

		/* Nom, prénom et code apprenant*/
		if(affichageResumeEtudiant) {
			addToDrawer(getResumeLayout());
		}
		
		/* Menu */
		tabs.getStyle().set("max-width", "16em");
		tabs.getStyle().set("margin-left", "auto");
		tabs.getStyle().set("box-shadow", "none");
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		tabs.addSelectedChangeListener(event -> {
			/* Seules les actions de navigation doivent pouvoir changer la tab sélectionnée. */
			if (event.isFromClient()) {
				tabs.setSelectedTab(event.getPreviousTab());
			}
		});
		addDrawerRouterLink(VaadinIcon.SEARCH, "recherche.title", RechercheView.class);
		addDrawerRouterLink(VaadinIcon.USER_CARD, "etatcivil.title", EtatCivilView.class);
		addDrawerRouterLink(VaadinIcon.HOME, "coordonnees.title", CoordonneesView.class);
		//addDrawerRouterLink(VaadinIcon.ROAD_BRANCHES, "parcours.title", ParcoursView.class);
		addDrawerRouterLink(VaadinIcon.FOLDER_OPEN, "parcours.title", ParcoursView.class);
		addDrawerRouterLink(VaadinIcon.OPEN_BOOK, "inscriptions.title", InscriptionsView.class);
		//addDrawerRouterLink(VaadinIcon.ACADEMY_CAP, "notes.title", NotesView.class);
		addDrawerRouterLink(VaadinIcon.BAR_CHART_H, "connexions.title", ConnexionsView.class);
		if (!docUrl.isBlank()) {
			addDrawerHrefLink(VaadinIcon.BOOK, "menu.doc", docUrl, true);
		}
		if (!helpUrl.isBlank()) {
			addDrawerHrefLink(VaadinIcon.LIFEBUOY, "menu.help", helpUrl, true);
		}
		addToDrawer(tabs);

		/* Bouton de basculement du menu dans la barre d'application */
		addToNavbar(new DrawerToggle());

		navBarHeader.getStyle()
		.set("flex", "1")
		.set("margin", "0 var(--lumo-space-s) 0 0");
		addToNavbar(navBarHeader);

		if (securityService.isUserLoggedIn()) {
			securityService.getPrincipal()
			.map(this::createUserMenu)
			.ifPresent(this::addToNavbar);
		}

		// On attache la mainLayout au component afin d'être notifié des changements de dossier
		mainController.setMainLayout(this);
		
		// Si on doit afficher la pop-up d'info à l'arrivée sur l'application
		if(affichagePopupInfo && StringUtils.hasText(getTranslation("connexion.info"))) {
			log.info("Affichage popup info");
			Dialog infoDialog = new Dialog();
			Icon infoIcon = VaadinIcon.INFO_CIRCLE_O.create();
			infoIcon.getStyle().set("margin-right", "1em");
			infoIcon.setColor(CSSColorUtils.MAIN_HEADER_COLOR);
			infoDialog.add(infoIcon);
			Span info = new Span();
			info.getElement().setProperty("innerHTML", getTranslation("connexion.info"));
			infoDialog.add(info);
			infoDialog.open();
		}
	}

	private Component getResumeLayout() {
		VerticalLayout nomPrenomLayout = new VerticalLayout();
		nomPrenomLayout.getStyle().set("max-width", "16em");
		nomPrenomLayout.getStyle().set("margin-left", "auto");
		nomPrenomLayout.getStyle().set("box-shadow", "none");
		nomPrenomLayout.getStyle().set("padding-top", "0.5em");
		nomPrenomLayout.getStyle().set("padding-bottom", "0");

		nomPrenom.getStyle().set("margin-left", "auto");
		nomPrenom.getStyle().set("margin-right", "auto");
		nomPrenom.getStyle().set("color","var(--lumo-contrast-60pct)");
		nomPrenom.getStyle().set("font-weight","600");
		nomPrenomLayout.add(nomPrenom);

		numeroDossier.getStyle().set("margin", "0px auto 0px auto");
		numeroDossier.getStyle().set("font-size", "smaller");
		numeroDossier.getStyle().set("color","var(--lumo-contrast-60pct)");
		nomPrenomLayout.add(numeroDossier);
		
		// On passe les labels au service pour les mettre à jour en cas de changement de dossier
		//securityService.setInfoLabels(nomPrenom, numeroDossier);
		// Vérification que les informations nécessaires à la vue (dossier) ont été récupérées
		/*securityService.checkDossier();
		if(securityService.getDossier()!=null && securityService.getDossier().getApprenant() !=null) {
			nomPrenom.setText(getInfoNomPrenom(securityService.getDossier().getApprenant()));
			numeroDossier.setText(securityService.getDossier().getApprenant().getCode());
		}*/
		return nomPrenomLayout;
	}
	
	/**
	 * Mise à jour des données "résumé"
	 * @param apprenant
	 */
	public void updateData(Apprenant apprenant) {
		if(apprenant!=null) {
			nomPrenom.setText(getInfoNomPrenom(apprenant));
			numeroDossier.setText(apprenant.getCode());
		} else {
			nomPrenom.setText("");
			numeroDossier.setText("");
		}
		
	}


	private String getInfoNomPrenom(Apprenant apprenant) {
		String nom = apprenant.getEtatCivil().getNomUsuel() == null ? apprenant.getEtatCivil().getNomDeNaissance() : apprenant.getEtatCivil().getNomUsuel();
		String prenom = apprenant.getEtatCivil().getPrenom();
		return nom + " " + prenom;
	}
	
	private MenuBar createUserMenu(final Utilisateur utilisateur) {

		// Maj du darkMode en fonction des préférences de l'utilisateur
		Optional<PreferencesUtilisateur> prefDarkMode =prefService.getPreference(utilisateur.getUsername(), PrefUtils.DARK_MODE);
		// Si on a une préférence pour l'utilisateur
		if(prefDarkMode.isPresent()) {
			// Maj du skin en fonction de la préférence de l'utilisateur
			currentUiService.setDarkMode(prefService.getBooleanPref(prefDarkMode.get()));
		}else {
			// Dark mode par défaut
			currentUiService.setDarkMode(false);
		}

		MenuBar topMenu = new MenuBar();
		topMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
		topMenu.addClassName("user-menu");

		MenuItem userItem = topMenu.addItem(createUserImage(utilisateur));
		SubMenu userMenu = userItem.getSubMenu();

		String name = Optional.ofNullable(utilisateur.getDisplayName())
			.or(() -> Optional.ofNullable(utilisateur.getUsername()))
			.orElse("-");
		MenuItem usernameItem = userMenu.addItem(name);
		usernameItem.setEnabled(false);
		usernameItem.getElement()
		.getStyle()
		.set("color", "var(--lumo-primary-color)")
		.set("text-align", "center");

		userMenu.add(new Hr());

		//userMenuEtatCivilItem = userMenu.addItem((String) null, event -> getUI().ifPresent(ui -> ui.navigate(EtatCivilView.class)));
		//userMenuCoordonneesItem = userMenu.addItem((String) null, event -> getUI().ifPresent(ui -> ui.navigate(CoordonneesView.class)));
		// Entrée 'Paramètres' dans le menu en haut à droite :
		//userMenuParametresItem = userMenu.addItem((String) null, event -> getUI().ifPresent(ui -> ui.navigate(ParametresView.class)));
		userMenuAproposItem = userMenu.addItem((String) null, event -> getUI().ifPresent(ui -> ui.navigate(AProposView.class)));

		userMenuLogoutItem =
			userMenu.addItem((String) null,
				event -> getUI().map(UI::getPage).ifPresent(page -> page.executeJs("window.open('" + SecurityConfig.LOGOUT_URL + "', '_self')")));

		return topMenu;
	}

	private Component createUserImage(final Utilisateur utilisateur) {
		String displayName = utilisateur.getDisplayName();
		//if (displayName == null || displayName.isBlank()) {
		Icon icon = new Icon(VaadinIcon.USER);
		icon.addClassName("user-image");
		icon.getStyle().set("padding-top", "5px");
		return icon;
		/*} else {
			Div div = new Div();
			div.addClassName("user-image");

			String initials = displayName.replaceAll("\\W|(?<=\\w)\\w", "");
			if (initials.length() > 4) {
				initials = initials.substring(0, 4);
			}
			div.setText(initials);

			if (initials.length() == 3) {
				div.getStyle().set("font-size", "small");
			} else if (initials.length() == 4) {
				div.getStyle().set("font-size", "x-small");
			}
			return div;
		}*/
	}

	private void addDrawerRouterLink(final VaadinIcon icon, final String textKey, final Class<? extends Component> navigationTarget) {
		if (securityService.isAccessGranted(navigationTarget)) {
			DrawerRouterLink routerLink = new DrawerRouterLink(icon, textKey, navigationTarget);
			Tab tab = new Tab(routerLink);
			tabs.add(tab);
			navigationTargetToTab.put(navigationTarget, tab);
		}
	}

	private void addDrawerHrefLink(final VaadinIcon icon, final String textKey, final String href, final boolean openInNewTab) {
		DrawerHrefLink link = new DrawerHrefLink(icon, textKey, href, openInNewTab);
		Tab tab = new Tab(link);
		tabs.add(tab);
	}

	/**
	 * Sélectionne l'onglet adéquat.
	 * @see com.vaadin.flow.router.internal.BeforeEnterHandler#beforeEnter(com.vaadin.flow.router.BeforeEnterEvent)
	 */
	@Override
	public void beforeEnter(final BeforeEnterEvent event) {
		tabs.setSelectedTab(navigationTargetToTab.get(event.getNavigationTarget()));
	}

	/**
	 * @see com.vaadin.flow.server.PageConfigurator#configurePage(com.vaadin.flow.server.InitialPageSettings)
	 */
	@Override
	public void configurePage(final InitialPageSettings settings) {
		settings.addFavIcon("icon", "/favicon-32x32.png", "32x32");
		settings.addFavIcon("icon", "/favicon-16x16.png", "16x16");
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		if (userMenuAproposItem != null) {
			userMenuAproposItem.setText(getTranslation("apropos.title"));
		}
		if (userMenuLogoutItem != null) {
			userMenuLogoutItem.setText(getTranslation("menu.exit"));
		}
		/*if (userMenuEtatCivilItem != null) {
			userMenuEtatCivilItem.setText(getTranslation("etatcivil.title"));
		}
		if (userMenuCoordonneesItem != null) {
			userMenuCoordonneesItem.setText(getTranslation("coordonnees.title"));
		}*/
		if( userMenuParametresItem != null) {
			userMenuParametresItem.setText(getTranslation("parametres.title"));
		}

		/* Initialise les messages indiquant la perte de connexion. */
		getUI().map(UI::getReconnectDialogConfiguration)
		.ifPresent(reconnectDialogConfiguration -> {
			reconnectDialogConfiguration.setDialogText(getTranslation("vaadin.reconnectDialog.text"));
			reconnectDialogConfiguration.setDialogTextGaveUp(getTranslation("vaadin.reconnectDialog.textGaveUp"));
		});
	}

	/**
	 * @see com.vaadin.flow.component.applayout.AppLayout#showRouterLayoutContent(com.vaadin.flow.component.HasElement)
	 */
	@Override
	public void showRouterLayoutContent(final HasElement content) {
		super.showRouterLayoutContent(content);
		if (content instanceof HasHeader) {
			navBarHeader.add(((HasHeader) content).getHeader());
		}
	}

	/**
	 * @see com.vaadin.flow.router.RouterLayout#removeRouterLayoutContent(com.vaadin.flow.component.HasElement)
	 */
	@Override
	public void removeRouterLayoutContent(final HasElement oldContent) {
		super.removeRouterLayoutContent(oldContent);
		if (oldContent instanceof HasHeader) {
			((HasHeader) oldContent).getHeader().getElement().removeFromParent();
		}
	}

}
