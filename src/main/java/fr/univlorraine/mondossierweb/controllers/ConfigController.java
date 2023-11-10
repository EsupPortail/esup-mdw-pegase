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

import fr.univlorraine.mondossierweb.services.PreferencesService;

@Component
public class ConfigController {
	
	private static final String ACCES_ETUDIANT_ACTIF = "ACCES_ETUDIANT_ACTIF";

	private static final String ACCES_GESTIONNAIRE_ACTIF = "ACCES_GESTIONNAIRE_ACTIF";

	private static final String NOTE_COEFF = "NOTE_COEFF";

	private static final String NOTE_CONTROLE = "NOTE_CONTROLE";

	private static final String NOTE_ECTS = "NOTE_ECTS";

	private static final String ACCESSTOKEN_URL = "ACCESSTOKEN_URL";

	private static final String ACCESSTOKEN_PASSWORD = "ACCESSTOKEN_PASSWORD";

	private static final String ACCESSTOKEN_USERNAME = "ACCESSTOKEN_USERNAME";
	
	private static final String ACCESSTOKEN_DURATION = "ACCESSTOKEN_DURATION";

	private static final String DOC_URL = "DOC_URL";

	private static final String HELP_URL = "HELP_URL";

	private static final String LDAP_CODETU_ATTRIBUTE = "LDAP_CODETU_ATTRIBUTE";

	private static final String LDAP_FILTRE_ETUDIANT = "LDAP_FILTRE_ETUDIANT";

	private static final String LDAP_FILTRE_GESTIONNAIRE = "LDAP_FILTRE_GESTIONNAIRE";

	private static final String LDAP_MAIL_ATTRIBUTE = "LDAP_MAIL_ATTRIBUTE";
	
	private static final String UNIV_LOGO_IMG = "UNIV_LOGO_IMG";
	
	private static final String UNIV_FAVICON_32 = "UNIV_FAVICON_32";
	
	private static final String UNIV_FAVICON_16 = "UNIV_FAVICON_16";
	
	private static final String INSCRIPTION_DETAIL = "INSCRIPTION_DETAIL";

	private static final String NOTE_BAREME = "NOTE_BAREME";
	
	private static final String CURSUS_FAC_ITALIQUE = "CURSUS_FAC_ITALIQUE";

	private static final String INSCRIPTION_STATUTS = "INSCRIPTION_STATUTS";

	private static final String LDAP_DISPLAYNAME_ATTRIBUTE = "LDAP_DISPLAYNAME_ATTRIBUTE";
	
	private static final String INFO_CONNEXION = "INFO_CONNEXION";
	
	private static final String INFO_CONNEXION_PREF = "INFO_CONNEXION_PREF";
	
	private static final String ETUDIANT_MAIL_LDAP = "ETUDIANT_MAIL_LDAP";
	
	private static final String ETUDIANT_RESUME = "ETUDIANT_RESUME";
	
	private static final String BTN_CERT = "BTN_CERT";
	
	private static final String BTN_ATTEST_PAI = "BTN_ATTEST_PAI";
	
	private static final String BTN_CURSUS = "BTN_CURSUS";
	
	private static final String BTN_NOTES = "BTN_NOTES";
	
	private static final String SHOW_SQL = "SHOW_SQL";

	private static final String PEGASE_ETAB = "PEGASE_ETAB";

	private static final String PEGASE_API_INS_URL = "PEGASE_API_INS_URL";
	
	private static final String PEGASE_API_INS_EXT_URL = "PEGASE_API_INS_EXT_URL";
	
	private static final String PEGASE_API_CHC_URL = "PEGASE_API_CHC_URL";

	private static final String PEGASE_API_COC_URL = "PEGASE_API_COC_URL";

	private static final String PEGASE_API_PAI_URL = "PEGASE_API_PAI_URL";

	private static final String PEGASE_ID_PJ_PHOTO = "PEGASE_ID_PJ_PHOTO";

	private static final String PEGASE_DEMO_APPRENANT = "PEGASE_DEMO_APPRENANT";

	private static final String PEGASE_TEST_PERIODE = "PEGASE_TEST_PERIODE";
	
	private static final String PEGASE_TEST_APPRENANT = "PEGASE_TEST_APPRENANT";
	
	private static final String PEGASE_TEST_CHEMIN = "PEGASE_TEST_CHEMIN";
	
	private static final String SMTP_HOST = "SMTP_HOST";
	
	private static final String SMTP_PORT = "SMTP_PORT";
	
	private static final String SMTP_USERNAME = "SMTP_USERNAME";
	
	private static final String SMTP_PASSWORD = "SMTP_PASSWORD";
	
	private static final String SMTP_FROM = "SMTP_FROM";
	
	private static final String LOG_MAIL_TO = "LOG_MAIL_TO";
	
	private static final String ADMINS = "ADMINS";

	private static final String CSS_MAIN_COLOR = "CSS_MAIN_COLOR";
	
	private static final String CSS_SECOND_COLOR = "CSS_SECOND_COLOR";
	
	private static final String CSS_HEADER_CARD_SEP_COLOR = "CSS_HEADER_CARD_SEP_COLOR";
	
	private static final String CSS_TEXT_COLOR = "CSS_TEXT_COLOR";
	
	private static final String CSS_BTN_COLOR = "CSS_BTN_COLOR";
	
	private static final String CSS_BACKGROUND_COLOR = "CSS_BACKGROUND_COLOR";
	


	@Autowired
	private PreferencesService prefService;
	
	public boolean isInfoConnexionActif() {
		return getBooleanValueForParameter(INFO_CONNEXION);
	}
	public boolean isInfoConnexionPrefActif() {
		return getBooleanValueForParameter(INFO_CONNEXION_PREF);
	}
	public boolean isEtudiantMailLdapActif() {
		return getBooleanValueForParameter(ETUDIANT_MAIL_LDAP);
	}
	public boolean isEtudiantResumeActif() {
		return getBooleanValueForParameter(ETUDIANT_RESUME);
	}
	public boolean isCertificatActif() {
		return getBooleanValueForParameter(BTN_CERT);
	}
	public boolean isAttestationPaiementActif() {
		return getBooleanValueForParameter(BTN_ATTEST_PAI);
	}
	public boolean isCursusActif() {
		return getBooleanValueForParameter(BTN_CURSUS);
	}
	public boolean isNotesActif() {
		return getBooleanValueForParameter(BTN_NOTES);
	}
	public boolean isShowSqlActif() {
		return getBooleanValueForParameter(SHOW_SQL);
	}
	public boolean isAccesEtudiantActif() {
		return getBooleanValueForParameter(ACCES_ETUDIANT_ACTIF);
	}
	public boolean isAffichageCursusFacItalique() {
		return getBooleanValueForParameter(CURSUS_FAC_ITALIQUE);
	}
	public boolean isAffichageNoteBaremeActif() {
		return getBooleanValueForParameter(NOTE_BAREME);
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
	public String getLdapDisplayNameAttribute() {
		return getStringValueForParameter(LDAP_DISPLAYNAME_ATTRIBUTE);
	}
	public byte[] getUnivLogoImg() {
		return getByteValueForParameter(UNIV_LOGO_IMG);
	}
	public byte[] getUnivFavicon32() {
		return getByteValueForParameter(UNIV_FAVICON_32);
	}
	public String getUnivFavicon32Name() {
		return getStringValueForParameter(UNIV_FAVICON_32);
	}
	public byte[] getUnivFavicon16() {
		return getByteValueForParameter(UNIV_FAVICON_16);
	}
	public String getUnivFavicon16Name() {
		return getStringValueForParameter(UNIV_FAVICON_16);
	}
	public String getAccesTokenPassword() {
		return getSecretValueForParameter(ACCESSTOKEN_PASSWORD);
	}
	public String getAccesTokenDuration() {
		return getStringValueForParameter(ACCESSTOKEN_DURATION);
	}
	public String getInscriptionDetail() {
		return getStringValueForParameter(INSCRIPTION_DETAIL);
	}
	public String getInscriptionStatuts() {
		return getStringValueForParameter(INSCRIPTION_STATUTS);
	}
	public String getEtablissement() {
		return getStringValueForParameter(PEGASE_ETAB);
	}
	public String getApiInsUrl() {
		return getStringValueForParameter(PEGASE_API_INS_URL);
	}
	public String getApiInsExtUrl() {
		return getStringValueForParameter(PEGASE_API_INS_EXT_URL);
	}
	public String getApiChcUrl() {
		return getStringValueForParameter(PEGASE_API_CHC_URL);
	}
	public String getApiCocUrl() {
		return getStringValueForParameter(PEGASE_API_COC_URL);
	}
	public String getApiPaiUrl() {
		return getStringValueForParameter(PEGASE_API_PAI_URL);
	}
	public String getIdPjPhoto() {
		return getStringValueForParameter(PEGASE_ID_PJ_PHOTO);
	}
	public String getPegaseDemoApprenant() {
		return getStringValueForParameter(PEGASE_DEMO_APPRENANT);
	}
	public String getPegaseTestPeriode() {
		return getStringValueForParameter(PEGASE_TEST_PERIODE);
	}
	public String getPegaseTestApprenant() {
		return getStringValueForParameter(PEGASE_TEST_APPRENANT);
	}
	public String getPegaseTestChemin() {
		return getStringValueForParameter(PEGASE_TEST_CHEMIN);
	}
	public String getSmtpHost() {
		return getStringValueForParameter(SMTP_HOST);
	}
	public String getSmtpPort() {
		return getStringValueForParameter(SMTP_PORT);
	}
	public String getSmtpUsername() {
		return getStringValueForParameter(SMTP_USERNAME);
	}
	public String getSmtpPassword() {
		return getSecretValueForParameter(SMTP_PASSWORD);
	}
	public String getSmtpFrom() {
		return getStringValueForParameter(SMTP_FROM);
	}
	public String getLogMailTo() {
		return getStringValueForParameter(LOG_MAIL_TO);
	}
	public String getAdmins() {
		return getStringValueForParameter(ADMINS);
	}
	public String getCssMainColor() {
		return getStringValueForParameter(CSS_MAIN_COLOR);
	}
	public String getCssSecondColor() {
		return getStringValueForParameter(CSS_SECOND_COLOR);
	}
	public String getCssHeaderCardSepColor() {
		return getStringValueForParameter(CSS_HEADER_CARD_SEP_COLOR);
	}
	public String getCssTextColor() {
		return getStringValueForParameter(CSS_TEXT_COLOR);
	}
	public String getCssBtnColor() {
		return getStringValueForParameter(CSS_BTN_COLOR);
	}
	public String getCssBackgroundColor() {
		return getStringValueForParameter(CSS_BACKGROUND_COLOR);
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
	 * @return la valeur d'un parametre de type Blob
	 */
	private byte[] getByteValueForParameter(String parametre){
		return prefService.getPreferences(parametre).getData();
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
