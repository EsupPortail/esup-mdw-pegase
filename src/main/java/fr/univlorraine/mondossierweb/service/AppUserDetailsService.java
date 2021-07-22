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
package fr.univlorraine.mondossierweb.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.vaadin.flow.server.VaadinSession;

import fr.univlorraine.mondossierweb.model.app.entity.Utilisateur;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesUtilisateurRepository;
import fr.univlorraine.mondossierweb.model.app.repository.UtilisateurRepository;
import fr.univlorraine.mondossierweb.model.ldap.entity.LdapPerson;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private transient UtilisateurRepository utilisateurRepository;

	@Autowired
	private transient PreferencesUtilisateurRepository preferencesUtilisateurRepository;

	@Autowired
	protected transient LdapService ldapService;
	
	@Autowired
	private transient PegaseService pegaseService;

	@Value("${app.superadmins:}")
	private transient List<String> superAdmins;

	@Value("${acces.enseignant.actif}")
	private transient boolean accesEnseignantActif;

	@Value("${acces.etudiant.actif}")
	private transient boolean accesEtudiantActif;

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
			if(accesEtudiantActif && student != null) {
				utilisateur.getAuthorities().add(new SimpleGrantedAuthority(SecurityUtils.ROLE_ETUDIANT));
				utilisateur.setDisplayName(student.getDisplayName());
				utilisateur.setCodeEtudiant(student.getCodeApprenant());
				utilisateur.setMail(student.getMail());
				//utilisateur.setCodEtuDossier(student.getCodeApprenant());
				if( VaadinSession.getCurrent() != null) {
					VaadinSession.getCurrent().setAttribute("codeApprenant", student.getCodeApprenant());
				}
				// Nécessaire de le faire à cet endroit?
				// utilisateur.setDossier(pegaseService.recupererDossierApprenant(utilisateur.getCodEtuDossier()));
			} else {
				// 3- Si l'accès enseignant est activé
				if(accesEnseignantActif) {
					// 3.1 - On cherche à savoir si l'utilisateur est enseignant
					LdapPerson teacher = ldapService.findTeacherByUid(username);
					if(teacher != null) {
						utilisateur.getAuthorities().add(new SimpleGrantedAuthority(SecurityUtils.ROLE_ENSEIGNANT));
						utilisateur.setDisplayName(teacher.getDisplayName());
					}
				}
			}
		}

		// On renseigne l'attribut lastRole de l'utilisateur
		utilisateur.setLastRole(utilisateur.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));

		// Si l'utilisateur existe, on met à jour la date de dernière connexion et le role
		Optional<Utilisateur> u = utilisateurRepository.findById(username);
		if(u.isPresent()) {
			Utilisateur util = u.get();
			util.setDisplayName(utilisateur.getDisplayName());
			util.setLastRole(utilisateur.getLastRole());
			util.setLastLogin(LocalDateTime.now());
			utilisateurRepository.save(util);
			//utilisateurRepository.updateInfo(username, utilisateur.getDisplayName(), utilisateur.getLastRole());
		} else {
			// sinon on crée l'utilisateur
			utilisateurRepository.save(utilisateur);
		}

		return utilisateur;
	}



	private Utilisateur newUtilisateur(final String username) {
		Utilisateur superAdmin = new Utilisateur();
		superAdmin.setUsername(username);
		superAdmin.setLastLogin(LocalDateTime.now());
		return superAdmin;
	}



}
