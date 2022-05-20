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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name="PREFERENCES_APPLICATION_VALEURS")
@EqualsAndHashCode(of = {"valId"})
public class PreferencesApplicationValeurs implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VAL_ID")
	private Integer valId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="PREF_ID" )
	private PreferencesApplication preference;
	
	@Column(name="VALEUR")
	private String valeur;
	
	@Column(name="LIBELLE")
	private String libelle;
	
}
