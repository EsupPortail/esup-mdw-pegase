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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import fr.univlorraine.mondossierweb.model.ldap.entity.LdapPerson;
import lombok.extern.slf4j.Slf4j;


@Service
@SuppressWarnings("serial")
@Slf4j
public class LdapService implements Serializable {


	@Value("${ldap.login.attribute}")
	private transient String ldapLoginAttribute;	
	@Value("${ldap.displayname.attribute}")
	private transient String ldapDisplayNameAttribute;	
	@Value("${ldap.codetu.attribute}")
	private transient String ldapCodEtuAttribute;
	@Value("${ldap.mail.attribute}")
	private transient String ldapMailAttribute;
	@Value("${ldap.filtre.etudiant}")
	private transient String ldapFiltreEtudiant;
	@Value("${ldap.filtre.gestionnaire}")
	private transient String ldapFiltreGestionnaire;
	@Value("${ldap.filtre.limit}")
	private transient int maxLimitLdap;


	@Resource
	private transient LdapTemplate ldapTemplate;

	private class StudentAttributesMapper implements AttributesMapper {
		public Object mapFromAttributes(Attributes attrs) throws NamingException {
			LdapPerson person = new LdapPerson();
			person.setLogin((String)attrs.get(ldapLoginAttribute).get());
			person.setDisplayName((String)attrs.get(ldapDisplayNameAttribute).get());
			person.setCodeApprenant((String)attrs.get(ldapCodEtuAttribute).get());
			person.setMail(attrs.get(ldapMailAttribute)!=null?(String)attrs.get(ldapMailAttribute).get():null);
			return person;
		}
	}

	private class PersonAttributesMapper implements AttributesMapper {
		public Object mapFromAttributes(Attributes attrs) throws NamingException {
			LdapPerson person = new LdapPerson();
			person.setLogin((String)attrs.get(ldapLoginAttribute).get());
			person.setDisplayName((String)attrs.get(ldapDisplayNameAttribute).get());
			person.setMail((String)attrs.get(ldapMailAttribute).get());
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

		return (peoples!=null && !peoples.isEmpty()) ? Optional.ofNullable(peoples.get(0)) : null ;

	}
	
	public List<LdapPerson> searchStudentFromString(String chaine) {
		log.info("searchStudentByDisplayName : {}", chaine);

		ldapTemplate.setDefaultCountLimit(maxLimitLdap);
		
		if(!StringUtils.hasText(chaine)) {
			return new LinkedList<LdapPerson>();
		}
		String filtreDisplayName = "("+ldapDisplayNameAttribute+"=*" + chaine + "*)";
		String filtreCodeEtu = "("+ldapCodEtuAttribute+"=" + chaine + ")";
		List<LdapPerson> peoples = ldapTemplate.search("",  "(&"+ldapFiltreEtudiant+"(|"+filtreDisplayName+filtreCodeEtu+"))", new StudentAttributesMapper());

		return peoples;

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
}
