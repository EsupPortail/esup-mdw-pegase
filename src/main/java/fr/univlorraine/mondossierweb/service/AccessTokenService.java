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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;


@Service
@SuppressWarnings("serial")
@Slf4j
public class AccessTokenService implements Serializable {


	//@Value("${pegase.accesstoken.url}")
	private transient String tokenUrl;	
	//@Value("${pegase.accesstoken.username}")
	private transient String username;	
	//@Value("${pegase.accesstoken.password}")
	private transient String password;
	//@Value("${pegase.accesstoken.duration}")
	private transient Long duration;

	private String token;
	private LocalDateTime tokenCreatedDateTime;
	
	@Autowired
	private transient ConfigController configController;


	private String getTokenUrl(boolean force) {
		if(token == null || tokenUrl == null || force) {
			tokenUrl = configController.getAccesTokenUrl();
		}
		return tokenUrl;
	}
	
	private String getUsername(boolean force) {
		if(token == null || username == null || force) {
			username = configController.getAccesTokenUsername();
		}
		return username;
	}
	
	private String getPassword(boolean force) {
		if(token == null || password == null || force) {
			password = configController.getAccesTokenPassword();
		}
		return password;
	}
	
	private long getDuration(boolean force) {
		if(token == null || duration == null || force) {
			duration = Long.parseLong(configController.getAccesTokenDuration());
		}
		return duration;
	}
	
	private void getAccessToken(boolean forceParamRefresh) {
		String urlToken = getTokenUrl(forceParamRefresh);
		// Si l'url de récupération du token est paramétrée
		if(StringUtils.hasText(urlToken)){
			// Headers
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			// URL
			String url = urlToken + "?username={username}&password={password}&token=true";

			// Paramètres
			Map<String, String> uriVariables = new HashMap<>();
			uriVariables.put("username", getUsername(forceParamRefresh));
			uriVariables.put("password", getPassword(forceParamRefresh));

			// RestTemplate
			RestTemplate restTemplate = new RestTemplate();
			log.info("Demande du Access Token à Pegase...");
			ResponseEntity<String> tokenResponse = restTemplate.exchange(url, HttpMethod.POST, null, String.class, uriVariables);
			// Récupération du token dans la réponse
			if(tokenResponse!=null && tokenResponse.getStatusCode().is2xxSuccessful() 
				&& StringUtils.hasText(tokenResponse.getBody())) {
				this.token= tokenResponse.getBody();
				this.tokenCreatedDateTime = LocalDateTime.now();
				log.info("Access Token récupéré : {}", this.token);
			} else {
				log.error("Anomalie lors de la récupération de access token PEGASE : {}", tokenResponse != null ? tokenResponse.getStatusCode() : null );
			}
		}
	}

	@Synchronized
	private void checkToken(boolean forceParamRefresh) {
		log.info("Check Access Token - forceParamRefresh : {}",forceParamRefresh);
		if(forceParamRefresh) {
			getAccessToken(true);
		} else if(token == null || tokenExpired()) {
			getAccessToken(false);
		}
	}

	private boolean tokenExpired() {
		LocalDateTime ldt = LocalDateTime.now();
		return (tokenCreatedDateTime.until( ldt, ChronoUnit.HOURS ) > getDuration(false));
	}

	public String getToken(boolean forceParamRefresh) {
		if(forceParamRefresh) {
			token = null;
		}
		if(token == null) {
			checkToken(forceParamRefresh);
		}
		return token;
	}

	@Scheduled(fixedRate = 60000)
	public void cronJobCheckToken() {
		// TODO mécanisque pour forcer la maj du token depuis un témoin bdd pour que les instances de mdw mettent à jour le token
		
		checkToken(false);
	}

}
