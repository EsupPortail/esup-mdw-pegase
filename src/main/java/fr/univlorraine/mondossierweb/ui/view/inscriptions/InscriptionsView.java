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
package fr.univlorraine.mondossierweb.ui.view.inscriptions;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import fr.univlorraine.mondossierweb.controllers.MainController;
import fr.univlorraine.mondossierweb.service.ExportService;
import fr.univlorraine.mondossierweb.service.PegaseService;
import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import fr.univlorraine.pegase.model.insgestion.ApprenantEtInscriptions;
import fr.univlorraine.pegase.model.insgestion.CibleInscription;
import fr.univlorraine.pegase.model.insgestion.InscriptionComplete;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class InscriptionsView extends VerticalLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver, HasUrlParameter<String> {

	private static final String CERT_FILE_EXT = ".pdf";
	private static final String CERT_FILE_NAME = "certificat";
	private static final String ATTEST_FILE_NAME = "attestation";
	private static final String ATTEST_FILE_EXT = ".pdf";


	@Value("#{'${pegase.inscription.statut}'.split(',')}") 
	private transient List<String> listeStatutsInscriptionAffichees;	
	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient MainController etudiantController;
	@Autowired
	private transient ExportService exportService;
	@Autowired
	private transient PegaseService pegaseService;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private int windowWidth;
	private final VerticalLayout inscriptionsLayout = new VerticalLayout();


	List<TextField> listTextFieldFormation = new LinkedList<TextField> ();
	List<TextField> listTextFieldPeriode = new LinkedList<TextField> ();
	List<TextField> listTextFieldRegime = new LinkedList<TextField> ();
	List<TextField> listTextFieldStatut = new LinkedList<TextField> ();
	List<TextField> listTextFieldPaiement = new LinkedList<TextField> ();
	List<TextField> listTextFieldPieces = new LinkedList<TextField> ();
	List<Button> listButtonCertificat = new LinkedList<Button> ();
	List<Button> listButtonAttestation = new LinkedList<Button> ();
	List<Button> listButtonPhoto = new LinkedList<Button> ();
	List<Button> listButtonCursus = new LinkedList<Button> ();

	Map<String,List<ObjetMaquetteDTO>> cursusMap = new HashMap<String,List<ObjetMaquetteDTO>>();

	@PostConstruct
	public void init() {
		setSizeFull();

		inscriptionsLayout.setWidthFull();
		inscriptionsLayout.getStyle().set("max-width", "52em");
		inscriptionsLayout.setJustifyContentMode(JustifyContentMode.EVENLY);
		//inscriptionsLayout.setFlexWrap(FlexWrap.WRAP);
		//inscriptionsLayout.getStyle().set("margin-top", "0");
		add(inscriptionsLayout);
		
		UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> { 
            windowWidth = details.getWindowInnerWidth();
            });
        UI.getCurrent().getPage().addBrowserWindowResizeListener(event -> {
            windowWidth = event.getWidth();
        });
	}


	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		log.info("localeChange");
		setViewTitle(getTranslation("inscriptions.title"));


		for(TextField tf : listTextFieldFormation) {
			tf.setLabel(getTranslation("inscription.formation"));
		}
		for(TextField tf : listTextFieldPeriode) {
			tf.setLabel(getTranslation("inscription.periode"));
		}
		for(TextField tf : listTextFieldRegime) {
			tf.setLabel(getTranslation("inscription.regime"));
		}
		for(TextField tf : listTextFieldStatut) {
			tf.setLabel(getTranslation("inscription.statut"));
		}
		for(TextField tf : listTextFieldPaiement) {
			tf.setLabel(getTranslation("inscription.paiement"));
		}
		for(TextField tf : listTextFieldPieces) {
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
		etudiantController.checkDossier();
		// Mise à jour de l'affichage
		updateData(etudiantController.getDossier()!=null ? etudiantController.getDossier() : null);
		//Force la maj des label
		localeChange(null);
	}

	/**
	 * Reset toutes les données affichées
	 * @param apprenant
	 */
	private void resetData() {
		inscriptionsLayout.removeAll();
		listTextFieldFormation.clear();
		listTextFieldPeriode.clear();
		listTextFieldRegime.clear();
		listTextFieldStatut.clear();
		listTextFieldPaiement.clear();
		listTextFieldPieces.clear();
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
		if(dossier!=null && dossier.getInscriptions() != null && !dossier.getInscriptions() .isEmpty()) {
			for(InscriptionComplete inscription : dossier.getInscriptions() ) {
				boolean inscriptionValide = false;
				boolean inscriptionPayee = false;
				boolean inscriptionAffichee = false;
				boolean inscriptionEnCours = false;
				CibleInscription cible = inscription.getCible();

				// Libellé de la carte
				String libelleInscription = cible.getLibelleCourt() != null ? cible.getLibelleCourt() : cible.getFormation().getLibelleLong() ;
				Card insCard = new Card(VaadinIcon.ACADEMY_CAP.create(),libelleInscription, true);

				// FORMATION
				TextField formation = new TextField();
				formation.setVisible(false);
				if(cible.getFormation()!=null) {
					CmpUtils.valueAndVisibleIfNotNull(formation,cible.getFormation().getLibelleLong());
				}
				formation.setReadOnly(true);
				CmpUtils.setLongTextField(formation);
				listTextFieldFormation.add(formation);


				// PERIODE
				TextField periode = new TextField();
				periode.setVisible(false);
				if(cible.getPeriode()!=null) {
					CmpUtils.valueAndVisibleIfNotNull(periode,cible.getPeriode().getLibelleAffichage());
					if(!Utils.estPassee(cible.getPeriode().getDateFin())) {
						inscriptionEnCours = true;
					}
				}
				periode.setReadOnly(true);
				CmpUtils.setLongTextField(periode);
				listTextFieldPeriode.add(periode);

				// REGIME
				TextField regime = new TextField();
				regime.setVisible(false);
				if(inscription.getRegimeInscription()!=null ) {
					CmpUtils.valueAndVisibleIfNotNull(regime,inscription.getRegimeInscription().getLibelle());
				}
				regime.setReadOnly(true);
				CmpUtils.setLongTextField(regime);
				listTextFieldRegime.add(regime);

				TextField statut = new TextField();
				statut.setVisible(false);
				if(inscription.getStatutInscription()!=null) {
					CmpUtils.valueAndVisibleIfNotNull(statut,inscription.getStatutInscription().getValue());
					if(inscription.getStatutInscription().getValue().equals(Utils.TEM_INS_VALIDE)) {
						inscriptionValide =  true;
					}
					if(listeStatutsInscriptionAffichees != null && listeStatutsInscriptionAffichees.contains(inscription.getStatutInscription().getValue())) {
						inscriptionAffichee = true;
					}
				}
				if(inscriptionAffichee) {

					statut.setReadOnly(true);
					CmpUtils.setShortTextField(statut);
					listTextFieldStatut.add(statut);

					TextField paiement = new TextField();
					paiement.setVisible(false);
					if(inscription.getStatutPaiement()!=null) {
						CmpUtils.valueAndVisibleIfNotNull(paiement,inscription.getStatutPaiement().getValue());
						if(inscription.getStatutPaiement().getValue().equals(Utils.TEM_INS_PAYEE)) {
							inscriptionPayee =  true;
						}
					}
					paiement.setReadOnly(true);
					CmpUtils.setShortTextField(paiement);
					listTextFieldPaiement.add(paiement);


					TextField pieces = new TextField();
					pieces.setVisible(false);
					if(inscription.getStatutPieces()!=null) {
						CmpUtils.valueAndVisibleIfNotNull(pieces,inscription.getStatutPieces().getValue());
					}
					pieces.setReadOnly(true);
					CmpUtils.setShortTextField(pieces);
					listTextFieldPieces.add(pieces);

					/* AJout de la liste des bourses et aides ?
				for( OccurrenceNomenclature occ : inscription.getBoursesEtAides()) {
					occ.getLibelle()
				} */


					// Ajout bouton certificat de scolarité
					Button certButton = new Button("", VaadinIcon.FILE_TEXT_O.create());
					certButton.setWidth("15em");
					certButton.getStyle().set("background-color", "#343a40");
					certButton.getStyle().set("color", "white");
					Anchor exportCertificatAnchor = new Anchor();
					exportCertificatAnchor.getStyle().set("margin-left", "0");
					exportCertificatAnchor.add(certButton);
					exportCertificatAnchor.setHref(new StreamResource(CERT_FILE_NAME +"-" + LocalDateTime.now() + CERT_FILE_EXT,
						() -> exportService.getCertificat(dossier.getApprenant().getCode(), Utils.getCodeVoeu(inscription))));
					exportCertificatAnchor.getElement().getStyle().set("margin-left", "1em");
					exportCertificatAnchor.setTarget("_blank");

					// Ajout à la liste des boutons
					listButtonCertificat.add(certButton);


					// Ajout bouton attestation de paiement
					Button attestationButton = new Button("", VaadinIcon.FILE_TEXT_O.create());
					attestationButton.setWidth("15em");
					attestationButton.getStyle().set("background-color", "#343a40");
					attestationButton.getStyle().set("color", "white");
					Anchor exportAttestationAnchor = new Anchor();
					exportAttestationAnchor.getStyle().set("margin-left", "0");
					exportAttestationAnchor.add(attestationButton);
					exportAttestationAnchor.setHref(new StreamResource(ATTEST_FILE_NAME +"-" + LocalDateTime.now() + ATTEST_FILE_EXT,
						() -> exportService.getAttestation(dossier.getApprenant().getCode(),  Utils.getCodeVoeu(inscription))));
					exportAttestationAnchor.getElement().getStyle().set("margin-left", "1em");
					exportAttestationAnchor.setTarget("_blank");

					// Ajout à la liste des boutons
					listButtonAttestation.add(attestationButton);


					// Ajout bouton photo
					Button photoButton = new Button("", VaadinIcon.USER.create());
					photoButton.setWidth("7em");
					photoButton.setHeight("8em");
					VerticalLayout photoLayout=new VerticalLayout();
					photoLayout.setSizeUndefined();
					photoLayout.getStyle().set("margin", "auto");
					photoLayout.getStyle().set("margin-top", "0");
					photoLayout.getStyle().set("padding", "0");
					photoButton.addClickListener(c-> {
						ByteArrayInputStream photo = exportService.getPhoto(dossier.getApprenant().getCode(),  Utils.getCodeVoeu(inscription));
						if(photo != null) {
							StreamResource resource = new StreamResource("photo_"+etudiantController.getDossierConsulte()+".jpg", () -> photo);
							Image image = new Image(resource, "photographie");
							image.setHeight("10em");
							photoLayout.removeAll();
							photoLayout.add(image);
							photoButton.setVisible(false);

						}
					});

					//Récupération de la photo automatiquement
					photoButton.click();

					// Ajout à la liste des boutons
					listButtonPhoto.add(photoButton);



					VerticalLayout verticalLayout = new VerticalLayout();
					verticalLayout.getStyle().set("padding", "0");
					verticalLayout.setSizeFull();

					VerticalLayout infoLayout = new VerticalLayout();
					infoLayout.getStyle().set("padding", "0");
					infoLayout.add(formation);
					infoLayout.add(periode);
					infoLayout.add(regime);
					verticalLayout.add(infoLayout);

					FlexLayout flexLayout = new FlexLayout();
					VerticalLayout statutLayout = new VerticalLayout();
					statutLayout.getStyle().set("padding", "0");
					statutLayout.add(statut);
					statutLayout.add(paiement);
					statutLayout.add(pieces);

					// Layout photo
					photoButton.getStyle().set("margin-left", "1em");
					photoLayout.addComponentAsFirst(photoButton);

					//Layout des boutons
					FlexLayout buttonLayout = new FlexLayout();
					buttonLayout.setSizeUndefined();
					buttonLayout.getStyle().set("padding", "0");
					buttonLayout.getStyle().set("margin", "auto");
					exportCertificatAnchor.setMinWidth("15em");
					exportCertificatAnchor.getStyle().set("margin", "auto");
					exportCertificatAnchor.getStyle().set("padding", "1em");
					buttonLayout.add(exportCertificatAnchor);
					exportAttestationAnchor.setMinWidth("15em");
					exportAttestationAnchor.getStyle().set("margin", "auto");
					exportAttestationAnchor.getStyle().set("padding", "1em");
					buttonLayout.add(exportAttestationAnchor);
					buttonLayout.setFlexWrap(FlexWrap.WRAP);
					buttonLayout.setFlexBasis("15em", exportCertificatAnchor,exportAttestationAnchor);
					if(inscriptionEnCours && !inscriptionValide) {
						exportCertificatAnchor.setVisible(false);
					}
					if(inscriptionEnCours && !inscriptionPayee) {
						exportAttestationAnchor.setVisible(false);
					}


					flexLayout.add(statutLayout);
					flexLayout.add(photoLayout);
					//flexLayout.add(buttonLayout);
					flexLayout.setWidthFull();
					flexLayout.setJustifyContentMode(JustifyContentMode.START);
					flexLayout.setFlexWrap(FlexWrap.WRAP);
					flexLayout.setFlexBasis("18em", statutLayout);
					//flexLayout.setFlexBasis("10em", buttonLayout);
					verticalLayout.add(flexLayout);
					verticalLayout.add(buttonLayout);

					Dialog cursusDialog = new Dialog();
					cursusDialog.setWidthFull();
					cursusDialog.setMaxWidth("50em");
					Button cursusButton = new Button("", VaadinIcon.SEARCH.create());
					cursusButton.getStyle().set("margin", "auto");
					cursusButton.getStyle().set("color", CSSColorUtils.MAIN_HEADER_COLOR);
					
					cursusButton.addClickListener(c-> {
						// Si le cursus n'est pas visible
						if(!cursusDialog.isOpened()) {
							
							//INIT dialog
							cursusDialog.removeAll();
							FlexLayout dialogLayout = new FlexLayout();
							dialogLayout.setFlexDirection(FlexDirection.COLUMN);
							dialogLayout.setHeightFull();
							VerticalLayout cursusLayout = new VerticalLayout();
							//cursusLayout.setVisible(false);
							cursusLayout.setPadding(false);
							HorizontalLayout headerDialog= new HorizontalLayout();
							Label titreDialog = new Label(libelleInscription);
							titreDialog.getStyle().set("margin", "auto");
							titreDialog.getStyle().set("color", CSSColorUtils.MAIN_HEADER_COLOR);
							Button closeButton = new Button("Fermer");
							closeButton.getStyle().set("color", CSSColorUtils.MAIN_HEADER_COLOR);
							headerDialog.add(titreDialog);
							dialogLayout.add(headerDialog);
							dialogLayout.add(cursusLayout);
							cursusDialog.add(dialogLayout);
							// si écran de petite taille
							if( windowWidth<=800) {
								HorizontalLayout footerDialog= new HorizontalLayout();
								footerDialog.add(closeButton);
								closeButton.getStyle().set("margin", "auto");
								closeButton.getStyle().set("margin-top", "0.5em");
								titreDialog.getStyle().set("margin-bottom", "0.5em");
								dialogLayout.add(footerDialog);
								cursusDialog.setSizeFull();
							} else {
								headerDialog.add(closeButton);
							}
							
							closeButton.addClickListener(cb -> { cursusDialog.close(); });
							
							// Mise à jour de l'affichage du cursus
							displayCursus(dossier.getApprenant().getCode(), inscription.getCible().getCodeChemin(), Utils.getCodePeriode(inscription),cursusLayout);
							//cursusButton.setIcon(VaadinIcon.SEARCH_MINUS.create());
							cursusDialog.open();
						} else {
							// On masque le cursus
							//cursusLayout.setVisible(false);
							//cursusButton.setIcon(VaadinIcon.SEARCH.create());
							cursusDialog.close();
							cursusDialog.removeAll();
						}
					});
					
					// Ajout à la liste des boutons
					listButtonCursus.add(cursusButton);
					verticalLayout.add(cursusButton);
					//verticalLayout.add(cursusLayout);

					insCard.addAlt(verticalLayout);

					insCard.displayAlt();
					inscriptionsLayout.add(insCard);
				}
			}
		}
		updateStyle();
	}



	private void displayCursus(String codeApprenant, String codeChemin, String codePeriode, VerticalLayout cursusLayout) {
		log.info("Récupération du cursus pour {} sur {}", codeApprenant, codeChemin);

		List<ObjetMaquetteDTO> listObj=new LinkedList<ObjetMaquetteDTO> ();
		String insKey = codeApprenant + "|" + codePeriode + "|" + codeChemin;
		// Gestion du cache des cursus en session
		if(cursusMap.containsKey(insKey)) {
			log.info("Récupération de la liste cursus dans la map");
			//Récupération de l'arborescence dans la map
			listObj = cursusMap.get(insKey);
		}else {
			log.info("Récupération de la liste cursus dans Pégase");
			// Correction du chemin pour en replaçant le séparateur
			String codeCheminChc = codeChemin.replaceAll("→", ">");
			// Récupération du cursus
			listObj = Utils.convertObjetMaquetteListToDTO(pegaseService.getCursus(codeApprenant, codePeriode), codeCheminChc);
			// suppression de la racine
			listObj = listObj.get(0).getChildObjects();
			log.info("sauvegarde de la liste cursus dans la map ({} elements)", listObj.size());
			// On stocke l'arborescence dans la map
			cursusMap.put(insKey, listObj);
		}
		cursusLayout.removeAll();

		// Création de la TreeGrid contenant l'arborescence des objets de formation
		TreeGrid<ObjetMaquetteDTO> arbo = new TreeGrid<ObjetMaquetteDTO>();
		arbo.setItems(listObj, ObjetMaquetteDTO::getChildObjects);
		arbo.addHierarchyColumn(ObjetMaquetteDTO::getLibelle).setFlexGrow(1).setAutoWidth(true);
		arbo.addComponentColumn(o -> getObjetDetails(o)).setFlexGrow(0);
		arbo.expandRecursively(listObj, 10);
		// si écran de petite taille
		if( windowWidth<=800) {
			arbo.setHeightByRows(false);
			//arbo.setWidthFull();
			//arbo.setHeightFull();
			cursusLayout.setSizeFull();
		}else {
			arbo.setHeightByRows(false);
		}
		cursusLayout.add(arbo);
	}


	/**
	 * 
	 * @param o
	 * @return Element de la colonne "Acquis" du cursus
	 */
	private Component getObjetDetails(ObjetMaquetteDTO o) {
		FlexLayout l = new FlexLayout();
		//l.setPadding(false);
		if(o!=null && o.getAcquis()!=null && o.getAcquis().booleanValue()) {
			Button bAcquis = new Button(VaadinIcon.CHECK.create());
			bAcquis.setHeight("1.5em");
			bAcquis.addClickListener(e -> Notification.show(getTranslation("inscription.element.acquis"),2000, Position.MIDDLE));
			l.add(bAcquis);
		}
		return l;
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
