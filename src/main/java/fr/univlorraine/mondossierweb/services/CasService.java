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
package fr.univlorraine.mondossierweb.services;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Service
@SuppressWarnings("serial")
@Slf4j
public class CasService implements Serializable {


	@Value("${cas.login.attribute}")
	@Getter
	private transient String casLoginAttribute;
	@Getter
	private transient String casDisplayNameAttribute;
	@Getter
	private transient String casCodEtuAttribute;
	@Getter
	private transient String casMailAttribute;
	@Getter
	private transient Map<String, List<String>> casFiltreEtudiant;
	@Getter
	private transient Map<String, List<String>> casFiltreGestionnaire;

	@Autowired
	private transient ConfigController configController;


	@PostConstruct
	public void init() {	
		refreshParameters();
	}

	public void refreshParameters() {
		setCasFiltreEtudiant();
		setCasFiltreGestionnaire();
		setcasDisplayNameAttribute();
		setcasMailAttribute();
		setcasCodEtuAttribute();
	}

	private void setCasFiltreEtudiant() {
		casFiltreEtudiant = configController.getCasFiltreEtudiant();
	}

	private void setCasFiltreGestionnaire() {
		casFiltreGestionnaire = configController.getCasFiltreGestionnaire();
	}

	private void setcasDisplayNameAttribute() {
		casDisplayNameAttribute = configController.getCasDisplayNameAttribute();
	}

	private void setcasMailAttribute() {
		casMailAttribute = configController.getCasMailAttribute();
	}

	private void setcasCodEtuAttribute() {
		casCodEtuAttribute = configController.getCasCodEtuAttribute();
	}



}
