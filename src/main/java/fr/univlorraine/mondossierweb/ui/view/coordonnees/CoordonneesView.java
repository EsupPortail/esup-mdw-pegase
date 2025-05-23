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
package fr.univlorraine.mondossierweb.ui.view.coordonnees;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import fr.univlorraine.mondossierweb.controllers.ConfigController;
import fr.univlorraine.mondossierweb.services.CasService;
import fr.univlorraine.mondossierweb.services.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.component.TextLabel;
import fr.univlorraine.mondossierweb.ui.layout.HasCodeApprenantUrlParameterView;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.CssUtils;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import fr.univlorraine.pegase.insext.model.Apprenant;
import fr.univlorraine.pegase.insext.model.ApprenantEtInscriptions;
import fr.univlorraine.pegase.insext.model.ContactAdresseComplet;
import fr.univlorraine.pegase.insext.model.ContactComplet;
import fr.univlorraine.pegase.insext.model.ContactMelComplet;
import fr.univlorraine.pegase.insext.model.ContactTelephoneComplet;
import fr.univlorraine.pegase.insext.model.DemandeDeContactSimple;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_GESTIONNAIRE})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
@Slf4j
public class CoordonneesView extends HasCodeApprenantUrlParameterView implements HasDynamicTitle, HasHeader, LocaleChangeObserver {

	private static final String NOM_TEL = "nomTel_";

	private static final String TEL = "tel_";

	private static final String NOM_MAIL = "nomMail_";

	private static final String MAIL = "mail_";

	private static final String NOM_ADRESSE = "nomAdresse_";

	private static final String PAYS_ADRESSE = "paysAdresse_";

	private static final String COMP1_ADRESSE = "compl1Adresse_";

	private static final String COMP2_ADRESSE = "compl2Adresse_";

	private static final String NUM_VOIE_ADRESSE = "numVoieAdresse_";

	private static final String LIEU_SERV_ADRESSE = "lieuServAdresse_";

	private static final String CODE_POSTAL_ADRESSE = "codePostalAdresse_";

	private static final String COMMUNE_ADRESSE = "communeAdresse_";
	@Getter
	private final TextHeader header = new TextHeader();
	private final VerticalLayout coordPersoLayout = new VerticalLayout();
	// label d'erreur
	private final NativeLabel errorLabel = new NativeLabel();
	@Autowired
	protected transient CasService casService;
	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient ConfigController configController;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	private transient Boolean afficherMailCas;
	@Getter
	private String pageTitle = "";
	// Carte pour les contacts (mail, tel) personnels
	private Card contacts;
	// Carte pour les contacts (mail, tel) d'urgence
	private Card contactsUrgence;

	private void initParameters() {
		afficherMailCas = configController.isEtudiantMailCasActif();
	}
	
	@PostConstruct
	private void init() {
		initParameters();
		
		setSizeFull();
		addClassName("view");
		getStyle().set("padding","0");
		errorLabel.getStyle().set("padding", "1em");
		
		coordPersoLayout.setWidthFull();
		coordPersoLayout.getStyle().set("max-width", "52em");
		coordPersoLayout.setJustifyContentMode(JustifyContentMode.EVENLY);

		add(coordPersoLayout);
	}

	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("coordonnees.title"));

		errorLabel.setText(getTranslation("error.unknown"));
		
		coordPersoLayout.getChildren().forEach(this::updateCardLocale );
		// Si on a une carte de contacts personnels
		if(contacts!=null) {
			// On met à jour son titre
			contacts.getTitre().setText(getTranslation("contacts.perso"));
		}
		// Si on a une carte de contacts d'urgence
		if(contactsUrgence!=null) {
			// On met à jour son titre
			contactsUrgence.getTitre().setText(getTranslation("contacts.urgence"));
		}

	}

	private void updateCardLocale(Component c) {
		c.getChildren().forEach(this::updateLayoutLocale);
	}

	private void updateLayoutLocale(Component c) {
		c.getChildren().forEach(this::updateFormLayoutLocale);
	}

	private void updateFormLayoutLocale(Component c) {
		c.getChildren().forEach(this::updateTextLabelLocale);
	}

	private void updateTextLabelLocale(Component c) {
		TextLabel t = null;

		try {
			t = (TextLabel) c;
		} catch (ClassCastException e) {
			log.debug("Le composant n'est pas un TextLabel");
		}

		if(t!=null && t.getId().isPresent()) {
			String id = t.getId().orElse("");
			String type = id.split("_")[0] + "_";
			switch(type) {
			case NOM_TEL :
				t.setLabel(getTranslation("tel.nom"));
				break;
			case TEL :
				t.setLabel(getTranslation("tel.libelle"));
				break;
			case NOM_MAIL :
				t.setLabel(getTranslation("mail.nom"));
				break;
			case MAIL :
				t.setLabel(getTranslation("mail.libelle"));
				break;
			case NOM_ADRESSE :
				t.setLabel(getTranslation("adresse.nom"));
				break;
			case PAYS_ADRESSE :
				t.setLabel(getTranslation("adresse.pays"));
				break;
			case COMP1_ADRESSE :
				t.setLabel(getTranslation("adresse.compl1"));
				break;
			case COMP2_ADRESSE :
				t.setLabel(getTranslation("adresse.compl2"));
				break;
			case NUM_VOIE_ADRESSE :
				t.setLabel(getTranslation("adresse.numvoie"));
				break;
			case LIEU_SERV_ADRESSE :
				t.setLabel(getTranslation("adresse.lieuservice"));
				break;
			case CODE_POSTAL_ADRESSE :
				t.setLabel(getTranslation("adresse.codepostal"));
				break;
			case COMMUNE_ADRESSE :
				t.setLabel(getTranslation("adresse.commune"));
				break;
			default:
				break;

			}
		}

	}


	private void setViewTitle(final String viewTitle) {
		pageTitle = pageTitleFormatter.format(viewTitle);
		getUI().map(UI::getPage).ifPresent(page -> page.setTitle(pageTitle));

		header.setText(viewTitle);
	}

	/**
	 * Reset toutes les données affichées
	 */
	private void resetData() {
		coordPersoLayout.removeAll();
		// Si on a une carte de contacts personnels
		if(contacts!=null) {
			// On supprime le contenu
			contacts.removeAll();
			contacts=null;
		}
		// Si on a une carte de contacts d'urgence
		if(contactsUrgence!=null) {
			// On supprime le contenu
			contactsUrgence.removeAll();
			contactsUrgence=null;
		}
	}

	@Override
	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	public void updateData(ApprenantEtInscriptions dossier) {
		resetData();
		if(dossier == null || dossier.getApprenant() == null ) {
			this.removeAll();
			add(errorLabel);
		} else {
			Apprenant apprenant = dossier.getApprenant();
			if (apprenant != null && apprenant.getContacts() != null && !apprenant.getContacts().isEmpty()) {
				int cpt = 0;
				// Pour chaque contact
				for (ContactComplet c : apprenant.getContacts()) {
					cpt++;
					// En fonction du type de contact
					switch (c.getCanalCommunication().getValue()) {
						case Utils.CANAL_CONTACT_ADRESSE:
							ajouterAdresse(c, cpt);
							break;
						case Utils.CANAL_CONTACT_MAIL:
							// Si mail de contact d'urgence
							if (StringUtils.hasText(c.getProprietaire())) {
								ajouterInfoContactUrgence(c, getTranslation("mail.libelle"));
							} else {
								// mail de l'étudiant
								ajouterInfoContactPerso(c);
							}
							break;
						case Utils.CANAL_CONTACT_TEL:
							// Si numéro du contact d'urgence
							if (StringUtils.hasText(c.getProprietaire())) {
								ajouterInfoContactUrgence(c, getTranslation("tel.libelle2"));
							} else {
								ajouterInfoContactPerso(c);
							}
							break;
						default:
							break;
					}

				}
				if (Boolean.TRUE.equals(afficherMailCas)) {
					Optional<String> mail = securityService.getMail();
					// Si on a récupéré un mail
					if (mail.isPresent() && StringUtils.hasText(mail.get())) {
						// Création d'un contact correspondant au mail établissement
						ContactMelComplet c = new ContactMelComplet();
						DemandeDeContactSimple dmc = new DemandeDeContactSimple();
						dmc.setLibelleAffichage(getTranslation("coordonnees.mail.etablissement"));
						c.setDemandeDeContact(dmc);
						c.setMail(mail.get());
						// Ajout du mail dans la vue
						ajouterInfoContactPerso(c);
					}
				}
				updateStyle();
			}
			// Si on a une carte de contacts d'urgence
			if (contactsUrgence != null) {
				// On met à jour le titre
				contactsUrgence.getTitre().setText(getTranslation("contacts.urgence"));
				// On l'ajoute en haut de la vue
				coordPersoLayout.addComponentAsFirst(contactsUrgence);
			}
			// Si on a une carte de contacts personnels
			if (contacts != null) {
				// On met à jour le titre
				contacts.getTitre().setText(getTranslation("contacts.perso"));
				// On l'ajoute en haut de la vue
				coordPersoLayout.addComponentAsFirst(contacts);
			}
		}
	}

	private void ajouterInfoContactPerso(ContactComplet c) {
		// Si on la carte de contact personnels est null
		if(contacts == null) {
			// On initialise la carte
			contacts = new Card(VaadinIcon.USER.create(),"", true);
		}

		FormLayout contactLayout = new FormLayout();
		contactLayout.getStyle().set(CssUtils.MARGIN, "0");
		contacts.addAlt(contactLayout);

		TextLabel contact=new TextLabel();
		contactLayout.add(contact);
		contact.setLabel(c.getDemandeDeContact().getLibelleAffichage());
		CmpUtils.valueAndVisibleIfNotNull(contact,getValue(c));
		CmpUtils.setModerateTextLabel(contact);

		contacts.displayAlt();
	}

	private void ajouterInfoContactUrgence(ContactComplet c, String libelle) {
		// Si on la carte de contact d'urgence est null
		if(contactsUrgence == null) {
			// On initialise la carte
			contactsUrgence = new Card(VaadinIcon.BELL.create(),"", true);
		}

		FormLayout contactLayout = new FormLayout();
		contactLayout.getStyle().set(CssUtils.MARGIN, "0");
		contactsUrgence.addAlt(contactLayout);

		TextLabel contact=new TextLabel();
		contactLayout.add(contact);
		contact.setLabel(libelle+ " ("+c.getProprietaire()+")");
		CmpUtils.valueAndVisibleIfNotNull(contact,getValue(c));
		CmpUtils.setModerateTextLabel(contact);

		contactsUrgence.displayAlt();
	}

	private String getValue(ContactComplet c) {
		// En fonction du type de contact
		switch(c.getCanalCommunication().getValue()) {
		case Utils.CANAL_CONTACT_MAIL :
			ContactMelComplet cmc= (ContactMelComplet) c;
			return cmc.getMail();
		case Utils.CANAL_CONTACT_TEL :
			ContactTelephoneComplet ctc= (ContactTelephoneComplet) c;
			return ctc.getTelephone();
		default:
			break;
		}
		return null;
	}

	private void ajouterAdresse(ContactComplet c,int n) {
		ContactAdresseComplet cac= (ContactAdresseComplet) c;

		Card adresseCard = new Card(VaadinIcon.LOCATION_ARROW_CIRCLE.create(),"", true);
		adresseCard.getTitre().setText(cac.getDemandeDeContact().getLibelleAffichage());

		FormLayout adresseLayout = new FormLayout();
		adresseLayout.getStyle().set(CssUtils.MARGIN, "0");
		adresseCard.addAlt(adresseLayout);

		TextLabel nomAdresse=new TextLabel();
		nomAdresse.setId(NOM_ADRESSE + n);
		adresseLayout.add(nomAdresse);
		CmpUtils.valueAndVisibleIfNotNull(nomAdresse,cac.getProprietaire());
		CmpUtils.setModerateTextLabel(nomAdresse);


		TextLabel paysAdresse=new TextLabel();
		paysAdresse.setId(PAYS_ADRESSE + n);
		adresseLayout.add(paysAdresse);
		CmpUtils.valueAndVisibleIfNotNull(paysAdresse,cac.getLibellePays());
		CmpUtils.setModerateTextLabel(paysAdresse);


		TextLabel compl1Adresse=new TextLabel();
		compl1Adresse.setId(COMP1_ADRESSE + n);
		adresseLayout.add(compl1Adresse);
		CmpUtils.valueAndVisibleIfNotNull(compl1Adresse,cac.getLigne3OuVoie());
		CmpUtils.setModerateTextLabel(compl1Adresse);


		TextLabel compl2Adresse=new TextLabel();
		compl2Adresse.setId(COMP2_ADRESSE + n);
		adresseLayout.add(compl2Adresse);
		CmpUtils.valueAndVisibleIfNotNull(compl2Adresse,cac.getLigne4OuComplement());
		CmpUtils.setModerateTextLabel(compl2Adresse);



		TextLabel numVoieAdresse=new TextLabel();
		numVoieAdresse.setId(NUM_VOIE_ADRESSE + n);
		adresseLayout.add(numVoieAdresse);
		CmpUtils.valueAndVisibleIfNotNull(numVoieAdresse,cac.getLigne1OuEtage());
		CmpUtils.setModerateTextLabel(numVoieAdresse);



		TextLabel lieuServAdresse=new TextLabel();
		lieuServAdresse.setId(LIEU_SERV_ADRESSE + n);
		adresseLayout.add(lieuServAdresse);
		CmpUtils.valueAndVisibleIfNotNull(lieuServAdresse,cac.getLigne2OuBatiment());
		CmpUtils.setModerateTextLabel(lieuServAdresse);


		TextLabel codePostalAdresse=new TextLabel();
		codePostalAdresse.setId(CODE_POSTAL_ADRESSE + n);
		adresseLayout.add(codePostalAdresse);
		CmpUtils.setModerateTextLabel(codePostalAdresse);

		TextLabel communeAdresse=new TextLabel();
		communeAdresse.setId(COMMUNE_ADRESSE + n);
		adresseLayout.add(communeAdresse);
		CmpUtils.setModerateTextLabel(communeAdresse);


		if(cac.getPays().equals(Utils.CODE_PAYS_FRANCE)) {
			CmpUtils.valueAndVisibleIfNotNull(codePostalAdresse,cac.getCodePostal());
			CmpUtils.valueAndVisibleIfNotNull(communeAdresse,cac.getLibelleCommune());
		} else {
			CmpUtils.valueAndVisibleIfNotNull(codePostalAdresse,cac.getCodePostal());
			CmpUtils.valueAndVisibleIfNotNull(communeAdresse,cac.getLigne5Etranger());
		}

		coordPersoLayout.add(adresseCard);

		adresseCard.displayAlt();
	}


	protected void updateStyle() {
		// Si on a une carte de contact
		if(contacts!=null) {
			// On met à jour son style css
			contacts.updateStyle();
		}
		// Si on a une carte de contact d'urgence
		if(contactsUrgence!=null) {
			// On met à jour son style css
			contactsUrgence.updateStyle();
		}

		List<Component> listComp = coordPersoLayout.getChildren().collect(Collectors.toList());
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

}
