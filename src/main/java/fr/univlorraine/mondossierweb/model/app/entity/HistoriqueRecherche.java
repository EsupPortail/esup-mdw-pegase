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

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the historique_recherche database table.
 * 
 */
@Entity
@Table(name="historique_recherche")
@NamedQuery(name="HistoriqueRecherche.findAll", query="SELECT h FROM HistoriqueRecherche h")
public class HistoriqueRecherche implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HistoriqueRecherchePK id;

	@Column(name="uid_apprenant")
	private String uidApprenant;
	
	@Column(name="code_apprenant")
	private String codeApprenant;

	@Column(name="display_name")
	private String displayName;

	private String mail;

	public HistoriqueRecherche() {
	}

	public HistoriqueRecherchePK getId() {
		return this.id;
	}

	public void setId(HistoriqueRecherchePK id) {
		this.id = id;
	}
	
	
	public String getUidApprenant() {
		return uidApprenant;
	}

	public void setUidApprenant(String uidApprenant) {
		this.uidApprenant = uidApprenant;
	}

	public String getCodeApprenant() {
		return this.codeApprenant;
	}

	public void setCodeApprenant(String codeApprenant) {
		this.codeApprenant = codeApprenant;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}