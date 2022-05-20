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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplication;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplicationCategorie;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplicationValeurs;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateur;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateurPK;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesApplicationCategorieRepository;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesApplicationRepository;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesApplicationTypeRepository;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesApplicationValeursRepository;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesUtilisateurRepository;
import fr.univlorraine.mondossierweb.utils.PrefUtils;

@Service
public class PreferencesService {
	
	@Autowired
	private transient PreferencesUtilisateurRepository prefUtilRepository;
	
	@Autowired
	private transient PreferencesApplicationCategorieRepository preferencesApplicationCategorieRepository;
	
	@Autowired
	private transient PreferencesApplicationRepository preferencesApplicationRepository;
	
	@Autowired
	private transient PreferencesApplicationValeursRepository preferencesApplicationValeursRepository;

	
	
	public boolean getBooleanPref(PreferencesUtilisateur p) {
		return PrefUtils.getBooleanValue(p.getValeur());
	}

	public Optional<PreferencesUtilisateur> getPreference(String username, String pref) {
		PreferencesUtilisateurPK ppk = new PreferencesUtilisateurPK();
		ppk.setUsername(username);
		ppk.setPrefId(pref);
		return prefUtilRepository.findById(ppk);
	}

	public void saveUserPref(String username, String pref, String value) {
		PreferencesUtilisateurPK ppk = new PreferencesUtilisateurPK();
		ppk.setUsername(username);
		ppk.setPrefId(pref);
		PreferencesUtilisateur p = new PreferencesUtilisateur();
		p.setId(ppk);
		p.setValue(value);
		p.setLastUpdate(LocalDateTime.now());
		prefUtilRepository.save(p);
	}
	
	public void saveUserPref(String username, String pref, Boolean value) {
		saveUserPref(username, pref, value.toString());
	}
	
	public List<PreferencesApplicationCategorie> getCategories() {
		return preferencesApplicationCategorieRepository.findAllByOrderByOrdre();
	}
	
	@Transactional
	public PreferencesApplicationValeurs getPreferencesApplicationValeurs(PreferencesApplication pa) {
		if(pa.getValeur() == null) {
			return null;
		}
		List<PreferencesApplicationValeurs> lp = preferencesApplicationValeursRepository.findAllByPreferenceAndValeur(pa, pa.getValeur());
		if(lp == null || lp.isEmpty()) {
			return null;
		}
		return lp.get(0);
	}
	
	

	@Transactional
	public PreferencesApplication savePref(String prefId, String value) {
		PreferencesApplication pref = preferencesApplicationRepository.getById(prefId);
		if(pref != null) {
			// Si c'est un parametre de type "secret" (cad crypté en base)
			if(pref.getType().isSecret()) {
				pref.setSecret(value);
			} else {
				pref.setValeur(value);
			}
			pref = preferencesApplicationRepository.save(pref);		
		}
		return pref;
	}

}
