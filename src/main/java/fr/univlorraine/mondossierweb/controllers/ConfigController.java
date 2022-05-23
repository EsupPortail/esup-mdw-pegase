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
package fr.univlorraine.mondossierweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.univlorraine.mondossierweb.service.PreferencesService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConfigController {
	
	private static final String ACCES_ETUDIANT_ACTIF = "ACCES_ETUDIANT_ACTIF";

	private static final String ACCES_GESTIONNAIRE_ACTIF = "ACCES_GESTIONNAIRE_ACTIF";

	private static final String NOTE_COEFF = "NOTE_COEFF";

	private static final String NOTE_CONTROLE = "NOTE_CONTROLE";

	private static final String NOTE_ECTS = "NOTE_ECTS";

	private static final String ACCESSTOKEN_URL = "ACCESSTOKEN_URL";

	private static final String ACCESSTOKEN_PASSWORD = "ACCESSTOKEN_PASSWORD";

	private static final String ACCESSTOKEN_USERNAME = "ACCESSTOKEN_USERNAME";

	private static final String DOC_URL = "DOC_URL";

	private static final String HELP_URL = "HELP_URL";

	private static final String LDAP_CODETU_ATTRIBUTE = "LDAP_CODETU_ATTRIBUTE";

	private static final String LDAP_FILTRE_ETUDIANT = "LDAP_FILTRE_ETUDIANT";

	private static final String LDAP_FILTRE_GESTIONNAIRE = "LDAP_FILTRE_GESTIONNAIRE";

	private static final String LDAP_MAIL_ATTRIBUTE = "LDAP_MAIL_ATTRIBUTE";

	private static final String UNIV_LOGO = "UNIV_LOGO";
	
	@Autowired
	private transient PreferencesService prefService;
	
	public boolean isAccesEtudiantActif() {
		return getBooleanValueForParameter(ACCES_ETUDIANT_ACTIF);
	}
	public boolean isAccesGestionnaireActif() {
		return getBooleanValueForParameter(ACCES_GESTIONNAIRE_ACTIF);
	}
	public boolean isAffichageNoteCoeffActif() {
		return getBooleanValueForParameter(NOTE_COEFF);
	}
	public boolean isAffichageNoteControleActif() {
		return getBooleanValueForParameter(NOTE_CONTROLE);
	}
	public boolean isAffichageCreditECTSActif() {
		return getBooleanValueForParameter(NOTE_ECTS);
	}
	public String getAccesTokenUrl() {
		return getStringValueForParameter(ACCESSTOKEN_URL);
	}
	public String getAccesTokenUsername() {
		return getStringValueForParameter(ACCESSTOKEN_USERNAME);
	}
	public String getDocUrl() {
		return getStringValueForParameter(DOC_URL);
	}
	public String getHelpUrl() {
		return getStringValueForParameter(HELP_URL);
	}
	public String getLdapCodEtuAttribute() {
		return getStringValueForParameter(LDAP_CODETU_ATTRIBUTE);
	}
	public String getLdapFiltreEtudiant() {
		return getStringValueForParameter(LDAP_FILTRE_ETUDIANT);
	}
	public String getLdapFiltreGestionnaire() {
		return getStringValueForParameter(LDAP_FILTRE_GESTIONNAIRE);
	}
	public String getLdapMailAttribute() {
		return getStringValueForParameter(LDAP_MAIL_ATTRIBUTE);
	}
	public String getUnivLogo() {
		return getStringValueForParameter(UNIV_LOGO);
	}
	public String getAccesTokenPassword() {
		return getSecretValueForParameter(ACCESSTOKEN_PASSWORD);
	}
	
	
	/**
	 * 
	 * @param parameter
	 * @return la valeur d'un parametre de type Secret
	 */
	private String getSecretValueForParameter(String parametre){
		return prefService.getPreferences(parametre).getSecret();
	}
	
	/**
	 * 
	 * @param parameter
	 * @return la valeur d'un parametre de type String
	 */
	private String getStringValueForParameter(String parametre){
		return prefService.getPreferences(parametre).getValeur();
	}
	
	/**
	 * 
	 * @param parameter
	 * @return la valeur d'un parametre de type booleen
	 */
	private boolean getBooleanValueForParameter(String parametre){
		return prefService.getBooleanValue(prefService.getPreferences(parametre));
	}

}
