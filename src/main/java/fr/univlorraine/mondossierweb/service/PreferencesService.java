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
package fr.univlorraine.mondossierweb.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateur;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateurPK;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesUtilisateurRepository;
import fr.univlorraine.mondossierweb.utils.PrefUtils;

@Service
public class PreferencesService {
	
	@Autowired
	private transient PreferencesUtilisateurRepository prefUtilRepository;
	
	public boolean getBooleanPref(String username, String pref) {
		PreferencesUtilisateurPK ppk = new PreferencesUtilisateurPK();
		ppk.setUsername(username);
		ppk.setPrefId(pref);
		Optional<PreferencesUtilisateur> p = prefUtilRepository.findById(ppk);
		if(p.isPresent()) {
			return PrefUtils.getBooleanValue(p.get().getValeur());
		}
		return false;
	}


	public void saveUserPref(String username, String pref, String value) {
		PreferencesUtilisateurPK ppk = new PreferencesUtilisateurPK();
		ppk.setUsername(username);
		ppk.setPrefId(pref);
		PreferencesUtilisateur p = new PreferencesUtilisateur();
		p.setId(ppk);
		p.setValeur(value);
		p.setLastUpdate(LocalDateTime.now());
		prefUtilRepository.save(p);
	}
	
	public void saveUserPref(String username, String pref, Boolean value) {
		saveUserPref(username, pref, value.toString());
	}

}
