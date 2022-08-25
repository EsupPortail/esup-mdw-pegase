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

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplication;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplicationCategorie;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesApplicationValeurs;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesServiceSync;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesServiceSyncPK;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateur;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateurPK;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesApplicationCategorieRepository;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesApplicationRepository;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesApplicationValeursRepository;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesServiceSyncRepository;
import fr.univlorraine.mondossierweb.model.app.repository.PreferencesUtilisateurRepository;
import fr.univlorraine.mondossierweb.utils.PrefUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PreferencesService {

	@Autowired
	ApplicationContext ctx;
	
	@Autowired
	private PreferencesUtilisateurRepository prefUtilRepository;

	@Autowired
	private PreferencesApplicationCategorieRepository preferencesApplicationCategorieRepository;

	@Autowired
	private PreferencesApplicationRepository preferencesApplicationRepository;

	@Autowired
	private PreferencesServiceSyncRepository preferencesServiceSyncRepository;

	@Autowired
	private PreferencesApplicationValeursRepository preferencesApplicationValeursRepository;

	private LocalDateTime lastSyncUpdate;

	@PostConstruct
	public void init() {
		lastSyncUpdate = LocalDateTime.now();
	}

	public boolean getBooleanValue(PreferencesApplication p) {
		return PrefUtils.getBooleanValue(p.getValeur());
	}

	public boolean getBooleanValue(PreferencesUtilisateur p) {
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
		Optional<PreferencesApplication> pref = preferencesApplicationRepository.findById(prefId);
		if(pref.isPresent()) {
			PreferencesApplication pa = pref.get();
			// Si c'est un parametre de type "secret" (cad crypté en base)
			if(pa.getType().isSecret()) {
				pa.setSecret(value);
			} else {
				pa.setValeur(value);
			}
			pa = preferencesApplicationRepository.save(pa);	
			return pa;
		}
		return null;
	}

	@Transactional
	public PreferencesApplication getPreferences(String prefId) {
		Optional<PreferencesApplication> pa = preferencesApplicationRepository.findById(prefId);
		if(pa.isPresent()) {
			return pa.get();
		}
		return null;
	}

	@Scheduled(fixedRate = 5000)
	public void cronJobCheckSync() {
		LocalDateTime newSyncUpdate = LocalDateTime.now();
		List<PreferencesServiceSync> lsync = preferencesServiceSyncRepository.findByLastUpdateAfter(lastSyncUpdate);
		if(lsync != null && !lsync.isEmpty()) {
			lsync.forEach(this::refreshServiceParameters);
		}
		lastSyncUpdate = newSyncUpdate;
	}

	private void refreshServiceParameters(PreferencesServiceSync sync) {
		log.info("Mise à jour du service {}->{} demandés par {} ", sync.getId().getServiceName(), sync.getId().getMethodName(), sync.getUsername());
		Object bean = ctx.getBean(sync.getId().getServiceName());
		try {
			bean.getClass().getMethod(sync.getId().getMethodName()).invoke(bean);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Transactional
	public boolean forceServiceSync(String serviceName, String methodName, Optional<String> username) {
		serviceName = getBeanNameFromClass(serviceName);
		log.info("Forcer sync des paramètres du service {} demandés par {} ", serviceName, username.orElse(null));
		try {
			PreferencesServiceSyncPK psspk = new PreferencesServiceSyncPK();
			psspk.setServiceName(serviceName);
			psspk.setMethodName(methodName);
			Optional<PreferencesServiceSync> pss = preferencesServiceSyncRepository.findById(psspk);
			PreferencesServiceSync p;
			if(pss.isPresent()) {
				p = pss.get();
			} else {
				p = new PreferencesServiceSync();
				p.setId(new PreferencesServiceSyncPK());
				p.getId().setServiceName(serviceName);
				p.getId().setMethodName(methodName);
			}
			p.setUsername(username.orElse(null));
			p.setLastUpdate(LocalDateTime.now());
			preferencesServiceSyncRepository.save(p);
			return true;
		}catch(Exception e) {
			log.error("Erreur lors de la sync des paramètres du service {} demandés par {} ", serviceName, username.orElse(null), e);
			return false;
		}
	}

	private String getBeanNameFromClass(String serviceName) {
		if(serviceName.contains(".")) {
			// suppression du nom du package
			serviceName = serviceName.substring(serviceName.lastIndexOf(".") + 1);
		}
		char[] c = serviceName.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		return new String(c);
	}

}
