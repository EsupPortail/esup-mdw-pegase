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
package fr.univlorraine.mondossierweb.ui.layout;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.util.StringUtils;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import fr.univlorraine.mondossierweb.config.SecurityConfig;
import fr.univlorraine.mondossierweb.controllers.ConfigController;
import fr.univlorraine.mondossierweb.controllers.SessionController;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateur;
import fr.univlorraine.mondossierweb.model.user.entity.Utilisateur;
import fr.univlorraine.mondossierweb.services.CssService;
import fr.univlorraine.mondossierweb.services.PreferencesService;
import fr.univlorraine.mondossierweb.services.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.AppColorStyle;
import fr.univlorraine.mondossierweb.ui.view.acces.AccesView;
import fr.univlorraine.mondossierweb.ui.view.apropos.AProposView;
import fr.univlorraine.mondossierweb.ui.view.connexions.ConnexionsView;
import fr.univlorraine.mondossierweb.ui.view.coordonnees.CoordonneesView;
import fr.univlorraine.mondossierweb.ui.view.etatcivil.EtatCivilView;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.InscriptionsView;
import fr.univlorraine.mondossierweb.ui.view.logger.LoggersView;
import fr.univlorraine.mondossierweb.ui.view.parametres.ParametresView;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.PrefUtils;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.pegase.model.insext.Apprenant;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Push(transport = Transport.WEBSOCKET_XHR)
//@JsModule("./src/set-dark-mode.js")
@JsModule("./src/font-open-sans.js")
@CssImport(value = "./styles/mdw-default.css")
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
@CssImport(value = "./styles/vaadin-grid-tree-toggle.css", themeFor = "vaadin-grid-tree-toggle")
@CssImport(value = "./styles/vaadin-grid-cell-content.css", themeFor = "vaadin-grid-cell-content")
@CssImport(value = "./styles/vaadin-dialog-overlay.css", themeFor = "vaadin-dialog-overlay")
@CssImport(value = "./styles/vaadin-tab.css", themeFor = "vaadin-tab")
@CssImport(value = "./styles/vaadin-tabs.css", themeFor = "vaadin-tabs")
@CssImport(value = "./styles/vaadin-drawer-toggle.css", themeFor = "vaadin-drawer-toggle")
@SuppressWarnings("serial")
@Slf4j
@Theme(variant = Lumo.LIGHT)
public class MainLayout extends AppLayout implements AppShellConfigurator, BeforeEnterObserver, LocaleChangeObserver {

	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient PreferencesService prefService;
	@Autowired
	private transient SessionController mainController;
	@Autowired
	private transient ConfigController configController;
	@Autowired
	private transient BuildProperties buildProperties;
	@Autowired
	private transient CssService cssService;


	private transient String docUrl;
	private transient String helpUrl;

	private transient boolean affichagePopupInfo;
	private transient boolean popupInfoDesactivable;

	private transient boolean affichageResumeEtudiant;

	private final Tabs tabs = new Tabs();
	private final Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();

	private final Div navBarHeader = new Div();
	private MenuItem userMenuAproposItem;
	private MenuItem userMenuParametresItem;
	private MenuItem userMenuLogoutItem;
	private Label nomPrenom = new Label();
	private Label numeroDossier = new Label();
	private final Image logo = new Image();
	private byte[] imgLogo;

	private void initParameters() {
		docUrl = configController.getDocUrl();
		helpUrl = configController.getHelpUrl();
		affichagePopupInfo = configController.isInfoConnexionActif();
		popupInfoDesactivable = configController.isInfoConnexionPrefActif();
		affichageResumeEtudiant = configController.isEtudiantResumeActif();
	}

	@PostConstruct
	public void init() {

		initParameters();

		/* Theme: Couleur principale */
		AppColorStyle appColorStyle = new AppColorStyle();
		appColorStyle.setColor(cssService.getMainColor(), cssService.getSecondColor(), cssService.getHeaderCardSepColor(),
			cssService.getTxtColor(), cssService.getBtnColor(), cssService.getBackgroundColor());
		getElement().appendChild(appColorStyle.getElement());

		/* Menu au-dessus de la barre d'application */
		setPrimarySection(Section.DRAWER);


		/* Titre et logo de l'application */
		addToDrawer(getAppTitle());


		/* Nom, prénom et code apprenant*/
		if(affichageResumeEtudiant) {
			addToDrawer(getResumeLayout());
		}

		/* Menu */
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		tabs.addSelectedChangeListener(event -> {
			/* Seules les actions de navigation doivent pouvoir changer la tab sélectionnée. */
			if (event.isFromClient()) {
				tabs.setSelectedTab(event.getPreviousTab());
			}
		});
		addDrawerRouterLink(VaadinIcon.USER_CARD, "etatcivil.title", EtatCivilView.class);
		addDrawerRouterLink(VaadinIcon.HOME, "coordonnees.title", CoordonneesView.class);
		addDrawerRouterLink(VaadinIcon.FOLDER_OPEN, "acces.title", AccesView.class);
		addDrawerRouterLink(VaadinIcon.ACADEMY_CAP, "inscriptions.title", InscriptionsView.class);
		addDrawerRouterLink(VaadinIcon.BAR_CHART_H, "connexions.title", ConnexionsView.class);
		addDrawerRouterLink(VaadinIcon.COGS, "loggers.title", LoggersView.class);
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
		.set(CSSColorUtils.MARGIN, "0 var(--lumo-space-s) 0 0");
		addToNavbar(navBarHeader);

		if (securityService.isUserLoggedIn()) {
			securityService.getPrincipal()
			.map(this::createUserMenu)
			.ifPresent(this::addToNavbar);
		}

		// On attache la mainLayout au component afin d'être notifié des changements de dossier
		mainController.setMainLayout(this);


	}


	@Override
	protected void onAttach(AttachEvent attachEvent) {
		updateLogo();
		// Si on doit afficher la pop-up d'info à l'arrivée sur l'application
		if(affichagePopupInfo && StringUtils.hasText(getTranslation("connexion.info"))) {
			log.info("Affichage popup info ?");
			createInfoPopUp(securityService.getPrincipal().orElse(null));
		}
	}


	private Component getAppTitle() {

		imgLogo = configController.getUnivLogoImg();

		HorizontalLayout appTitleLayout = new HorizontalLayout();
		appTitleLayout.setAlignItems(Alignment.END);
		appTitleLayout.getStyle().set("height", "3.75em");
		appTitleLayout.getStyle().set(CSSColorUtils.BACKGROUND_COLOR, CSSColorUtils.MAIN_COLOR);
		appTitleLayout.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.WHITE);

		HorizontalLayout titleLayout = new HorizontalLayout();
		titleLayout.add(logo);

		Div appNameTitle = new Div(new Text(buildProperties.getName()));
		appNameTitle.getElement().getStyle().set("font-size", "var(--lumo-font-size-xl)");
		appNameTitle.addClassName("tracking-in-expand");
		titleLayout.add(appNameTitle);

		titleLayout.getStyle().set(CSSColorUtils.MARGIN_LEFT, CSSColorUtils.AUTO);
		titleLayout.getStyle().set(CSSColorUtils.MARGIN_RIGHT, CSSColorUtils.AUTO);
		titleLayout.setWidthFull();
		titleLayout.getStyle().set("max-width", "16em");
		titleLayout.getStyle().set(CSSColorUtils.PADDING_LEFT, "1em");
		titleLayout.getStyle().set(CSSColorUtils.MARGIN_TOP, CSSColorUtils.AUTO);
		titleLayout.getStyle().set(CSSColorUtils.MARGIN_BOTTOM, CSSColorUtils.AUTO);

		appTitleLayout.add(titleLayout);

		return appTitleLayout;
	}

	private void updateLogo() {
		if(imgLogo != null) {
			StreamResource resource = new StreamResource("", () -> new ByteArrayInputStream(imgLogo));
			log.info("*** updateLogo ***");
			logo.setSrc(resource);
		}
		logo.setHeight(Utils.LARGEUR_LOGO);
		logo.setWidth(Utils.HAUTEUR_LOGO);
	}

	private Component getResumeLayout() {
		VerticalLayout nomPrenomLayout = new VerticalLayout();
		nomPrenomLayout.getStyle().set("max-width", "16em");
		nomPrenomLayout.getStyle().set(CSSColorUtils.MARGIN_LEFT, CSSColorUtils.AUTO);
		nomPrenomLayout.getStyle().set(CSSColorUtils.MARGIN_RIGHT, CSSColorUtils.AUTO);
		nomPrenomLayout.getStyle().set("box-shadow", "none");
		nomPrenomLayout.getStyle().set("padding-top", CSSColorUtils.EM_0_5);
		nomPrenomLayout.getStyle().set("padding-bottom", "0");
		CmpUtils.deleteGap(nomPrenomLayout);

		nomPrenom.getStyle().set(CSSColorUtils.MARGIN_LEFT, CSSColorUtils.AUTO);
		nomPrenom.getStyle().set(CSSColorUtils.MARGIN_RIGHT, CSSColorUtils.AUTO);
		nomPrenom.getStyle().set(CSSColorUtils.COLOR,"var(--lumo-contrast-80pct)");
		nomPrenom.getStyle().set(CSSColorUtils.FONT_WEIGHT,"600");
		nomPrenomLayout.add(nomPrenom);

		numeroDossier.getStyle().set(CSSColorUtils.MARGIN, "0px auto 0px auto");
		numeroDossier.getStyle().set("font-size", "smaller");
		numeroDossier.getStyle().set("color","var(--lumo-contrast-80pct)");
		nomPrenomLayout.add(numeroDossier);

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
		String nom = !StringUtils.hasText(apprenant.getEtatCivil().getNomUsuel()) ? apprenant.getEtatCivil().getNomDeNaissance() : apprenant.getEtatCivil().getNomUsuel();
		String prenom = apprenant.getEtatCivil().getPrenom();
		return nom + " " + prenom;
	}

	private MenuBar createUserMenu(final Utilisateur utilisateur) {

		MenuBar topMenu = new MenuBar();
		topMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
		topMenu.addClassName("user-menu");

		MenuItem userItem = topMenu.addItem(createUserImage());
		SubMenu userMenu = userItem.getSubMenu();

		String name = Optional.ofNullable(utilisateur.getDisplayName())
			.or(() -> Optional.ofNullable(utilisateur.getUsername()))
			.orElse("-");
		MenuItem usernameItem = userMenu.addItem(name);
		usernameItem.setEnabled(false);
		usernameItem.getElement()
		.getStyle()
		.set(CSSColorUtils.COLOR, "var(--lumo-primary-color)")
		.set("text-align", "center");

		userMenu.add(new Hr());

		// Entrée 'Paramètres' dans le menu en haut à droite :
		if (securityService.isAccessGranted(ParametresView.class)) {
			userMenuParametresItem = userMenu.addItem((String) null, event -> getUI().ifPresent(ui -> ui.navigate(ParametresView.class)));
		}
		userMenuAproposItem = userMenu.addItem((String) null, event -> getUI().ifPresent(ui -> ui.navigate(AProposView.class)));

		userMenuLogoutItem =
			userMenu.addItem((String) null,
				event -> getUI().map(UI::getPage).ifPresent(page -> page.executeJs("window.open('" + SecurityConfig.LOGOUT_URL + "', '_self')")));

		return topMenu;
	}

	private Component createUserImage() {
		Icon icon = new Icon(VaadinIcon.USER);
		icon.addClassName("user-image");
		icon.getStyle().set("padding-top", "5px");
		icon.getStyle().set("color", "white");
		return icon;
	}

	private void createInfoPopUp(final Utilisateur utilisateur) {
		if(utilisateur != null && StringUtils.hasText(utilisateur.getUsername())) {
			Optional<PreferencesUtilisateur> pu = prefService.getPreference(utilisateur.getUsername(), PrefUtils.HIDE_WELCOME_MESSAGE);
			// Si la pop-up n'est pas désactivable par l'utilisateur ou qu'il n'a pas demandé à la désactiver
			if(!popupInfoDesactivable || pu.isEmpty() || !Boolean.parseBoolean(pu.get().getValeur())) {
				log.info("Affichage popup info");
				Dialog infoDialog = new Dialog();
				infoDialog.setCloseOnOutsideClick(true);
				infoDialog.setCloseOnEsc(false);
				VerticalLayout dialogLayout = new VerticalLayout();
				dialogLayout.setPadding(false);
				Icon infoIcon = VaadinIcon.INFO_CIRCLE_O.create();
				infoIcon.getStyle().set(CSSColorUtils.MARGIN_RIGHT, "1em");
				infoIcon.setColor(CSSColorUtils.SECOND_COLOR);

				Span info = new Span();
				info.getElement().setProperty("innerHTML", getTranslation("connexion.info"));

				HorizontalLayout infoLayout = new HorizontalLayout();
				infoLayout.add(infoIcon);
				infoLayout.add(info);

				dialogLayout.add(infoLayout);

				if(popupInfoDesactivable) {
					Checkbox checkInfo =new Checkbox(getTranslation("connexion.check"));
					checkInfo.getStyle().set(CSSColorUtils.MARGIN_TOP, CSSColorUtils.AUTO);
					checkInfo.getStyle().set(CSSColorUtils.MARGIN_BOTTOM, CSSColorUtils.AUTO);
					checkInfo.addClickListener(e-> {
						log.info("Enregistrement parametre Masquer message bienvenu : {}",checkInfo.getValue());
						prefService.saveUserPref(utilisateur.getUsername(), PrefUtils.HIDE_WELCOME_MESSAGE, checkInfo.getValue());
					});

					FlexLayout btnLayout = new FlexLayout();
					btnLayout.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
					btnLayout.setFlexWrap(FlexWrap.WRAP);
					btnLayout.add(checkInfo);
					dialogLayout.add(btnLayout);
				}

				infoDialog.add(dialogLayout);

				infoDialog.open();
			} else {
				log.info("Pop-up désactivée par l'utilisateur {}",utilisateur.getUsername());
			}
		}

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
	public void configurePage(final AppShellSettings settings) {
		// Parametrage des favicon
		settings.addFavIcon("icon", "/"+configController.getUnivFavicon32Name(), "32x32");
		settings.addFavIcon("icon", "/"+configController.getUnivFavicon16Name(), "16x16");
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
