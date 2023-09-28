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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="PREFERENCES_APPLICATION_CATEGORIE")
public class PreferencesApplicationCategorie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CAT_ID")
	private Integer catId;
	
	@Column(name="CAT_DESC")
	private String catDesc;
	
	@Column(name="ORDRE")
	private Integer ordre;
	
	@OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER)
	private List<PreferencesApplication> preferencesApplication;

}
