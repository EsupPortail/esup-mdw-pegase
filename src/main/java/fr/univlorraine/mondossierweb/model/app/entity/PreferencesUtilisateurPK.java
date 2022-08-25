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
package fr.univlorraine.mondossierweb.model.app.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the preferences_utilisateur database table.
 * 
 */
@Embeddable
@SuppressWarnings("serial")
public class PreferencesUtilisateurPK implements Serializable {

	@Column(insertable=false, updatable=false)
	private String username;

	@Column(name="pref_id")
	private String prefId;

	public PreferencesUtilisateurPK() {
		super();
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPrefId() {
		return this.prefId;
	}
	public void setPrefId(String prefId) {
		this.prefId = prefId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PreferencesUtilisateurPK)) {
			return false;
		}
		PreferencesUtilisateurPK castOther = (PreferencesUtilisateurPK)other;
		return 
			this.username.equals(castOther.username)
			&& this.prefId.equals(castOther.prefId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.prefId.hashCode();
		
		return hash;
	}
}