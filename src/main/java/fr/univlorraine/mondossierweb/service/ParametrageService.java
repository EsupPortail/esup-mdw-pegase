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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import lombok.extern.slf4j.Slf4j;


@Service
@SuppressWarnings("serial")
@Slf4j
public class ParametrageService implements Serializable {



	private transient boolean showSql;	


	@Autowired
	private transient ConfigController configController;
	@Autowired
	private transient LoggingSystem loggingSystem;


	
	private void setShowSql() {
		showSql = configController.isShowSqlActif();
	}
	

	public void refreshParameters() {
		refreshLogParameters();
	}

	public void refreshLogParameters() {
		setShowSql();
		if(showSql) {
			log.info("Activation des traces SQL");
			loggingSystem.setLogLevel("org.hibernate.SQL", LogLevel.DEBUG);
			loggingSystem.setLogLevel("org.hibernate.type", LogLevel.TRACE);
		} else {
			log.info("Désactivation des traces SQL");
			loggingSystem.setLogLevel("org.hibernate.SQL", LogLevel.ERROR);
			loggingSystem.setLogLevel("org.hibernate.type", LogLevel.ERROR);
		}
	}


	@PostConstruct
	public void init() {
		refreshParameters();
	}


}
