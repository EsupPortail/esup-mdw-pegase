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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.univlorraine.mondossierweb.converters.JPACryptoConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name="PREFERENCES_APPLICATION")
@EqualsAndHashCode(of = "prefId")
public class PreferencesApplication implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PREF_ID")
	private String prefId;
	
	@Column(name="PREF_DESC")
	private String prefDesc;
	
	@Column(name="VALEUR")
	private String valeur;
	
	@Column(name="SECRET")
	@Convert(converter = JPACryptoConverter.class)
	private String secret;
	
	@Column(name="ORDRE")
	private Integer ordre;
	
	@Column(name="DATA")
	@Lob
	private byte[] data;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="CAT_ID" )
	private PreferencesApplicationCategorie categorie;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="TYPE_ID" )
	private PreferencesApplicationType type;
	
	@OneToMany(mappedBy = "preference", fetch = FetchType.EAGER)
	private List<PreferencesApplicationValeurs> valeurs;
}
