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
package fr.univlorraine.mondossierweb.model.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class Utilisateur implements Serializable, UserDetails {


	private String username;

	/** Nom à afficher. */
	private String displayName;
	
	/** Identifiant etudiant correspondant */
	private String codeEtudiant;

	/** Date et heure de dernière connexion. */
	private LocalDateTime lastLogin;
	
	/** Roles lors de la dernière connexion. */
	private String lastRole;
	
	/* Mail */
	private String mail;

	/* Implémentation de UserDetails */
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
