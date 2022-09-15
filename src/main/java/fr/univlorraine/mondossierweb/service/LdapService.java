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

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import fr.univlorraine.mondossierweb.model.ldap.entity.LdapPerson;
import lombok.extern.slf4j.Slf4j;


@Service
@SuppressWarnings("serial")
@Slf4j
public class LdapService implements Serializable {


	@Value("${ldap.login.attribute}")
	private transient String ldapLoginAttribute;	
	private transient String ldapDisplayNameAttribute;	
	private transient String ldapCodEtuAttribute;
	private transient String ldapMailAttribute;
	private transient String ldapFiltreEtudiant;
	private transient String ldapFiltreGestionnaire;

	@Autowired
	private transient ConfigController configController;


	@Resource
	private transient LdapTemplate ldapTemplate;

	@PostConstruct
	public void init() {	
		refreshParameters();
	}
	
	private class StudentAttributesMapper implements AttributesMapper<LdapPerson> {
		public LdapPerson mapFromAttributes(Attributes attrs) throws NamingException {
			LdapPerson person = new LdapPerson();
			person.setLogin((String)attrs.get(ldapLoginAttribute).get());
			person.setDisplayName((String)attrs.get(ldapDisplayNameAttribute).get());
			person.setCodeApprenant((String)attrs.get(ldapCodEtuAttribute).get());
			person.setMail(attrs.get(ldapMailAttribute)!=null?(String)attrs.get(ldapMailAttribute).get():null);
			return person;
		}
	}

	private class PersonAttributesMapper implements AttributesMapper<LdapPerson> {
		public LdapPerson mapFromAttributes(Attributes attrs) throws NamingException {
			LdapPerson person = new LdapPerson();
			person.setLogin((String)attrs.get(ldapLoginAttribute).get());
			try {
				person.setDisplayName((String)attrs.get(ldapDisplayNameAttribute).get());
				person.setMail((String)attrs.get(ldapMailAttribute).get());
			}catch(Exception e) {
				log.error("Erreur lors de la récupération des informations du compte ldap {}", person.getLogin(), e);	
			}
			return person;
		}
	}


	public LdapPerson findStudentByUid(String username) {
		log.info("findStudentByUid : {}", username);

		List<LdapPerson> peoples = ldapTemplate.search("",  "(&"+ldapFiltreEtudiant+"("+ldapLoginAttribute+"=" + username + "))", new StudentAttributesMapper());

		return (peoples!=null && !peoples.isEmpty()) ? peoples.get(0) : null ;

	}

	public LdapPerson findAdministratorByUid(String username) {
		log.info("findTeacherByUid : {}", username);

		List<LdapPerson> peoples = ldapTemplate.search("",  "(&"+ldapFiltreGestionnaire+"("+ldapLoginAttribute+"=" + username + "))", new PersonAttributesMapper());

		return (peoples!=null && !peoples.isEmpty()) ? peoples.get(0) : null ;
	}


	public Optional<LdapPerson> findByUid(String username) {
		log.info("findByUid : {}", username);

		List<LdapPerson> peoples = ldapTemplate.search("",  "(&("+ldapLoginAttribute+"=" + username + "))", new PersonAttributesMapper());

		return (peoples!=null && !peoples.isEmpty()) ? Optional.ofNullable(peoples.get(0)) : Optional.empty();

	}

	public String getStudentMailByCodeApprenant(String code) {
		log.info("findStudentByCodeApprenant : {}", code);

		List<LdapPerson> peoples = ldapTemplate.search("",  "(&"+ldapFiltreEtudiant+"("+ldapCodEtuAttribute+"=" + code + "))", new StudentAttributesMapper());

		//Si on a récupéré qu'une seule entrée
		if(peoples != null && peoples.size()==1) {
			// récupération du mail de la personne
			LdapPerson people = peoples.get(0);
			return people.getMail();
		}
		return null;
	}

	public void refreshParameters() {
		getLdapFiltreEtudiant();
		getLdapFiltreGestionnaire();
		getLdapDisplayNameAttribute();
		getLdapMailAttribute();
		getLdapCodEtuAttribute();
	}

	private void getLdapFiltreEtudiant() {
		ldapFiltreEtudiant = configController.getLdapFiltreEtudiant();
	}

	private void getLdapFiltreGestionnaire() {
		ldapFiltreGestionnaire = configController.getLdapFiltreGestionnaire();
	}

	private void getLdapDisplayNameAttribute() {
		ldapDisplayNameAttribute = configController.getLdapDisplayNameAttribute();
	}

	private void getLdapMailAttribute() {
		ldapMailAttribute = configController.getLdapMailAttribute();
	}

	private void getLdapCodEtuAttribute() {
		ldapCodEtuAttribute = configController.getLdapCodEtuAttribute();
	}



}
