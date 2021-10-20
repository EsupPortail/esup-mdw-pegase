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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the preferences_utilisateur database table.
 * 
 */
@Entity
@Table(name="preferences_utilisateur")
@NamedQuery(name="PreferencesUtilisateur.findAll", query="SELECT p FROM PreferencesUtilisateur p")
public class PreferencesUtilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PreferencesUtilisateurPK id;

	@Column(name="last_update")
	private LocalDateTime lastUpdate;

	private String valeur;

	public PreferencesUtilisateur() {
	}

	public PreferencesUtilisateurPK getId() {
		return this.id;
	}

	public void setId(PreferencesUtilisateurPK id) {
		this.id = id;
	}

	public LocalDateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getValeur() {
		return this.valeur;
	}

	public void setValue(String valeur) {
		this.valeur = valeur;
	}

}