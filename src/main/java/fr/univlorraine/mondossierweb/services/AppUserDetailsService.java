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
package fr.univlorraine.mondossierweb.services;

import com.vaadin.flow.server.VaadinSession;
import fr.univlorraine.mondossierweb.controllers.ConfigController;
import fr.univlorraine.mondossierweb.model.user.entity.Utilisateur;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AppUserDetailsService implements AuthenticationUserDetailsService {


	@Autowired
	protected CasService casService;

	@Autowired
	private ConfigController configController;

	@Value("${app.superadmins:}")
	private List<String> initSuperAdmins;

	@Value("${app.superadmins:}")
	private List<String> superAdmins;


	@PostConstruct
	public void init() {
		refreshParameters();
	}

	public void refreshParameters() {
		superAdmins.clear();
		superAdmins.addAll(initSuperAdmins);
		String admins = configController.getAdmins();
		if (StringUtils.hasText(admins)) {
			if (admins.contains(";")) {
				String[] tmails = admins.split(";");
				superAdmins.addAll(Arrays.asList(tmails));
			} else {
				superAdmins.add(configController.getLogMailTo());
			}
		}
		log.info("Admins : {}", superAdmins);
	}

	@Transactional
	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		CasAssertionAuthenticationToken casAssertionAuthenticationToken = (CasAssertionAuthenticationToken) token;
		AttributePrincipal principal = casAssertionAuthenticationToken.getAssertion().getPrincipal();
		Map attributes = principal.getAttributes();

		log.info("Map attributes : {}", attributes);

		String username = (String) attributes.get(casService.getCasLoginAttribute());
		String mail = (String) attributes.get(casService.getCasMailAttribute());
		String displayName = (String) attributes.get(casService.getCasDisplayNameAttribute());
		String supannEtuId = (String) attributes.get(casService.getCasCodEtuAttribute());

		log.info("username : {}, mail : {}, displayName : {}, supannEtuId : {}", username, mail, displayName, supannEtuId);

		Assert.notNull(username, "Le nom d'utilisateur ne doit pas être nul.");
		Utilisateur utilisateur = newUtilisateur(username);
		utilisateur.setDisplayName(displayName);

		// On peuple les droits
		// 1 - On cherche à savoir si l'utilisateur est ADMIN
		if (superAdmins.contains(username)) {
			utilisateur.getAuthorities().add(new SimpleGrantedAuthority(SecurityUtils.ROLE_SUPERADMIN));
		} else {
			// Teste la condition pour identifier l'étudiant
			boolean estEtudiant = isFiltreValid(attributes, configController.getCasFiltreEtudiant());
			// 2.1- Si l'accès étudiant est activé et que l'utilisateur est étudiant
			if (configController.isAccesEtudiantActif() && StringUtils.hasText(supannEtuId) && estEtudiant) {
				utilisateur.getAuthorities().add(new SimpleGrantedAuthority(SecurityUtils.ROLE_ETUDIANT));
				utilisateur.setCodeEtudiant(supannEtuId);
				if(StringUtils.hasText(mail)) {
					utilisateur.setMail(mail);
				}
				if (VaadinSession.getCurrent() != null) {
					VaadinSession.getCurrent().setAttribute(Utils.DOSSIER_CONSULTE_APPRENANT, supannEtuId);
				}
			} else {
				// 3- Si l'accès gestionnaire est activé
				if (configController.isAccesGestionnaireActif()) {
					// Teste la condition pour identifier l'étudiant
					boolean estGestionnaire = isFiltreValid(attributes, configController.getCasFiltreGestionnaire());
					if (estGestionnaire) {
						utilisateur.getAuthorities().add(new SimpleGrantedAuthority(SecurityUtils.ROLE_GESTIONNAIRE));
					}
				}
			}
		}

		// On renseigne l'attribut lastRole de l'utilisateur
		utilisateur.setLastRole(utilisateur.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));

		// Tracer l'accès dans un fichier de log
		log.trace("Connexion de {} (login:{} - codeApprenant:{}) en tant que {}", utilisateur.getDisplayName(), utilisateur.getUsername(), utilisateur.getCodeEtudiant(), utilisateur.getLastRole());

		return utilisateur;

	}

	private boolean isFiltreValid(Map casAttributes, Map<String, List<String>> casFilter) {
		// Si on n'a bien une et une seule clé (attribut) à tester
		if(casFilter.keySet() != null && casFilter.keySet().size() == 1) {
			// récupération du nom de l'attribut à tester
			String attributeToTest = (String) casFilter.keySet().toArray()[0];
			// récupération de la valeur de l'attribut pour l'utilisateur
			String casAttributeValue = (String) casAttributes.get(attributeToTest);

			log.info("{} casAttributeValue : {}", attributeToTest, casAttributeValue);
			// Si l'attribut ciblé est valué pour l'utilisateur
			if(StringUtils.hasText(casAttributeValue)) {
				// On découpe casAttributeValue en liste au cas où ce soit un attribut multivalué
				List<String> casAttributeValues = Arrays.asList(casAttributeValue.split(";"));

				// Si l'utilisateur a des valeurs pour l'attribut ciblé
				if (casAttributeValues != null && !casAttributeValues.isEmpty()) {
					// Pour chaque valeur à tester
					for (String valueToTest : casFilter.get(attributeToTest)) {
						log.info("valueToTest : {}", valueToTest);
						// Si la valeur de l'attribut vaut "*" ou si la valeur est contenu dans la liste des valeurs de l'attribut pour l'utilisateur
						if (valueToTest.equals("*") || casAttributeValues.contains(valueToTest)) {
							// On considère que la condition est remplie
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private Utilisateur newUtilisateur(final String username) {
		Utilisateur superAdmin = new Utilisateur();
		superAdmin.setUsername(username);
		superAdmin.setLastLogin(LocalDateTime.now());
		return superAdmin;
	}


}
