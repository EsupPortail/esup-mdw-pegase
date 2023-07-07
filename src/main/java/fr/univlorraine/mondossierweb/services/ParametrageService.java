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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.net.SMTPAppender;
import fr.univlorraine.mondossierweb.controllers.ConfigController;
import jakarta.annotation.PostConstruct;
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
		refreshSmtpParameters();
	}

	public void refreshSmtpParameters() {
		LoggerContext logCtx  = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger logger = logCtx.getLogger(Logger.ROOT_LOGGER_NAME);
		SMTPAppender mailAppender = (SMTPAppender) logger.getAppender("MAIL");
		logMailAppender(mailAppender);
		mailAppender.setSmtpHost(configController.getSmtpHost());
		mailAppender.setSmtpPort(Integer. valueOf(configController.getSmtpPort()));
		mailAppender.setUsername(configController.getSmtpUsername());
		mailAppender.setPassword(configController.getSmtpPassword());
		mailAppender.setFrom(configController.getSmtpFrom());
		mailAppender.getToList().clear();
		String mails = configController.getLogMailTo();
		if(mails !=null && mails.contains(";")) {
			String[] tmails = mails.split(";");
			for (String m : tmails) {
				mailAppender.addTo(m);
			}
		} else {
			mailAppender.addTo(configController.getLogMailTo());
		}
		mailAppender.stop();
		mailAppender.start();
		logMailAppender(mailAppender);
	}

	private void logMailAppender(SMTPAppender mailAppender) {
		log.info("** MailAppender : {}", mailAppender.getClass());
		log.info("**** smtp host : {}", mailAppender.getSmtpHost());
		log.info("**** smtp port : {}", mailAppender.getSmtpPort());
		log.info("**** username : {}", mailAppender.getUsername());
		log.info("**** from : {}", mailAppender.getFrom());
		log.info("**** to : {}", mailAppender.getToAsListOfString());
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

	public void refreshFavIconParameters() {
		// Récupération des favicon en base et copie dans le repertoire static
		writeFaviconToRessources(configController.getUnivFavicon16(),  configController.getUnivFavicon16Name());
		writeFaviconToRessources(configController.getUnivFavicon32(), configController.getUnivFavicon32Name());
	}


	private void writeFaviconToRessources(byte[] favicon, String name) {
		if(favicon != null) {
			ClassLoader classLoader = getClass().getClassLoader();
			File outputFile = new File(classLoader.getResource(".").getFile() + "/static/" + name);
			try {
				log.error("Ecriture du fichier {}", outputFile.getAbsolutePath());
				FileUtils.writeByteArrayToFile(outputFile, favicon);
			} catch (IOException e) {
				log.error("Erreur lors de l'écriture du favicon dans le répertoire ressources", e);
			}
		} else {
			log.info("Favicon {} null",name);
		}

	}


	@PostConstruct
	public void init() {
		refreshParameters();
	}


}
