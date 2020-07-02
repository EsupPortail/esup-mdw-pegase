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
import fr.univlorraine.mondossierweb.ui.component.Card;
import fr.univlorraine.mondossierweb.ui.layout.HasCodeEtuUrlParameterView;
import fr.univlorraine.mondossierweb.ui.layout.HasHeader;
import fr.univlorraine.mondossierweb.ui.layout.MainLayout;
import fr.univlorraine.mondossierweb.ui.layout.PageTitleFormatter;
import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import fr.univlorraine.pegase.model.insgestion.InscriptionComplete;
import fr.univlorraine.pegase.model.insgestion.OccurrenceNomenclature;
import lombok.Getter;

@Secured({SecurityUtils.ROLE_SUPERADMIN,SecurityUtils.ROLE_ETUDIANT, SecurityUtils.ROLE_ENSEIGNANT})
@Route(layout = MainLayout.class)
@SuppressWarnings("serial")
public class InscriptionsView extends HasCodeEtuUrlParameterView implements HasDynamicTitle, HasHeader, LocaleChangeObserver, HasUrlParameter<String> {

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
	@PostConstruct
	public void init() {
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
	}
	/**
	 * Mise à jour des données affichées
	 * @param apprenant
	 */
	private void updateData(List<InscriptionComplete> inscriptions) {
		resetData();
		if(inscriptions != null && !inscriptions.isEmpty()) {
			for(InscriptionComplete inscription : inscriptions) {
				Card insCard = new Card(inscription.getCible().getLibelleLong(), true);
				
				TextField periode = new TextField();
				periode.setValue(inscription.getCible().getPeriode().getLibelleAffichage());
				textFieldPeriode.add(periode);
				
				TextField regime = new TextField();
				regime.setValue(inscription.getRegimeInscription().getLibelle());
				textFieldRegime.add(regime);
				
				TextField statut = new TextField();
				statut.setValue(inscription.getStatutInscription().getValue());
				textFieldStatut.add(statut);
				
				/* AJout de la liste des bourses et aides ?
				for( OccurrenceNomenclature occ : inscription.getBoursesEtAides()) {
					occ.getLibelle()
				} */
				
				// TODO ajout bouton certificat de scolarité
				
				insCard.add(periode);
				insCard.add(regime);
				insCard.add(statut);
				
				insCard.hideAlt();
				inscriptionsLayout.add(insCard);
			}
		}
	}

}
