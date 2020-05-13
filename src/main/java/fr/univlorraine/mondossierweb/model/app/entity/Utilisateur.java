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
package fr.univlorraine.mondossierweb.model.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vaadin.flow.data.provider.ListDataProvider;

import fr.univlorraine.mondossierweb.model.ldap.entity.LdapPerson;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SuppressWarnings("serial")
public class Utilisateur implements Serializable, UserDetails {

	@Id
	@NotBlank
	@Column(length = 10)
	private String username;

	/** Nom à afficher. */
	@Column(name="display_name",length = 64)
	private String displayName;
	
	/** Identifiant etudiant correspondant */
	@Column(name="code_etudiant")
	private String codeEtudiant;

	/** Date et heure de dernière connexion. */
	@Column(name="last_login")
	private LocalDateTime lastLogin;
	
	/** Roles lors de la dernière connexion. */
	@Column(name="last_role")
	private String lastRole;
	
	/* Mail */
	@Transient
	private String mail;
	
	/* identifiant de l'étudiant dont le dossier est consulté */
	@Transient
	private String codEtuDossier;
	
	/* Dernière Chaîne recherchée dans la vue Recherche */
	@Transient
	private String recherche;
	
	/* Dernier résultat de la recherche */
	@Transient
	private Collection<LdapPerson> resultatRecherche;

	/* Implémentation de UserDetails */
	@Transient
	private final List<GrantedAuthority> authorities = new ArrayList<>();

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return "-";
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * cf. https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Utilisateur)) {
			return false;
		}

		Utilisateur other = (Utilisateur) obj;

		return username != null && username.equals(other.getUsername());
	}

	/**
	 * cf. https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 31;
	}

}
