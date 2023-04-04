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
package fr.univlorraine.mondossierweb.ui.view.parametres;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplication;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplicationCategorie;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplicationValeurs;
import fr.univlorraine.mondossierweb.services.AccessTokenService;
import fr.univlorraine.mondossierweb.services.AppUserDetailsService;
import fr.univlorraine.mondossierweb.services.LdapService;
import fr.univlorraine.mondossierweb.services.ParametrageService;
import fr.univlorraine.mondossierweb.services.PegaseService;
import fr.univlorraine.mondossierweb.services.PreferencesService;
import fr.univlorraine.mondossierweb.services.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Secured(SecurityUtils.ROLE_SUPERADMIN)
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class ParametresView extends Div implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	private static final String TYPE_BOOLEAN = "BOOLEAN";
	private static final String TYPE_LIST_STRING = "LIST_STRING";
	private static final String TYPE_IMG = "IMAGE";
	private static final String TRUE_VALUE = "true";
	private static final Integer LDAP_ID = 1;
	private static final Integer PEGASE_ACCESS_TOKEN_ID = 2;
	private static final Integer PEGASE_API_ID = 3;
	private static final Integer PEGASE_PARAM = 4;
	private static final Integer ADMIN_PARAM = 8;
	private static final Integer SMTP_PARAM = 9;
	private static final Integer AFFICHAGE_PARAM = 6;
	private static final String PARAMETRES_BUTTON_SYNC = "parametres.button-sync";
	private static final String REFRESH_PARAMETERS = "refreshParameters";

	@Autowired
	private transient PreferencesService prefService;
	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient AccessTokenService accessTokenService;
	@Autowired
	private transient PegaseService pegaseService;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;

	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private final Checkbox darkModeCB = new Checkbox();
	private final TextField docUrlTF = new TextField();
	private final TextField assistanceUrlTF = new TextField();

	private final VerticalLayout parametresLayout = new VerticalLayout();

	private List<Button> buttonsEditer = new LinkedList<> ();
	private List<Button> buttonsAnnuler = new LinkedList<> ();
	private List<Button> buttonsEnregistrer = new LinkedList<> ();

	private HashMap<String,String> backupValues = new HashMap<> ();

	private HashMap<String,byte[]> blobValues = new HashMap<> ();

	private HashMap<String,String> blobNames = new HashMap<> ();

	private HashMap<String, Image> blobImages = new HashMap<> ();

	@PostConstruct
	private void init() {
		setSizeFull();

		parametresLayout.setWidthFull();
		parametresLayout.getStyle().set("max-width", "52em");
		parametresLayout.setJustifyContentMode(JustifyContentMode.EVENLY);

		initParameters();

		add(parametresLayout);

	}

	private void initParameters() {

		List<PreferencesApplicationCategorie> categories = prefService.getCategories();
		if(categories!=null && !categories.isEmpty()) {
			for(PreferencesApplicationCategorie categorie : categories) {

				Card categorieCard = new Card(VaadinIcon.COG_O.create(),"", false);
				categorieCard.getTitre().setText(categorie.getCatDesc());

				if(categorie.getPreferencesApplication() != null && !categorie.getPreferencesApplication().isEmpty()) {
					Collections.sort(categorie.getPreferencesApplication(), 
						(PreferencesApplication p1, PreferencesApplication p2) -> p1.getOrdre().compareTo(p2.getOrdre()));
					VerticalLayout categorieLayout = new VerticalLayout();
					for(PreferencesApplication p : categorie.getPreferencesApplication()) {
						if(p.getType().isSecret()) {
							PasswordField pf = new PasswordField(p.getPrefDesc());
							pf.setId(p.getPrefId());
							pf.setWidthFull();
							if(p.getSecret()!=null) {
								pf.setValue(p.getSecret());
							}
							pf.setReadOnly(true);
							categorieLayout.add(pf);
						} else {
							if(p.getType().getTypeId().equals(TYPE_BOOLEAN)){
								Checkbox cb = new Checkbox(p.getPrefDesc());
								cb.setId(p.getPrefId());
								cb.setValue(p.getValeur().equals(TRUE_VALUE));
								cb.setReadOnly(true);
								categorieLayout.add(cb);
							} else {
								if(p.getType().getTypeId().equals(TYPE_LIST_STRING)){
									ComboBox<PreferencesApplicationValeurs> cb = new ComboBox<>(p.getPrefDesc());
									cb.setWidthFull();
									cb.setId(p.getPrefId());
									cb.setItems(p.getValeurs());
									cb.setItemLabelGenerator(PreferencesApplicationValeurs::getLibelle);
									cb.setValue(p.getValeur() != null ? prefService.getPreferencesApplicationValeurs(p) : null);
									cb.setReadOnly(true);
									categorieLayout.add(cb);
								} else {
									// S'il s'agit d'une image
									if(p.getType().getTypeId().equals(TYPE_IMG)){
										Label labelImage = new Label(p.getPrefDesc());
										categorieLayout.add(labelImage);
										Image image = new Image();
										StreamResource resource;
										if (p.getData() != null) {
											resource = new StreamResource(p.getValeur(), () -> new ByteArrayInputStream(p.getData()));
											image = new Image(resource, p.getValeur());
										}
										image.setHeight(Utils.LARGEUR_LOGO);
										image.setWidth(Utils.HAUTEUR_LOGO);
										categorieLayout.add(image);
										MemoryBuffer memoryBuffer = new MemoryBuffer();
										Upload uploadImg = new Upload(memoryBuffer);
										uploadImg.setId(p.getPrefId());
										blobImages.put(p.getPrefId(), image);
										uploadImg.setAcceptedFileTypes("image/png","image/jpg");
										uploadImg.addSucceededListener(event -> {
											// Récupération des informations sur le fichier
											InputStream fileData = memoryBuffer.getInputStream();
											String fileName = event.getFileName();

											log.info("Image {} mimeType : {} length : {}", fileName, event.getMIMEType(),event.getContentLength());
											// Maj de l'image
											try {
												blobValues.put(uploadImg.getId().get(), fileData.readAllBytes());
												Timestamp timestamp = new Timestamp(System.currentTimeMillis());
												blobNames.put(uploadImg.getId().get(), Utils.getFileName(fileName) + "-" + timestamp.getTime() + Utils.getFileExtension(fileName));
											} catch (IOException e) {
												String errorMessage = e.getMessage();

												Notification notification = Notification.show(
													errorMessage,
													5000,
													Notification.Position.MIDDLE
													);
												notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
											}
										});
										uploadImg.addFileRejectedListener(event -> {
											String errorMessage = event.getErrorMessage();

											Notification notification = Notification.show(
												errorMessage,
												5000,
												Notification.Position.MIDDLE
												);
											notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
										});
										uploadImg.setVisible(false);
										categorieLayout.add(uploadImg);
									} else {
										TextField tf = new TextField(p.getPrefDesc());
										tf.setId(p.getPrefId());
										tf.setWidthFull();
										if(p.getValeur()!=null) {
											tf.setValue(p.getValeur());
										}
										tf.setReadOnly(true);
										categorieLayout.add(tf);
									}
								}
							}
						}
					}
					initButtons(categorieLayout, categorie.getCatId());
					categorieCard.add(categorieLayout);

				}
				parametresLayout.add(categorieCard);
			}
			updateStyle();
		}


	}

	private void initButtons(VerticalLayout layout, Integer categorieId) {
		HorizontalLayout bl = new HorizontalLayout();
		bl.setWidthFull();
		Button buttonEditer = new Button();
		Button buttonAnnuler = new Button();
		buttonAnnuler.setVisible(false);
		Button buttonEnregistrer = new Button();
		buttonEnregistrer.setVisible(false);
		buttonsEditer.add(buttonEditer);
		buttonsAnnuler.add(buttonAnnuler);
		buttonsEnregistrer.add(buttonEnregistrer);
		bl.add(buttonEditer);
		bl.add(buttonAnnuler);
		bl.add(buttonEnregistrer);
		buttonEditer.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		buttonAnnuler.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		buttonEnregistrer.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		layout.add(bl);

		HorizontalLayout testButtonLayout = new HorizontalLayout();
		testButtonLayout.setWidthFull();
		Button buttonTester = new Button();
		buttonTester.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		testButtonLayout.add(buttonTester);

		HorizontalLayout syncButtonLayout = new HorizontalLayout();
		syncButtonLayout.setWidthFull();
		Button buttonSync = new Button();
		buttonSync.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		syncButtonLayout.add(buttonSync);

		buttonEditer.addClickListener(e -> {
			layout.getChildren().forEach(c -> setEditableComponent(c, false, false, false));
			buttonEditer.setVisible(false);
			buttonTester.setVisible(false);
			buttonAnnuler.setVisible(true);
			buttonEnregistrer.setVisible(true);
			buttonSync.setVisible(false);
		});

		buttonAnnuler.addClickListener(e -> {
			layout.getChildren().forEach(c -> setEditableComponent(c, true, true, false));
			buttonEditer.setVisible(true);
			buttonTester.setVisible(true);
			buttonAnnuler.setVisible(false);
			buttonEnregistrer.setVisible(false);
			buttonSync.setVisible(true);
		});

		buttonEnregistrer.addClickListener(e -> {
			layout.getChildren().forEach(c -> setEditableComponent(c, true, false, true));
			buttonEditer.setVisible(true);
			buttonTester.setVisible(true);
			buttonAnnuler.setVisible(false);
			buttonEnregistrer.setVisible(false);
			buttonSync.setVisible(true);
		});

		//S'il s'agit de la catégorie LDAP
		if(categorieId.equals(LDAP_ID)) {
			buttonSync.setText(getTranslation(PARAMETRES_BUTTON_SYNC));
			buttonSync.addClickListener(e -> syncServiceConfig(LdapService.class.getName(),REFRESH_PARAMETERS));
			layout.add(syncButtonLayout);
		}
		//S'il s'agit de la catégorie Pégase Access-token
		if(categorieId.equals(PEGASE_ACCESS_TOKEN_ID)) {
			buttonTester.setText(getTranslation("parametres.button-tester-accesstoken"));
			buttonTester.addClickListener(e -> {
				try {
					// Maj des paramètres depuis la BDD
					accessTokenService.refreshParameters();
					// Récupération du token
					String t = accessTokenService.getToken();
					if(StringUtils.hasText(t)) {
						Utils.notifierSucces(getTranslation("accesstoken.ok"));
					} else {
						Utils.notifierAnomalie(getTranslation("accesstoken.error"));
					}
				}catch(Exception ex) {
					Utils.notifierAnomalie(getTranslation("accesstoken.error") + " : " + ex.getLocalizedMessage());
				}
			});
			layout.add(testButtonLayout);

			buttonSync.setText(getTranslation(PARAMETRES_BUTTON_SYNC));
			buttonSync.addClickListener(e -> syncServiceConfig(AccessTokenService.class.getName(), REFRESH_PARAMETERS));
			layout.add(syncButtonLayout);
		}

		//S'il s'agit de la catégorie Pégase API
		if(categorieId.equals(PEGASE_API_ID)) {
			buttonTester.setText(getTranslation("parametres.button-tester-api"));
			buttonTester.addClickListener(e -> {
				// Maj des paramètres depuis la BDD
				pegaseService.refreshParameters();
				try {
					// teste api INS
					if(pegaseService.recupererDossierApprenant(pegaseService.getCodeApprenantTest()) != null) {
						Utils.notifierSucces(getTranslation("api-ins.ok", pegaseService.getCodeApprenantTest()));
					} else {
						Utils.notifierAnomalie(getTranslation("api-ins.error", pegaseService.getCodeApprenantTest()));
					}
				}catch(Exception ex) {
					Utils.notifierAnomalie(getTranslation("api-ins.error", pegaseService.getCodeApprenantTest()) + " : " + ex.getLocalizedMessage());
				}
				try {
					// teste api CHC
					if(pegaseService.getCursus(pegaseService.getCodeApprenantTest()) != null) {
						Utils.notifierSucces(getTranslation("api-chc.ok", pegaseService.getCodeApprenantTest()));
					} else {
						Utils.notifierAnomalie(getTranslation("api-chc.error", pegaseService.getCodeApprenantTest()));
					}
				}catch(Exception ex) {
					Utils.notifierAnomalie(getTranslation("api-chc.error", pegaseService.getCodeApprenantTest()) + " : " + ex.getLocalizedMessage());
				}
				try {
					// teste api COC
					if(pegaseService.getNotes(pegaseService.getCodeApprenantTest(), pegaseService.getCodePeriodeTest(), pegaseService.getCodeCheminTest()) != null) {
						Utils.notifierSucces(getTranslation("api-coc.ok", pegaseService.getCodeApprenantTest()));
					} else {
						Utils.notifierAnomalie(getTranslation("api-coc.error", pegaseService.getCodeApprenantTest()));
					}
				}catch(Exception ex) {
					Utils.notifierAnomalie(getTranslation("api-coc.error", pegaseService.getCodeApprenantTest()) + " : " + ex.getLocalizedMessage());
				}
				try {
					// teste api PAI
					File pdf = pegaseService.attestationDePaiement(pegaseService.getCodeApprenantTest(), pegaseService.getCodePeriodeTest());
					if(pdf != null && StringUtils.hasText(pdf.getName()) && pdf.getName().endsWith(Utils.EXT_PDF)) {
						Utils.notifierSucces(getTranslation("api-pai.ok", pegaseService.getCodeApprenantTest()));
					} else {
						Utils.notifierAnomalie(getTranslation("api-pai.error", pegaseService.getCodeApprenantTest()));
					}
				}catch(Exception ex) {
					Utils.notifierAnomalie(getTranslation("api-pai.error", pegaseService.getCodeApprenantTest()) + " : " + ex.getLocalizedMessage());
				}
			});
			layout.add(testButtonLayout);

			buttonSync.setText(getTranslation(PARAMETRES_BUTTON_SYNC));
			buttonSync.addClickListener(e -> syncServiceConfig(PegaseService.class.getName(), "refreshApiParameters"));
			layout.add(syncButtonLayout);
		}

		//S'il s'agit de la catégorie paramétrage Pégase
		if(categorieId.equals(PEGASE_PARAM)) {
			buttonSync.setText(getTranslation(PARAMETRES_BUTTON_SYNC));
			buttonSync.addClickListener(e -> syncServiceConfig(PegaseService.class.getName(), "refreshPegaseParameters"));
			layout.add(syncButtonLayout);
		}

		//S'il s'agit de la catégorie Administration
		if(categorieId.equals(ADMIN_PARAM)) {
			buttonSync.setText(getTranslation(PARAMETRES_BUTTON_SYNC));
			buttonSync.addClickListener(e -> {
				syncServiceConfig(ParametrageService.class.getName(), "refreshLogParameters");
				syncServiceConfig(AppUserDetailsService.class.getName(), REFRESH_PARAMETERS);
			});
			layout.add(syncButtonLayout);
		}

		//S'il s'agit de la catégorie Affichage
		if(categorieId.equals(AFFICHAGE_PARAM)) {
			buttonSync.setText(getTranslation(PARAMETRES_BUTTON_SYNC));
			buttonSync.addClickListener(e -> syncServiceConfig(ParametrageService.class.getName(), "refreshFavIconParameters"));
			layout.add(syncButtonLayout);
		}

		//S'il s'agit de la catégorie SMTP
		if(categorieId.equals(SMTP_PARAM)) {
			buttonSync.setText(getTranslation(PARAMETRES_BUTTON_SYNC));
			buttonSync.addClickListener(e -> syncServiceConfig(ParametrageService.class.getName(), "refreshSmtpParameters"));
			layout.add(syncButtonLayout);
		}
	}

	private void syncServiceConfig(String serviceClassName, String methodName) {
		try {
			// Demander la maj des services des instances via la BDD
			boolean syncOk = prefService.forceServiceSync(serviceClassName, methodName, securityService.getUsername());
			if(syncOk) {
				Utils.notifierSucces(getTranslation("sync.ok"));
			} else {
				Utils.notifierAnomalie(getTranslation("sync.error"));
			}
		}catch(Exception ex) {
			Utils.notifierAnomalie(getTranslation("sync.error") + " : " + ex.getLocalizedMessage());
		}
	}

	// modification du composant/paramètreApplicatif en fonction des paramètres 
	private void setEditableComponent(Component c, boolean readonly, boolean rollback, boolean save) {
		if(save) {
			saveValueToDb(c);
		} else {
			backupOrRollbackValue(c,rollback);
		}
		if(c instanceof TextField) {
			TextField tf = (TextField) c;
			tf.setReadOnly(readonly);
		}
		if(c instanceof PasswordField) {
			PasswordField pf = (PasswordField) c;
			pf.setReadOnly(readonly);
		}
		if(c instanceof Checkbox) {
			Checkbox cb = (Checkbox) c;
			cb.setReadOnly(readonly);
		}
		if(c instanceof Upload) {
			Upload u = (Upload) c;
			u.setVisible(!readonly);
		}
		if(c instanceof ComboBox) {
			ComboBox<PreferencesApplicationValeurs> cb = (ComboBox<PreferencesApplicationValeurs>) c;
			cb.setReadOnly(readonly);
		}
	}

	// Sauvegarde des valeurs en base
	private void saveValueToDb(Component c) {
		Optional<String> componentId = c.getId();
		if(componentId.isPresent()) {
			if(c instanceof TextField) {
				TextField tf = (TextField) c;
				PreferencesApplication pa = prefService.savePref(componentId.get(), tf.getValue());
				tf.setValue(pa.getValeur());
			}
			if(c instanceof PasswordField) {
				PasswordField pf = (PasswordField) c;
				PreferencesApplication pa = prefService.savePref(componentId.get(), pf.getValue());
				pf.setValue(pa.getSecret());
			}
			if(c instanceof Checkbox) {
				Checkbox cb = (Checkbox) c;
				PreferencesApplication pa = prefService.savePref(componentId.get(), cb.getValue().toString());
				cb.setValue(pa.getValeur().equals(TRUE_VALUE));
			}
			if(c instanceof Upload) {
				String idPref = componentId.get();
				byte[] v = blobValues.get(componentId.get());
				String n = blobNames.get(componentId.get());
				if(n != null) {
					prefService.savePref(idPref, n, v);
					Image i = blobImages.get(componentId.get());

					StreamResource resource = new StreamResource(n, () -> new ByteArrayInputStream(v));
					i.setSrc(resource);
					i.setAlt(n);
				}
			}
			if(c instanceof ComboBox) {
				ComboBox<PreferencesApplicationValeurs> cb = (ComboBox<PreferencesApplicationValeurs>) c;
				PreferencesApplication pa = prefService.savePref(componentId.get(), cb.getValue().getValeur());
				cb.setValue(prefService.getPreferencesApplicationValeurs(pa));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void backupOrRollbackValue(Component c,  boolean rollback) {
		Optional<String> componentId = c.getId();
		if(componentId.isPresent()) {
			// On récupère les anciennes valeurs
			String value = null;
			// Si on a une valeur sauvegardée pour le composant
			if(backupValues.containsKey(componentId.get())) {
				// récupération de la valeur stockée
				value = backupValues.get(componentId.get());
				// suppression de la valeur sauvegardée dans la hashMap
				backupValues.remove(componentId.get());
			}
			if(c instanceof TextField) {
				TextField tf = (TextField) c;
				if(rollback) {
					tf.setValue(value);
				} else {
					backupValues.put(componentId.get(), tf.getValue());
				}
			}
			if(c instanceof PasswordField) {
				PasswordField pf = (PasswordField) c;
				if(rollback) {
					pf.setValue(value);
				} else {
					backupValues.put(componentId.get(), pf.getValue());
				}
			}
			if(c instanceof Checkbox) {
				Checkbox cb = (Checkbox) c;
				if(rollback) {
					cb.setValue(value != null && value.equals(TRUE_VALUE));
				} else {
					backupValues.put(componentId.get(), cb.getValue().toString());
				}
			}
			if(c instanceof Upload) {
				Upload u = (Upload) c;
				Optional<String> uid = u.getId();
				if(rollback && uid.isPresent()) {
					blobValues.remove(uid.get());
					blobNames.remove(uid.get());
				} 
			}
			if(c instanceof ComboBox) {
				ComboBox<PreferencesApplicationValeurs> cb = (ComboBox<PreferencesApplicationValeurs>) c;
				if(rollback) {
					cb.setValue(getPreferencesApplicationValeursFromValId(cb, Integer.parseInt(value)));
				} else {
					backupValues.put(componentId.get(), cb.getValue().getValId().toString());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private PreferencesApplicationValeurs getPreferencesApplicationValeursFromValId(ComboBox<PreferencesApplicationValeurs> comboBox, Integer value) {
		ListDataProvider<PreferencesApplicationValeurs> dataProvider = (ListDataProvider<PreferencesApplicationValeurs>) comboBox.getDataProvider();
		List<PreferencesApplicationValeurs> items = (List<PreferencesApplicationValeurs>) dataProvider.getItems();
		if(items!=null && !items.isEmpty()) {
			for(PreferencesApplicationValeurs item : items) {
				if(item!=null && item.getValId().equals(value)) {
					return item;
				}
			}
		}
		return null;
	}

	protected void updateStyle() {
		List<Component> listComp = parametresLayout.getChildren().collect(Collectors.toList());
		int cpt=0;
		for(Component c : listComp) {
			cpt++;
			Card coordCard = (Card) c; 
			coordCard.updateStyle();
			if(cpt<listComp.size()) {
				coordCard.addClassName("card-with-separator");
			}
		}

	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("parametres.title"));

		docUrlTF.setLabel(getTranslation("parametres.doc-url"));
		assistanceUrlTF.setLabel(getTranslation("parametres.assistance-url"));
		darkModeCB.setLabel(getTranslation("parametres.dark-mode-label"));
		buttonsEditer.forEach(b -> b.setText(getTranslation("parametres.button-editer-parametres")));
		buttonsAnnuler.forEach(b -> b.setText(getTranslation("parametres.button-annuler-parametres")));
		buttonsEnregistrer.forEach(b -> b.setText(getTranslation("parametres.button-enregistrer-parametres")));
	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

}
