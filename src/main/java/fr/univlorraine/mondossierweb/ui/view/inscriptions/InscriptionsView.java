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
package fr.univlorraine.mondossierweb.ui.view.inscriptions;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import fr.univlorraine.mondossierweb.controllers.PegaseController;
import fr.univlorraine.mondossierweb.controllers.SessionController;
import fr.univlorraine.mondossierweb.services.ExportService;
import fr.univlorraine.mondossierweb.services.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.component.TextLabel;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import fr.univlorraine.pegase.model.chc.AmenagementDCA;
import fr.univlorraine.pegase.model.coc.Absence;
import fr.univlorraine.pegase.model.insgestion.ApprenantEtInscriptions;
import fr.univlorraine.pegase.model.insgestion.CibleInscription;
import fr.univlorraine.pegase.model.insgestion.InscriptionComplete;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_GESTIONNAIRE})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class InscriptionsView extends VerticalLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver, HasUrlParameter<String> {

	private static final String CERT_FILE_EXT = ".pdf";
	private static final String CERT_FILE_NAME = "certificat";
	private static final String ATTEST_FILE_NAME = "attestation";
	private static final String ATTEST_FILE_EXT = ".pdf";


	private transient Boolean avecBareme;

	private transient Boolean avecCoeff;

	private transient Boolean avecECTS;

	private transient Boolean avecControle;

	private transient String afficherDetailInscription;

	private transient Boolean facItalique;

	private transient List<String> listeStatutsInscriptionAffichees;	
	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient SessionController sessionController;
	@Autowired
	private transient PegaseController pegaseController;
	@Autowired
	private transient ConfigController configController;
	@Autowired
	private transient ExportService exportService;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private int windowWidth;
	private final VerticalLayout inscriptionsLayout = new VerticalLayout();

	// label d'erreur
	private final Label errorLabel = new Label();

	transient List<TextLabel> listTextLabelFormation = new LinkedList<> ();
	transient List<TextLabel> listTextLabelPeriode = new LinkedList<> ();
	transient List<TextLabel> listTextLabelRegime = new LinkedList<> ();
	transient List<TextLabel> listTextLabelStatut = new LinkedList<> ();
	transient List<TextLabel> listTextLabelPaiement = new LinkedList<> ();
	transient List<TextLabel> listTextLabelPieces = new LinkedList<> ();
	transient List<Button> listButtonCertificat = new LinkedList<> ();
	transient List<Button> listButtonAttestation = new LinkedList<> ();
	transient List<Button> listButtonPhoto = new LinkedList<> ();
	transient List<Button> listButtonCursus = new LinkedList<> ();
	transient List<Button> listButtonNotes = new LinkedList<> ();
	transient List<Button> listButtonDetailInscription = new LinkedList<> ();
	transient List<Button> listButtonMasquerInscription = new LinkedList<> ();



	@PostConstruct
	public void init() {

		initParameters();

		setSizeFull();
		addClassName("view");

		inscriptionsLayout.setWidthFull();
		inscriptionsLayout.getStyle().set("max-width", "52em");
		inscriptionsLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
		add(inscriptionsLayout);

		UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> windowWidth = details.getWindowInnerWidth());
		UI.getCurrent().getPage().addBrowserWindowResizeListener(event -> windowWidth = event.getWidth());
	}


	private void initParameters() {
		avecBareme = configController.isAffichageNoteBaremeActif();
		avecCoeff = configController.isAffichageNoteCoeffActif();
		avecECTS = configController.isAffichageCreditECTSActif();
		avecControle = configController.isAffichageNoteControleActif();
		afficherDetailInscription = configController.getInscriptionDetail();
		facItalique = configController.isAffichageCursusFacItalique();
		listeStatutsInscriptionAffichees = Arrays.asList(configController.getInscriptionStatuts().split(","));
	}


	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		log.info("localeChange");
		setViewTitle(getTranslation("inscriptions.title"));

		errorLabel.setText(getTranslation("error.unknown"));

		for(TextLabel tl : listTextLabelFormation) {
			tl.setLabel(getTranslation("inscription.formation"));
		}
		for(TextLabel tf : listTextLabelPeriode) {
			tf.setLabel(getTranslation("inscription.periode"));
		}
		for(TextLabel tf : listTextLabelRegime) {
			tf.setLabel(getTranslation("inscription.regime"));
		}
		for(TextLabel tf : listTextLabelStatut) {
			tf.setLabel(getTranslation("inscription.statut"));
		}
		for(TextLabel tf : listTextLabelPaiement) {
			tf.setLabel(getTranslation("inscription.paiement"));
		}
		for(TextLabel tf : listTextLabelPieces) {
			tf.setLabel(getTranslation("inscription.pieces"));
		}
		for(Button b : listButtonCertificat) {
			b.setText(getTranslation("inscription.certificat"));
		}
		for(Button b : listButtonAttestation) {
			b.setText(getTranslation("inscription.attestation"));
		}
		for(Button b : listButtonPhoto ) {
			b.setText(getTranslation("inscription.photo"));
		}
		for(Button b : listButtonCursus ) {
			b.setText(getTranslation("inscription.cursus"));
		}
		for(Button b : listButtonNotes ) {
			b.setText(getTranslation("inscription.notes"));
		}
		for(Button b : listButtonDetailInscription ) {
			b.setText(getTranslation("inscription.detail"));
		}
		for(Button b : listButtonMasquerInscription ) {
			b.setText(getTranslation("inscription.masquer"));
		}



	}

	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

	@Override
	public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String codeApprenant) {
		// Sécurisation de l'accès au dossier en paramètre
		if(!securityService.secureAccess(codeApprenant)) {
			Notification.show(getTranslation("error.accesdossierrefuse"));
		}
		// Vérification que les informations nécessaires à la vue (dossier) ont été récupérées
		sessionController.checkDossier();
		// Mise à jour de l'affichage
		updateData(sessionController.getDossier()!=null ? sessionController.getDossier() : null);
		//Force la maj des label
		localeChange(null);
	}

	/**
	 * Reset toutes les données affichées
	 * @param apprenant
	 */
	private void resetData() {
		inscriptionsLayout.removeAll();
		listTextLabelFormation.clear();
		listTextLabelPeriode.clear();
		listTextLabelRegime.clear();
		listTextLabelStatut.clear();
		listTextLabelPaiement.clear();
		listTextLabelPieces.clear();
		listButtonCertificat.clear();
		listButtonAttestation.clear();
		listButtonCursus.clear();
		listButtonPhoto.clear();
	}
	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	private void updateData(ApprenantEtInscriptions dossier) {
		resetData();
		if(dossier == null ) {
			this.removeAll();
			add(errorLabel);
		}
		if(dossier!=null && dossier.getInscriptions() != null && !dossier.getInscriptions() .isEmpty()) {
			//On trie les inscriptions sur l'année universitaire de la période, par ordre décroissant
			dossier.getInscriptions().sort((InscriptionComplete i1, InscriptionComplete i2) ->
			i2.getCible().getPeriode().getAnneeUniversitaire().compareTo(i1.getCible().getPeriode().getAnneeUniversitaire()));
			// Pour chaque inscription
			for(InscriptionComplete inscription : dossier.getInscriptions() ) {
				boolean inscriptionValide = false;
				boolean inscriptionPayee = false;
				boolean inscriptionAffichee = false;
				boolean inscriptionEnCours = false;
				CibleInscription cible = inscription.getCible();

				// Libellé de la carte
				String libelleInscription = cible.getLibelleCourt() != null ? cible.getLibelleCourt() : cible.getFormation().getLibelleLong() ;
				Card insCard = new Card(VaadinIcon.BOOKMARK_O.create(),libelleInscription, true);

				// FORMATION
				TextLabel formation = new TextLabel();
				formation.setVisible(false);
				if(cible.getFormation()!=null) {
					CmpUtils.valueAndVisibleIfNotNull(formation,cible.getFormation().getLibelleLong());
				}
				formation.getStyle().set(CSSColorUtils.MARGIN_TOP, "var(--lumo-space-m)");
				listTextLabelFormation.add(formation);


				// PERIODE
				TextLabel periode = new TextLabel();
				periode.setVisible(false);
				if(cible.getPeriode()!=null) {
					CmpUtils.valueAndVisibleIfNotNull(periode,cible.getPeriode().getLibelleAffichage());
					if(!Utils.estPassee(cible.getPeriode().getDateFin())) {
						inscriptionEnCours = true;
					}
				}
				CmpUtils.setModerateTextLabel(periode);
				periode.getStyle().set(CSSColorUtils.MARGIN_TOP, "var(--lumo-space-m)");
				listTextLabelPeriode.add(periode);

				// REGIME
				TextLabel regime = new TextLabel();
				regime.setVisible(false);
				if(inscription.getRegimeInscription()!=null ) {
					CmpUtils.valueAndVisibleIfNotNull(regime,inscription.getRegimeInscription().getLibelle());
				}
				listTextLabelRegime.add(regime);

				// STATUT INSCRIPTION
				TextLabel statut = new TextLabel();
				statut.setVisible(false);
				if(inscription.getStatutInscription()!=null) {
					CmpUtils.valueAndVisibleIfNotNull(statut,formatEtat(inscription.getStatutInscription().getValue()));
					if(inscription.getStatutInscription().getValue().equals(Utils.TEM_INS_VALIDE)) {
						inscriptionValide =  true;
					}
					// Si le statut de l'inscription fait partie des statuts à afficher
					if(listeStatutsInscriptionAffichees != null && listeStatutsInscriptionAffichees.contains(inscription.getStatutInscription().getValue())) {
						// l'inscrition doit être affichée
						inscriptionAffichee = true;
					}
				}
				// Si l'inscrition doit être affichée
				if(inscriptionAffichee) {

					listTextLabelStatut.add(statut);

					// STATUT PAIEMENT
					TextLabel paiement = new TextLabel();
					paiement.setVisible(false);
					if(inscription.getStatutPaiement()!=null) {
						CmpUtils.valueAndVisibleIfNotNull(paiement, formatEtat(inscription.getStatutPaiement().getValue()));
						if(inscription.getStatutPaiement().getValue().equals(Utils.TEM_INS_PAYEE)) {
							inscriptionPayee =  true;
						}
					}
					listTextLabelPaiement.add(paiement);

					// STATUT PIECES JUSTIFICATIVES
					TextLabel pieces = new TextLabel();
					pieces.setVisible(false);
					if(inscription.getStatutPieces()!=null) {
						CmpUtils.valueAndVisibleIfNotNull(pieces,formatEtat(inscription.getStatutPieces().getValue()));
					}
					listTextLabelPieces.add(pieces);


					Anchor exportCertificatAnchor = new Anchor();
					if(configController.isCertificatActif()) {
						// Ajout bouton certificat de scolarité
						Button certButton = new Button("", VaadinIcon.FILE_TEXT_O.create());
						certButton.setWidth("15em");
						certButton.getStyle().set(CSSColorUtils.BACKGROUND_COLOR, CSSColorUtils.BTN_COLOR);
						certButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.WHITE);
						exportCertificatAnchor.getStyle().set(CSSColorUtils.MARGIN_LEFT, "0");
						exportCertificatAnchor.add(certButton);
						exportCertificatAnchor.setHref(new StreamResource(CERT_FILE_NAME +"-" + LocalDateTime.now() + CERT_FILE_EXT,
							() -> exportService.getCertificat(dossier.getApprenant().getCode(), Utils.getCodeVoeu(inscription))));
						exportCertificatAnchor.getElement().getStyle().set(CSSColorUtils.MARGIN_LEFT, "1em");
						exportCertificatAnchor.setTarget("_blank");

						// Ajout à la liste des boutons
						listButtonCertificat.add(certButton);
					}

					Anchor exportAttestationAnchor = new Anchor();
					if(configController.isAttestationPaiementActif()) {
						// Ajout bouton attestation de paiement
						Button attestationButton = new Button("", VaadinIcon.FILE_TEXT_O.create());
						attestationButton.setWidth("15em");
						attestationButton.getStyle().set(CSSColorUtils.BACKGROUND_COLOR, CSSColorUtils.BTN_COLOR);
						attestationButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.WHITE);
						exportAttestationAnchor.getStyle().set(CSSColorUtils.MARGIN_LEFT, "0");
						exportAttestationAnchor.add(attestationButton);
						exportAttestationAnchor.setHref(new StreamResource(ATTEST_FILE_NAME +"-" + LocalDateTime.now() + ATTEST_FILE_EXT,
							() -> exportService.getAttestation(dossier.getApprenant().getCode(),  Utils.getCodePeriode(inscription))));
						exportAttestationAnchor.getElement().getStyle().set(CSSColorUtils.MARGIN_LEFT, "1em");
						exportAttestationAnchor.setTarget("_blank");

						// Ajout à la liste des boutons
						listButtonAttestation.add(attestationButton);
					}

					// Ajout bouton photo
					Button photoButton = new Button("", VaadinIcon.USER.create());
					photoButton.setWidth("7em");
					photoButton.setHeight("8em");
					VerticalLayout photoLayout=new VerticalLayout();
					photoLayout.setSizeUndefined();
					photoLayout.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
					photoLayout.getStyle().set(CSSColorUtils.MARGIN_TOP, "0");
					photoLayout.getStyle().set(CSSColorUtils.PADDING, "0");
					if(!afficherDetailInscription.equals(Utils.DETAIL_INS_NON_AFFICHE)) {
						photoButton.addClickListener(c-> {
							ByteArrayInputStream photo = exportService.getPhoto(dossier.getApprenant().getCode(),  Utils.getCodeVoeu(inscription));
							if(photo != null) {
								StreamResource resource = new StreamResource("photo_"+sessionController.getCodeApprenant()+".jpg", () -> photo);
								Image image = new Image(resource, "photographie");
								image.setHeight("10em");
								image.getStyle().set(CSSColorUtils.BORDER_RADIUS, "0.8em");
								image.getStyle().set("border", "0.1em solid lightgray");
								photoLayout.removeAll();
								photoLayout.add(image);
								photoButton.setVisible(false);

							} else {
								if(StringUtils.hasText(getTranslation("photo.aucune"))) {
									Label noPhotoLabel=new Label(getTranslation("photo.aucune"));
									photoLayout.getStyle().set(CSSColorUtils.FONT_STYLE, CSSColorUtils.ITALIC);
									photoLayout.getStyle().set(CSSColorUtils.BORDER_RADIUS, "0.8em");
									photoLayout.getStyle().set(CSSColorUtils.PADDING, "3em 1em 3em 1em");
									photoLayout.getStyle().set("border", "0.1em dashed lightgray");
									photoLayout.removeAll();
									photoLayout.add(noPhotoLabel);
								}
								photoButton.setVisible(false);
							}
						});

						//Récupération de la photo automatiquement
						photoButton.click();
					}

					// Ajout à la liste des boutons
					listButtonPhoto.add(photoButton);



					VerticalLayout verticalLayout = new VerticalLayout();
					verticalLayout.getStyle().set(CSSColorUtils.PADDING, "0");
					verticalLayout.setSizeFull();

					FormLayout infoLayout = new FormLayout();
					infoLayout.getStyle().set(CSSColorUtils.MARGIN, "0");
					infoLayout.add(formation);
					infoLayout.add(periode);
					verticalLayout.add(infoLayout);

					VerticalLayout verticalInfoPhotoAndExportLayout= new VerticalLayout();
					verticalInfoPhotoAndExportLayout.getStyle().set(CSSColorUtils.PADDING, "0");

					FlexLayout flexInfoAndPhotoLayout = new FlexLayout();
					if(afficherDetailInscription.equals(Utils.DETAIL_INS_AFFICHE)){
						flexInfoAndPhotoLayout.getStyle().set(CSSColorUtils.BORDER_TOP, CSSColorUtils.SOLID_LIGHTGRAY);
					}
					flexInfoAndPhotoLayout.getStyle().set("padding-top", "1em");
					VerticalLayout detailInscriptionLayout = new VerticalLayout();
					detailInscriptionLayout.getStyle().set(CSSColorUtils.PADDING, "0");
					detailInscriptionLayout.add(regime);
					detailInscriptionLayout.add(statut);
					detailInscriptionLayout.add(paiement);
					detailInscriptionLayout.add(pieces);

					// Layout photo
					photoButton.getStyle().set(CSSColorUtils.MARGIN_LEFT, "1em");
					photoLayout.addComponentAsFirst(photoButton);

					//Layout des boutons
					FlexLayout buttonExportLayout = new FlexLayout();
					buttonExportLayout.setSizeUndefined();
					buttonExportLayout.getStyle().set(CSSColorUtils.PADDING, "0");
					buttonExportLayout.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
					buttonExportLayout.setFlexWrap(FlexWrap.WRAP);

					if(configController.isCertificatActif()) {
						setAnchorStyle(exportCertificatAnchor);
						buttonExportLayout.add(exportCertificatAnchor);
						if(inscriptionEnCours && !inscriptionValide) {
							exportCertificatAnchor.setVisible(false);
						}
					}

					if(configController.isAttestationPaiementActif()) {
						setAnchorStyle(exportAttestationAnchor);
						buttonExportLayout.add(exportAttestationAnchor);
						if(inscriptionEnCours && !inscriptionPayee) {
							exportAttestationAnchor.setVisible(false);
						}
					}
					if(configController.isCertificatActif() && configController.isAttestationPaiementActif()) {
						buttonExportLayout.setFlexBasis("15em", exportCertificatAnchor,exportAttestationAnchor);
					}

					flexInfoAndPhotoLayout.getStyle().set(CSSColorUtils.BORDER_TOP, CSSColorUtils.SOLID_LIGHTGRAY);
					flexInfoAndPhotoLayout.add(detailInscriptionLayout);
					flexInfoAndPhotoLayout.add(photoLayout);

					FlexLayout flexDropDownButtonLayout= new FlexLayout();
					flexDropDownButtonLayout.setVisible(false);
					flexDropDownButtonLayout.getStyle().set("padding-bottom","1em");
					flexDropDownButtonLayout.setWidthFull();

					if(afficherDetailInscription.equals(Utils.DETAIL_INS_NON_AFFICHE) || afficherDetailInscription.equals(Utils.DETAIL_INS_VIA_BOUTON) ) {
						verticalInfoPhotoAndExportLayout.setVisible(false);
						if(afficherDetailInscription.equals(Utils.DETAIL_INS_VIA_BOUTON)) {
							Button displayDetailButton=new Button("", VaadinIcon.ANGLE_DOWN.create());
							displayDetailButton.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
							displayDetailButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.BTN_COLOR);

							Button hideDetailButton=new Button("", VaadinIcon.ANGLE_UP.create());
							hideDetailButton.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
							hideDetailButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.BTN_COLOR);

							displayDetailButton.addClickListener(c-> {
								verticalInfoPhotoAndExportLayout.setClassName("diplayedpanel");
								hideDetailButton.setVisible(true);
								displayDetailButton.setVisible(false);
								verticalInfoPhotoAndExportLayout.setVisible(true);
							});
							listButtonDetailInscription.add(displayDetailButton);
							flexDropDownButtonLayout.add(displayDetailButton);

							hideDetailButton.addClickListener(c-> {
								verticalInfoPhotoAndExportLayout.setClassName("hiddenpanel");
								//flexDropDownButtonLayout.setVisible(true);
								hideDetailButton.setVisible(false);
								displayDetailButton.setVisible(true);
								//verticalInfoPhotoAndExportLayout.setVisible(false);
							});
							hideDetailButton.setVisible(false);
							listButtonMasquerInscription.add(hideDetailButton);
							flexDropDownButtonLayout.add(hideDetailButton);
							flexDropDownButtonLayout.setVisible(true);
						}
					}
					flexInfoAndPhotoLayout.setWidthFull();
					flexInfoAndPhotoLayout.setJustifyContentMode(JustifyContentMode.START);
					flexInfoAndPhotoLayout.setFlexWrap(FlexWrap.WRAP);
					flexInfoAndPhotoLayout.setFlexBasis("18em", detailInscriptionLayout);
					verticalInfoPhotoAndExportLayout.add(flexInfoAndPhotoLayout);
					verticalInfoPhotoAndExportLayout.add(buttonExportLayout);
					verticalLayout.add(verticalInfoPhotoAndExportLayout);
					if(flexDropDownButtonLayout.isVisible()) {
						verticalLayout.add(flexDropDownButtonLayout);
					}

					// Cursus
					Button cursusButton = new Button("", VaadinIcon.ARCHIVE.create());
					if(configController.isCursusActif()) {
						Dialog cursusDialog = new Dialog();
						cursusDialog.setWidthFull();
						cursusDialog.setMaxWidth("50em");
						cursusButton.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
						cursusButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.BTN_COLOR);
						cursusButton.addClickListener(c-> {
							// Si le cursus n'est pas visible
							if(!cursusDialog.isOpened()) {
								//Init dialog (a faire au clic car dépend de la taille de la fenêtre)
								cursusDialog.removeAll();
								FlexLayout dialogLayout = new FlexLayout();
								dialogLayout.setFlexDirection(FlexDirection.COLUMN);
								dialogLayout.setHeightFull();
								VerticalLayout cursusLayout = new VerticalLayout();
								cursusLayout.setPadding(false);
								HorizontalLayout headerDialog= new HorizontalLayout();
								headerDialog.getStyle().set(CSSColorUtils.MARGIN_BOTTOM, CSSColorUtils.VAR_LUMO_SPACE_L);
								Label titreDialog = new Label(libelleInscription);
								titreDialog.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
								titreDialog.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.SECOND_COLOR);
								Button closeButton = new Button(getTranslation("inscription.closedialog"));
								closeButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.BTN_COLOR);
								headerDialog.add(titreDialog);
								dialogLayout.add(headerDialog);
								dialogLayout.add(cursusLayout);
								cursusDialog.add(dialogLayout);
								// si écran de petite taille
								if( windowWidth<=800) {
									HorizontalLayout footerDialog= new HorizontalLayout();
									footerDialog.getStyle().set(CSSColorUtils.MARGIN_TOP, CSSColorUtils.VAR_LUMO_SPACE_L);
									footerDialog.add(closeButton);
									closeButton.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
									dialogLayout.add(footerDialog);
									cursusDialog.setSizeFull();
								} else {
									cursusDialog.setHeight(CSSColorUtils.AUTO);
									cursusDialog.setDraggable(true);
									headerDialog.add(closeButton);
								}

								closeButton.addClickListener(cb -> cursusDialog.close());

								// Mise à jour de l'affichage du cursus
								displayCursus(dossier.getApprenant().getCode(), inscription.getCible().getFormation().getCode(), inscription.getCible().getCode(), Utils.getCodePeriode(inscription),cursusLayout);
								cursusDialog.open();
							} else {
								// On masque le cursus
								cursusDialog.close();
								cursusDialog.removeAll();
							}
						});
						// Ajout à la liste des boutons
						listButtonCursus.add(cursusButton);
					}



					// Notes et résultats
					Button notesButton = new Button("", VaadinIcon.DIPLOMA.create());
					if(configController.isNotesActif()) {
						Dialog notesDialog = new Dialog();
						notesDialog.setWidthFull();
						notesDialog.setMaxWidth("70em");						
						notesButton.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
						notesButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.BTN_COLOR);
						notesButton.addClickListener(c-> {
							// Si le notes n'est pas visible
							if(!notesDialog.isOpened()) {
								//Init dialog (a faire au clic car dépend de la taille de la fenêtre)
								notesDialog.removeAll();
								FlexLayout dialogLayout = new FlexLayout();
								dialogLayout.setFlexDirection(FlexDirection.COLUMN);
								dialogLayout.setHeightFull();
								VerticalLayout notesLayout = new VerticalLayout();
								notesLayout.setPadding(false);
								HorizontalLayout headerDialog= new HorizontalLayout();
								headerDialog.getStyle().set("margin-bottom", CSSColorUtils.VAR_LUMO_SPACE_L);
								Label titreDialog = new Label(libelleInscription);
								titreDialog.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
								titreDialog.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.SECOND_COLOR);
								Button closeButton = new Button(getTranslation("inscription.closedialog"));
								closeButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.BTN_COLOR);
								headerDialog.add(titreDialog);
								dialogLayout.add(headerDialog);
								dialogLayout.add(notesLayout);
								notesDialog.add(dialogLayout);
								// si écran de petite taille
								boolean smallGrid=false;
								if( windowWidth<=800) {
									smallGrid=true;
									HorizontalLayout footerDialog= new HorizontalLayout();
									footerDialog.getStyle().set(CSSColorUtils.MARGIN_TOP, CSSColorUtils.VAR_LUMO_SPACE_L);
									footerDialog.add(closeButton);
									closeButton.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
									dialogLayout.add(footerDialog);
									notesDialog.setSizeFull();
								} else {
									notesDialog.setHeight(CSSColorUtils.AUTO);
									notesDialog.setDraggable(true);
									headerDialog.add(closeButton);
								}

								closeButton.addClickListener(cb -> notesDialog.close());

								// Mise à jour de l'affichage des notes
								displayNotes(dossier.getApprenant().getCode(), Utils.getCodeChemin(inscription.getCible()), Utils.getCodePeriode(inscription),notesLayout, smallGrid);
								notesDialog.open();
							} else {
								// On masque le notes
								notesDialog.close();
								notesDialog.removeAll();
							}
						});
						// Ajout à la liste des boutons
						listButtonNotes.add(notesButton);
					}

					//Layout des boutons concernant le cursus et les notes
					FlexLayout buttonLayout2 = new FlexLayout();
					buttonLayout2.setWidthFull();
					buttonLayout2.getStyle().set(CSSColorUtils.PADDING, "0");
					buttonLayout2.getStyle().set("padding-top", "1em");
					buttonLayout2.getStyle().set(CSSColorUtils.BORDER_TOP, CSSColorUtils.SOLID_LIGHTGRAY);
					buttonLayout2.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
					Div cursusBtnDiv=new Div();
					cursusBtnDiv.getStyle().set(CSSColorUtils.PADDING, "0.3em 1em 0.3em 1em");
					cursusBtnDiv.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
					Div notesBtnDiv=new Div();
					notesBtnDiv.getStyle().set(CSSColorUtils.PADDING, "0.3em 1em 0.3em 1em");
					notesBtnDiv.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
					cursusBtnDiv.add(cursusButton);
					notesBtnDiv.add(notesButton);
					if(configController.isCursusActif()) {
						buttonLayout2.add(cursusBtnDiv);
					}
					if(configController.isNotesActif()) {
						buttonLayout2.add(notesBtnDiv);
					}
					verticalLayout.add(buttonLayout2);
					cursusButton.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
					notesButton.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
					buttonLayout2.setFlexWrap(FlexWrap.WRAP);


					insCard.addAlt(verticalLayout);

					insCard.displayAlt();
					inscriptionsLayout.add(insCard);
				}
			}
		}
		updateStyle();
	}



	private void setAnchorStyle(Anchor anchor) {
		anchor.setMinWidth("15em");
		anchor.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		anchor.getStyle().set(CSSColorUtils.PADDING, "1em");
	}


	private String formatEtat(String value) {
		if(StringUtils.hasText(value)) {
			value = value.replace("_", " ");
			value = Character.toUpperCase(value.charAt(0)) + value.substring(1).toLowerCase();
		}
		return value;
	}


	private void displayCursus(String codeApprenant, String  codeFormation, String codeRacine, String codePeriode, VerticalLayout cursusLayout) {
		log.info("Récupération du cursus pour {} sur {} > {}", codeApprenant, codeFormation, codeRacine);

		//Récupération du cursus
		List<ObjetMaquetteDTO> listObj = pegaseController.getCursus(codeApprenant, codeFormation, codeRacine, codePeriode);

		// clean du layout
		cursusLayout.removeAll();

		// Création de la TreeGrid contenant l'arborescence des objets de formation
		TreeGrid<ObjetMaquetteDTO> arbo = new TreeGrid<>();
		arbo.setItems(listObj, ObjetMaquetteDTO::getChildObjects);
		arbo.addComponentHierarchyColumn(this::getObjetCursusLibelle).setFlexGrow(1).setAutoWidth(true).setWidth("100%");
		arbo.addComponentColumn(this::getObjetCursusDetails).setFlexGrow(0);
		arbo.expandRecursively(listObj, 10);
		arbo.setAllRowsVisible(false);
		// si écran de petite taille
		if( windowWidth<=800) {
			cursusLayout.setSizeFull();
		}
		arbo.setWidthFull();
		cursusLayout.add(arbo);
	}


	private void displayNotes(String codeApprenant, String codeChemin, String codePeriode, VerticalLayout notesLayout, boolean smallGrid) {
		log.info("Récupération des notes pour {} sur {}", codeApprenant, codeChemin);

		// Récupération des notes
		List<CheminDTO> listObj = pegaseController.getNotes(codeApprenant, codeChemin, codePeriode, avecControle);

		notesLayout.removeAll();

		// Création de la TreeGrid contenant l'arborescence des objets de formation
		TreeGrid<CheminDTO> arbo = new TreeGrid<>();
		arbo.setItems(listObj, CheminDTO::getChildObjects);
		arbo.addComponentHierarchyColumn(this::getObjetNotesLibelle).setFlexGrow(50).setAutoWidth(true).setWidth("100%");
		arbo.addComponentColumn(this::getSessionsDetails).setFlexGrow(1).setAutoWidth(true);
		arbo.setSelectionMode(SelectionMode.SINGLE);
		arbo.addItemClickListener(o -> showDetailNoteDialog(o.getItem()));

		if(smallGrid) {
			arbo.setThemeName("mobile");
			arbo.addClassName("mdw-small-grid");
		}
		arbo.expandRecursively(listObj, 10);
		arbo.setAllRowsVisible(false);
		// si écran de petite taille
		if( windowWidth<=800) {
			notesLayout.setSizeFull();
		}
		arbo.setWidthFull();
		notesLayout.add(arbo);
	}


	/**
	 * 
	 * @param o
	 * @return Element de la colonne libellé du cursus
	 */
	private Component getObjetCursusLibelle(ObjetMaquetteDTO o) {
		FlexLayout l = new FlexLayout();
		l.setWidthFull();

		Label libLabel = new Label(o.getLibelle());
		libLabel.getStyle().set(CSSColorUtils.WHITE_SPACE, "normal");
		l.add(libLabel);
		l.setFlexGrow(1, libLabel);


		// Si non obligatoire
		if(facItalique.booleanValue() && o.getObjet() != null && o.getObjet().getEstObligatoire()!=null && !o.getObjet().getEstObligatoire().booleanValue()) {
			// Le libellé est en italic
			libLabel.getStyle().set(CSSColorUtils.FONT_STYLE, CSSColorUtils.ITALIC);
		}

		return l;
	}

	/**
	 * 
	 * @param o
	 * @return Element de la colonne "Acquis" du cursus
	 */
	private Component getObjetCursusDetails(ObjetMaquetteDTO o) {
		FlexLayout l = new FlexLayout();
		l.setWidthFull();

		if(o!=null && o.getAcquis()!=null && o.getAcquis().booleanValue()) {
			Button bAcquis = new Button(VaadinIcon.CHECK.create());
			bAcquis.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.BTN_COLOR);
			bAcquis.getStyle().set(CSSColorUtils.MARGIN_RIGHT, CSSColorUtils.EM_0_5);
			bAcquis.setHeight(CSSColorUtils.EM_1_5);
			bAcquis.addClickListener(e -> showInfoDialog(getTranslation("inscription.element.acquis")));
			l.add(bAcquis);
		}
		// Si il y a des aménagements
		if(o!=null && o.getObjet()!=null && o.getObjet().getAmenagements()!=null &&  !o.getObjet().getAmenagements().isEmpty()) {
			Button bAmenagement = new Button(VaadinIcon.INFO_CIRCLE_O.create());
			bAmenagement.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.BTN_COLOR);
			bAmenagement.setHeight(CSSColorUtils.EM_1_5);
			bAmenagement.getStyle().set(CSSColorUtils.MARGIN_RIGHT, CSSColorUtils.EM_0_5);
			bAmenagement.addClickListener(e -> showDetailAmenagementDialog(o.getObjet().getAmenagements()));

			l.add(bAmenagement);
		}
		return l;
	}


	private void showInfoDialog(String info) {

		// Création dialog avec le détail des aménagements
		Dialog resultDialog = new Dialog();
		VerticalLayout dialLayout = new VerticalLayout();

		HorizontalLayout hl = new HorizontalLayout();
		hl.setWidthFull();
		Label libAmenagements = new Label(info);
		libAmenagements.getStyle().set(CSSColorUtils.FONT_WEIGHT, "bold");
		hl.add(libAmenagements);
		hl.add(libAmenagements);
		dialLayout.add(hl);

		resultDialog.add(dialLayout);
		resultDialog.open();
	}


	private void showDetailAmenagementDialog(List<AmenagementDCA> amenagements) {

		// Création dialog avec le détail des aménagements
		Dialog resultDialog = new Dialog();
		VerticalLayout dialLayout = new VerticalLayout();
		Label formationLabel = new Label(getTranslation("inscription.element.amenagement"));
		formationLabel.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		formationLabel.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.SECOND_COLOR);
		dialLayout.add(formationLabel);


		for(AmenagementDCA amenagement : amenagements) {
			HorizontalLayout hl = new HorizontalLayout();
			hl.setWidthFull();
			Label libAmenagements = new Label(amenagement.getLibelleAffichage());
			libAmenagements.getStyle().set(CSSColorUtils.FONT_WEIGHT, "bold");
			hl.add(libAmenagements);
			hl.add(libAmenagements);
			dialLayout.add(hl);
		}

		resultDialog.add(dialLayout);
		resultDialog.open();
	}




	/**
	 * 
	 * @param o
	 * @return Element de la colonne libelle du chemin
	 */
	private Component getObjetNotesLibelle(CheminDTO o) {
		FlexLayout l = new FlexLayout();
		l.setWidthFull();

		Div libLabel = new Div();
		libLabel.setText(o.getLibelle());
		libLabel.getStyle().set(CSSColorUtils.WHITE_SPACE, "normal");
		l.add(libLabel);
		l.setFlexGrow(1, libLabel);

		return l;
	}

	/**
	 * 
	 * @param o
	 * @return Element de la colonne "Notes" du chemin
	 */
	private Component getSessionsDetails(CheminDTO o) {
		// Préparation du layout à retourner
		FlexLayout l = new FlexLayout();
		l.setWidthFull();

		// Si l'objet est de type Contrôle
		if(o.getControle() != null) {
			FlexLayout controleLayout = getSessionControleDetails(o,true);
			if(controleLayout.getComponentCount()>0) {
				// Si note controle n'appartient pas à la session 1
				if(o.getControle().getNumeroSession().intValue() > 1) {
					// Ajout d'un espacement pour décaler le résultat du contrôle dans la colonne correspondant à la session
					for( int i = 1 ; i < o.getControle().getNumeroSession().intValue(); i++ ) {
						FlexLayout fl = new FlexLayout();
						fl.setWidth("7em");
						l.add(fl);
					}
				}
				l.add(controleLayout);
			} else {
				l.add(getAucunResultat());
			}
		} else {
			if(o.getObjet()!=null) {
				Component sessionfinalelayout = getSessionFinaleDetails(o, true);
				Component session2layout = getSession2Details(o, true);
				Component session1layout = getSession1Details(o, true);
				Component capitalise = getCapitaliseDetails(o, true);

				if(session1layout != null) {
					l.add(session1layout);
				}
				if(session2layout != null) {
					l.add(session2layout);
				}
				if(sessionfinalelayout != null) {
					l.add(sessionfinalelayout);
				}
				if(session1layout == null && session2layout == null && sessionfinalelayout == null) {
					l.add(getAucunResultat());
				}
				if(capitalise != null) {
					l.add(capitalise);
				}

			}
		}
		return l;
	}


	private Component getAucunResultat() {
		Div aucunResultat = new Div();
		aucunResultat.setText(getTranslation("notes.aucune"));
		aucunResultat.getStyle().set(CSSColorUtils.FONT_STYLE, CSSColorUtils.ITALIC);
		aucunResultat.getStyle().set("font-size", "smaller");
		aucunResultat.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		return aucunResultat;
	}


	private void showDetailNoteDialog(CheminDTO o) {
		// Création dialog avec le détail des notes et résultat pour l'objet de formation
		Dialog resultDialog = new Dialog();
		VerticalLayout dialLayout = new VerticalLayout();
		Label formationLabel = new Label(o.getLibelle());
		Label formationLabelPere = new Label(o.getObjet().getObjetFeuille().getLibelleCourt());
		formationLabel.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		formationLabel.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.SECOND_COLOR);
		formationLabelPere.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
		formationLabelPere.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.SECOND_COLOR);
		// Si c'est un contrôle
		if(o.getControle()!=null) {
			// on affiche le libellé de l'élément parent
			dialLayout.add(formationLabelPere);
		}
		dialLayout.add(formationLabel);


		// S'il s'agit d'un objet de type contrôle
		if(o.getControle()!=null) {
			FlexLayout controleLayout = getSessionControleDetails(o, false);
			if(controleLayout.getComponentCount()>0) {
				HorizontalLayout session1 = new HorizontalLayout();
				session1.setWidthFull();
				Label labelS1 = new Label(getTranslation("notes.session."+o.getControle().getNumeroSession()));
				labelS1.getStyle().set(CSSColorUtils.WHITE_SPACE, CSSColorUtils.NOWRAP);
				labelS1.getStyle().set(CSSColorUtils.FONT_WEIGHT, "bold");
				session1.add(labelS1);
				session1.add(controleLayout);
				dialLayout.add(session1);
			} else {
				dialLayout.add(getAucunResultat());
			}

		} else {
			Component sf = getSessionFinaleDetails(o, false);
			Component s2 = getSession2Details(o, false);
			Component s1 = getSession1Details(o, false);
			Component ac = getCapitaliseDetails(o, false);

			//Ajout du coeff principal
			BigDecimal coeff=getCoeff(o);
			if(coeff!=null && avecCoeff!=null && avecCoeff.booleanValue()) {
				HorizontalLayout hl = new HorizontalLayout();
				hl.setWidthFull();
				Label libCoeffLabel = new Label(getTranslation("notes.coeff"));
				libCoeffLabel.getStyle().set(CSSColorUtils.FONT_WEIGHT, "bold");
				hl.add(libCoeffLabel);
				Label coeffLabel = new Label(Utils.displayBigDecimal(coeff));
				coeffLabel.setWidthFull();
				hl.add(libCoeffLabel);
				hl.add(coeffLabel);
				dialLayout.add(hl);
			}
			// Ajout des crédits ECTS
			if(o.getObjet().getCreditEcts()!=null && avecECTS) {
				HorizontalLayout hl = new HorizontalLayout();
				hl.setWidthFull();
				Label libECTSLabel = new Label(getTranslation("notes.ects"));
				libECTSLabel.getStyle().set(CSSColorUtils.FONT_WEIGHT, "bold");
				hl.add(libECTSLabel);
				Label ectsLabel = new Label(Utils.displayBigDecimal(o.getObjet().getCreditEcts()));
				ectsLabel.setWidthFull();
				hl.add(libECTSLabel);
				hl.add(ectsLabel);
				dialLayout.add(hl);
			}

			boolean aucunResultatSession2=true;
			boolean aucunResultat=true;
			// Ajout des infos de session 1
			if(s1 != null) {
				HorizontalLayout session1 = new HorizontalLayout();
				session1.setWidthFull();
				Label labelS1 = new Label(getTranslation("notes.session.1"));
				labelS1.getStyle().set(CSSColorUtils.WHITE_SPACE, CSSColorUtils.NOWRAP);
				labelS1.getStyle().set(CSSColorUtils.FONT_WEIGHT, "bold");
				session1.add(labelS1);
				session1.add(s1);
				dialLayout.add(session1);
				aucunResultat=false;
			}
			// Ajout des infos de session 2
			if(s2 != null) {
				HorizontalLayout session2 = new HorizontalLayout();
				session2.setWidthFull();
				Label labelS2 = new Label(getTranslation("notes.session.2"));
				labelS2.getStyle().set(CSSColorUtils.WHITE_SPACE, CSSColorUtils.NOWRAP);
				labelS2.getStyle().set(CSSColorUtils.FONT_WEIGHT, "bold");
				session2.add(labelS2);
				session2.add(s2);
				dialLayout.add(session2);
				aucunResultat=false;
				aucunResultatSession2=false;
			}
			// Ajout des infos de session finale
			if(sf != null) {
				HorizontalLayout sessionFinale = new HorizontalLayout();
				sessionFinale.setWidthFull();
				Label labelSF = new Label(getTranslation("notes.session.finale"));
				labelSF.getStyle().set(CSSColorUtils.FONT_WEIGHT, "bold");
				sessionFinale.add(labelSF);
				sessionFinale.add(sf);
				dialLayout.add(sessionFinale);
				aucunResultat=false;
			}

			// Si aucun résultat
			if(aucunResultat) {
				HorizontalLayout aucunResLayout = new HorizontalLayout();
				aucunResLayout.setWidthFull();
				Div aucunResDiv = new Div();
				aucunResDiv.setText(getTranslation("notes.aucune"));
				aucunResDiv.getStyle().set(CSSColorUtils.FONT_STYLE, CSSColorUtils.ITALIC);
				aucunResDiv.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);
				aucunResLayout.add(aucunResDiv);
				dialLayout.add(aucunResLayout);
			}
			// Si concerné par session 2
			if(o.getObjet().getConcerneParSession2()!=null && o.getObjet().getConcerneParSession2().booleanValue() && aucunResultatSession2) {
				HorizontalLayout concerneSession2Layout = new HorizontalLayout();
				concerneSession2Layout.setWidthFull();
				Div concerneSession2Div = new Div();
				concerneSession2Div.setText(getTranslation("notes.concerne.session2"));
				concerneSession2Div.getStyle().set(CSSColorUtils.FONT_STYLE, CSSColorUtils.ITALIC);
				concerneSession2Layout.add(concerneSession2Div);
				dialLayout.add(concerneSession2Layout);
			}
			// Si capitalisé
			if(ac != null) {
				HorizontalLayout hlac = new HorizontalLayout();
				hlac.setWidthFull();
				hlac.add(ac);
				dialLayout.add(hlac);
			}

		}
		resultDialog.add(dialLayout);
		resultDialog.open();
	}


	// Retourne le dernier coeff
	private BigDecimal getCoeff(CheminDTO o) {
		if(o!=null && o.getObjet()!=null) {
			if(o.getObjet().getCoefficientFinal()!=null) {
				return o.getObjet().getCoefficientFinal();
			}
			if(o.getObjet().getCoefficientSession1()!=null) {
				return o.getObjet().getCoefficientSession1();
			}
			if(o.getObjet().getCoefficientSession2()!=null) {
				return o.getObjet().getCoefficientSession2();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @param compact
	 * @return Element de la colonne "Notes" du contrôle
	 */
	private FlexLayout getSessionControleDetails(CheminDTO o, boolean compact) {
		FlexLayout l = new FlexLayout();
		if(compact) {
			l.setMaxWidth("7em");
		}
		// Si le controle est non null et publiable
		if(o!=null && o.getControle()!=null && o.getControle().getPublie()) {
			if(o.getControle().getNote()!=null || o.getControle().getAbsence()!=null) {
				l.add(createLabelNote(o.getObjet().getBareme(), o.getControle().getNote(), o.getControle().getAbsence(), compact));
			}
			if(o.getControle().getResultat()!=null) {
				l.add(createLabelResult(compact ? o.getControle().getResultat().getLibelleCourt() : o.getControle().getResultat().getLibelleAffichage()));
			}
		}
		l.getStyle().set(CSSColorUtils.FLEW_FLOW, CSSColorUtils.ROW_WRAP);
		return l;
	}

	/**
	 * 
	 * @param o
	 * @param compact
	 * @return Element de la colonne "Notes" du chemin
	 */
	private Component getSession1Details(CheminDTO o, boolean compact) {
		FlexLayout l = new FlexLayout();
		if(compact) {
			l.setMaxWidth("7em");
		}

		//Si l'objet est non null est que les info de session1 sont publiables
		if(o!=null && o.getObjet()!=null && o.getObjet().getPublieSession1()) {
			if(o.getObjet().getNoteSession1()!=null || o.getObjet().getAbsenceSession1()!=null) {
				l.add(createLabelNote(o.getObjet().getBareme(), o.getObjet().getNoteSession1(), o.getObjet().getAbsenceSession1(), compact));
			}
			// Si on a un résultat de session1
			if(o.getObjet().getResultatSession1()!=null) {
				l.add(createLabelResult(compact ? o.getObjet().getResultatSession1().getLibelleCourt() : o.getObjet().getResultatSession1().getLibelleAffichage()));
			}
		}
		l.getStyle().set(CSSColorUtils.FLEW_FLOW, CSSColorUtils.ROW_WRAP);
		l.getStyle().set(CSSColorUtils.FLEX_DIRECTION, CSSColorUtils.COLUMN);

		if(l.getComponentCount()>0) {
			return l;
		}
		return null;
	}


	/**
	 * 
	 * @param o
	 * @param compact
	 * @return Element "capitalise"
	 */
	private Component getCapitaliseDetails(CheminDTO o, boolean compact) {
		FlexLayout l = new FlexLayout();
		if(compact) {
			l.setMaxWidth("7em");
		}

		// Si l'objet est capitalisé et qu'un libellé court est défini
		if(o!=null && o.getObjet()!=null && o.getObjet().getAcquisCapitalise() != null && o.getObjet().getAcquisCapitalise().booleanValue() 
			&& (compact ?  StringUtils.hasText(getTranslation("inscription.element.capitalise.court")) : StringUtils.hasText(getTranslation("inscription.element.capitalise.long")))) {
			l.add(createLabelResult(compact ? getTranslation("inscription.element.capitalise.court") : getTranslation("inscription.element.capitalise.long")));
		}

		l.getStyle().set(CSSColorUtils.FLEW_FLOW, CSSColorUtils.ROW_WRAP);
		l.getStyle().set(CSSColorUtils.FLEX_DIRECTION, CSSColorUtils.COLUMN);
		l.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO);

		if(l.getComponentCount()>0) {
			return l;
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @param compact
	 * @return Element de la colonne "Notes" du chemin
	 */
	private Component getSession2Details(CheminDTO o, boolean compact) {
		FlexLayout l = new FlexLayout();
		if(compact) {
			l.setMaxWidth("7em");
		}

		//Si l'objet est non null est que les info de session2 sont publiables
		if(o!=null && o.getObjet()!=null && o.getObjet().getPublieSession2()) {
			if(o.getObjet().getNoteSession2()!=null || o.getObjet().getAbsenceSession2()!=null) {
				l.add(createLabelNote(o.getObjet().getBareme(),o.getObjet().getNoteSession2(), o.getObjet().getAbsenceSession2(), compact));
			}
			if(o.getObjet().getResultatSession2()!=null) {
				l.add(createLabelResult(compact ? o.getObjet().getResultatSession2().getLibelleCourt() : o.getObjet().getResultatSession2().getLibelleAffichage()));
			}
		}

		l.getStyle().set(CSSColorUtils.FLEW_FLOW, CSSColorUtils.ROW_WRAP);
		l.getStyle().set(CSSColorUtils.FLEX_DIRECTION, CSSColorUtils.COLUMN);

		if(l.getComponentCount()>0) {
			return l;
		}
		return null;
	}

	/**
	 * 
	 * @param o
	 * @param compact
	 * @return Element de la colonne "Notes" du chemin
	 */
	private Component getSessionFinaleDetails(CheminDTO o, boolean compact) {
		FlexLayout l = new FlexLayout();
		if(compact) {
			l.setMaxWidth("7em");
		}

		//Si l'objet est non null est que les info de session finale sont publiables
		if(o!=null && o.getObjet()!=null && o.getObjet().getPublieEvaluationsFinales()) {
			if(o.getObjet().getNoteFinale()!=null || o.getObjet().getAbsenceFinale()!=null) {
				l.add(createLabelNote(o.getObjet().getBareme(), o.getObjet().getNoteFinale(), o.getObjet().getAbsenceFinale(), compact));
			}
			if(o.getObjet().getResultatFinal()!=null) {
				l.add(createLabelResult(compact ? o.getObjet().getResultatFinal().getLibelleCourt() : o.getObjet().getResultatFinal().getLibelleAffichage()));
			}
		}

		l.getStyle().set(CSSColorUtils.FLEW_FLOW, CSSColorUtils.ROW_WRAP);
		l.getStyle().set(CSSColorUtils.FLEX_DIRECTION, CSSColorUtils.COLUMN);

		if(l.getComponentCount()>0) {
			return l;
		}
		return null;
	}

	private Component createLabelNote(Integer bareme, BigDecimal note, Absence absence, boolean compact) {
		Div result = new Div();
		result.setHeight(CSSColorUtils.EM_1_5);
		if(compact) {
			result.setWidth("5em");
		}
		result.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO_1EM);
		if(note != null) {
			result.setText(Utils.displayNote(note, bareme, avecBareme));
		} else {
			if(absence != null) {
				result.setText(compact ? getTranslation("notes.absence.prefix") + absence.getValue().charAt(0) : getTranslation("notes.absence") + " " + absence.getValue());
			}
		}
		return result;
	}


	private Component createLabelResult(String libCourt) {
		Div result = new Div();
		result.setHeight(CSSColorUtils.EM_1_5);
		result.getStyle().set(CSSColorUtils.MARGIN, CSSColorUtils.AUTO_1EM);
		result.getStyle().set(CSSColorUtils.BACKGROUND_COLOR, CSSColorUtils.SECOND_COLOR);
		result.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.WHITE);
		result.getStyle().set(CSSColorUtils.PADDING_LEFT, CSSColorUtils.EM_0_5);
		result.getStyle().set(CSSColorUtils.PADDING_RIGHT, CSSColorUtils.EM_0_5);
		result.getStyle().set(CSSColorUtils.BORDER_RADIUS, "0.7em");

		if(StringUtils.hasText(libCourt)) {
			result.setText(libCourt);
		}

		return result;
	}



	/**
	 * met à jour le style de la carte
	 */
	protected void updateStyle() {

		List<Component> listComp = inscriptionsLayout.getChildren().collect(Collectors.toList());

		int cpt=0;
		for(Component c : listComp) {
			cpt++;
			Card insCard = (Card) c; 
			insCard.updateStyle();
			if(cpt<listComp.size()) {
				insCard.addClassName("card-with-separator");
			}
		}

	}

}
