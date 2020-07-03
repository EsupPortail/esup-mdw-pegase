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

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import fr.univlorraine.mondossierweb.service.SecurityService;
import fr.univlorraine.mondossierweb.ui.component.AdaptSizeLayout;
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.layout.HasCodeEtuUrlParameterView;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import fr.univlorraine.pegase.model.insgestion.CibleInscription;
import fr.univlorraine.pegase.model.insgestion.InscriptionComplete;
import lombok.Getter;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class InscriptionsView extends AdaptSizeLayout implements HasDynamicTitle, HasHeader, LocaleChangeObserver, HasUrlParameter<String> {

	@Autowired
	private transient SecurityService securityService;
	@Autowired
	private transient PageTitleFormatter pageTitleFormatter;
	@Getter
	private String pageTitle = "";
	@Getter
	private final TextHeader header = new TextHeader();

	private final VerticalLayout inscriptionsLayout = new VerticalLayout();

	List<TextField> textFieldPeriode = new LinkedList<TextField> ();
	List<TextField> textFieldRegime = new LinkedList<TextField> ();
	List<TextField> textFieldStatut = new LinkedList<TextField> ();
	List<Button> buttonCertificat = new LinkedList<Button> ();
	@PostConstruct
	public void init() {
		setSizeFull();
		add(inscriptionsLayout);
	}


	/**
	 * @see com.vaadin.flow.i18n.LocaleChangeObserver#localeChange(com.vaadin.flow.i18n.LocaleChangeEvent)
	 */
	@Override
	public void localeChange(final LocaleChangeEvent event) {
		setViewTitle(getTranslation("inscriptions.title"));

		for(TextField tf : textFieldPeriode) {
			tf.setLabel(getTranslation("inscription.periode"));
		}
		for(TextField tf : textFieldRegime) {
			tf.setLabel(getTranslation("inscription.regime"));
		}
		for(TextField tf : textFieldStatut) {
			tf.setLabel(getTranslation("inscription.statut"));
		}
		for(Button b : buttonCertificat) {
			b.setText(getTranslation("inscription.certificat"));
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
		securityService.checkDossier();
		// Mise à jour de l'affichage
		updateData(securityService.getDossier()!=null ? securityService.getDossier().getInscriptions() : null);
	}

	/**
	 * Reset toutes les données affichées
	 * @param apprenant
	 */
	private void resetData() {
		inscriptionsLayout.removeAll();
		textFieldPeriode.clear();
		textFieldRegime.clear();
		textFieldStatut.clear();
		buttonCertificat.clear();
	}
	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	private void updateData(List<InscriptionComplete> inscriptions) {
		resetData();
		if(inscriptions != null && !inscriptions.isEmpty()) {
			for(InscriptionComplete inscription : inscriptions) {
				CibleInscription cible = inscription.getCible();
				Card insCard = new Card(cible.getLibelleLong(), true);

				TextField periode = new TextField();
				if(cible.getPeriode()!=null) {
					periode.setValue(cible.getPeriode().getLibelleAffichage());
				}
				periode.setReadOnly(true);
				CmpUtils.setLongTextField(periode);
				textFieldPeriode.add(periode);

				TextField regime = new TextField();
				if(inscription.getRegimeInscription()!=null) {
					regime.setValue(inscription.getRegimeInscription().getLibelle());
				}
				regime.setReadOnly(true);
				CmpUtils.setLongTextField(regime);
				textFieldRegime.add(regime);

				TextField statut = new TextField();
				if(inscription.getStatutInscription()!=null) {
					statut.setValue(inscription.getStatutInscription().getValue());
				}
				statut.setReadOnly(true);
				CmpUtils.setLongTextField(statut);
				textFieldStatut.add(statut);

				/* AJout de la liste des bourses et aides ?
				for( OccurrenceNomenclature occ : inscription.getBoursesEtAides()) {
					occ.getLibelle()
				} */

				// Ajout bouton certificat de scolarité
				Button certButton = new Button();
				buttonCertificat.add(certButton);

				insCard.addAlt(periode);
				insCard.addAlt(regime);
				insCard.addAlt(statut);
				insCard.addAlt(certButton);

				// Si on doit afficher plus de 2 inscriptions, on replie la carte
				if(inscriptions.size()>2) {
					insCard.hideAlt();
				}else {
					insCard.displayAlt();
				}
				insCard.updateStyle(false, true);
				inscriptionsLayout.add(insCard);
			}
		}
	}
	
	@Override
	protected void adaptSize(final Boolean isMobile) {
		inscriptionsLayout.getChildren().forEach(c -> {
			Card insCard = (Card) c; 
			insCard.updateStyle(isMobile, true);
		});
		
	}

}
