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
package fr.univlorraine.mondossierweb.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.vaadin.flow.server.VaadinSession;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import fr.univlorraine.mondossierweb.model.ldap.entity.LdapPerson;
import fr.univlorraine.mondossierweb.model.user.entity.Utilisateur;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppUserDetailsService implements UserDetailsService {


	@Autowired
	protected LdapService ldapService;

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
			if(admins.contains(";")) {
				String[] tmails = admins.split(";");
				superAdmins.addAll(Arrays.asList(tmails));
			} else {
				superAdmins.add(configController.getLogMailTo());
			}
		}
		log.info("Admins : {}",superAdmins);
	}
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		Assert.notNull(username, "Le nom d'utilisateur ne doit pas être nul.");

		Utilisateur utilisateur = newUtilisateur(username);

		// On peuple les droits
		// 1 - On cherche à savoir si l'utilisateur est ADMIN
		if (superAdmins.contains(username)) {
			utilisateur.getAuthorities().add(new SimpleGrantedAuthority(SecurityUtils.ROLE_SUPERADMIN));
			ldapService.findByUid(username).ifPresent(people -> utilisateur.setDisplayName(people.getDisplayName()));
		} else {
			// 2 - On cherche à savoir si l'utilisateur est étudiant
			LdapPerson student = ldapService.findStudentByUid(username);
			// 2.1- Si l'accès étudiant est activé et que l'utilisateur est étudiant
			if(configController.isAccesEtudiantActif() && student != null) {
				utilisateur.getAuthorities().add(new SimpleGrantedAuthority(SecurityUtils.ROLE_ETUDIANT));
				utilisateur.setDisplayName(student.getDisplayName());
				utilisateur.setCodeEtudiant(student.getCodeApprenant());
				utilisateur.setMail(student.getMail());
				if( VaadinSession.getCurrent() != null) {
					VaadinSession.getCurrent().setAttribute(Utils.DOSSIER_CONSULTE_APPRENANT, student.getCodeApprenant());
				}
				// Nécessaire de le faire à cet endroit?
			} else {
				// 3- Si l'accès gestionnaire est activé
				if(configController.isAccesGestionnaireActif()) {
					// 3.1 - On cherche à savoir si l'utilisateur est gestionnaire
					LdapPerson teacher = ldapService.findAdministratorByUid(username);
					if(teacher != null) {
						utilisateur.getAuthorities().add(new SimpleGrantedAuthority(SecurityUtils.ROLE_GESTIONNAIRE));
						utilisateur.setDisplayName(teacher.getDisplayName());
					}
				}
			}
		}

		// On renseigne l'attribut lastRole de l'utilisateur
		utilisateur.setLastRole(utilisateur.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));

		// Tracer l'accès dans un fichier de log
		log.trace("Connexion de {} (login:{} - codeApprenant:{}) en tant que {}",utilisateur.getDisplayName(), utilisateur.getUsername(), utilisateur.getCodeEtudiant(), utilisateur.getLastRole());

		return utilisateur;
	}



	private Utilisateur newUtilisateur(final String username) {
		Utilisateur superAdmin = new Utilisateur();
		superAdmin.setUsername(username);
		superAdmin.setLastLogin(LocalDateTime.now());
		return superAdmin;
	}



}
