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

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
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


	//@Value("${ldap.login.attribute}")
	private transient String ldapLoginAttribute;	
	//@Value("${ldap.displayname.attribute}")
	private transient String ldapDisplayNameAttribute;	
	//@Value("${ldap.codetu.attribute}")
	private transient String ldapCodEtuAttribute;
	//@Value("${ldap.mail.attribute}")
	private transient String ldapMailAttribute;
	//@Value("${ldap.filtre.etudiant}")
	private transient String ldapFiltreEtudiant;
	//@Value("${ldap.filtre.gestionnaire}")
	private transient String ldapFiltreGestionnaire;
	
	@Autowired
	private transient ConfigController configController;


	@Resource
	private transient LdapTemplate ldapTemplate;

	private class StudentAttributesMapper implements AttributesMapper {
		public Object mapFromAttributes(Attributes attrs) throws NamingException {
			LdapPerson person = new LdapPerson();
			person.setLogin((String)attrs.get(getLdapLoginAttribute()).get());
			person.setDisplayName((String)attrs.get(getLdapDisplayNameAttribute()).get());
			person.setCodeApprenant((String)attrs.get(getLdapCodEtuAttribute()).get());
			person.setMail(attrs.get(getLdapMailAttribute())!=null?(String)attrs.get(getLdapMailAttribute()).get():null);
			return person;
		}
	}

	private class PersonAttributesMapper implements AttributesMapper {
		public Object mapFromAttributes(Attributes attrs) throws NamingException {
			LdapPerson person = new LdapPerson();
			person.setLogin((String)attrs.get(getLdapLoginAttribute()).get());
			person.setDisplayName((String)attrs.get(getLdapDisplayNameAttribute()).get());
			person.setMail((String)attrs.get(getLdapMailAttribute()).get());
			return person;
		}
	}

	
	public LdapPerson findStudentByUid(String username) {
		log.info("findStudentByUid : {}", username);

		List<LdapPerson> peoples = ldapTemplate.search("",  "(&"+getLdapFiltreEtudiant()+"("+getLdapLoginAttribute()+"=" + username + "))", new StudentAttributesMapper());

		return (peoples!=null && !peoples.isEmpty()) ? peoples.get(0) : null ;

	}

	public LdapPerson findAdministratorByUid(String username) {
		log.info("findTeacherByUid : {}", username);

		List<LdapPerson> peoples = ldapTemplate.search("",  "(&"+getLdapFiltreGestionnaire()+"("+getLdapLoginAttribute()+"=" + username + "))", new PersonAttributesMapper());

		return (peoples!=null && !peoples.isEmpty()) ? peoples.get(0) : null ;
	}

	
	public Optional<LdapPerson> findByUid(String username) {
		log.info("findByUid : {}", username);

		List<LdapPerson> peoples = ldapTemplate.search("",  "(&("+getLdapLoginAttribute()+"=" + username + "))", new PersonAttributesMapper());

		return (peoples!=null && !peoples.isEmpty()) ? Optional.ofNullable(peoples.get(0)) : null ;

	}
	
	public String getStudentMailByCodeApprenant(String code) {
		log.info("findStudentByCodeApprenant : {}", code);

		List<LdapPerson> peoples = ldapTemplate.search("",  "(&"+getLdapFiltreEtudiant()+"("+getLdapCodEtuAttribute()+"=" + code + "))", new StudentAttributesMapper());

		//Si on a récupéré qu'une seule entrée
		if(peoples != null && peoples.size()==1) {
			// récupération du mail de la personne
			LdapPerson people = peoples.get(0);
			return people.getMail();
		}
		return null;
	}

	private String getLdapFiltreEtudiant() {
		if(ldapFiltreEtudiant == null) {
			ldapFiltreEtudiant = configController.getLdapFiltreEtudiant();
		}
		return ldapFiltreEtudiant;
	}
	
	private String getLdapFiltreGestionnaire() {
		if(ldapFiltreGestionnaire == null) {
			ldapFiltreGestionnaire = configController.getLdapFiltreGestionnaire();
		}
		return ldapFiltreGestionnaire;
	}
	

	private String getLdapLoginAttribute() {
		if(ldapLoginAttribute == null) {
			ldapLoginAttribute = configController.getLdapLoginAttribute();
		}
		return ldapLoginAttribute;
	}
	
	private String getLdapDisplayNameAttribute() {
		if(ldapDisplayNameAttribute == null) {
			ldapDisplayNameAttribute = configController.getLdapDisplayNameAttribute();
		}
		return ldapDisplayNameAttribute;
	}
	
	private String getLdapMailAttribute() {
		if(ldapMailAttribute == null) {
			ldapMailAttribute = configController.getLdapMailAttribute();
		}
		return ldapMailAttribute;
	}
	
	private String getLdapCodEtuAttribute() {
		if(ldapCodEtuAttribute == null) {
			ldapCodEtuAttribute = configController.getLdapCodEtuAttribute();
		}
		return ldapCodEtuAttribute;
	}

	
	
}
