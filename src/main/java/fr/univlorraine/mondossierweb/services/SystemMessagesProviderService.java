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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.vaadin.flow.server.CustomizedSystemMessages;
import com.vaadin.flow.server.SystemMessages;
import com.vaadin.flow.server.SystemMessagesInfo;
import com.vaadin.flow.server.SystemMessagesProvider;

/**
 * Service des messages systèmes Vaadin.
 * @author Adrien Colson
 */
@Service
@SuppressWarnings("serial")
public class SystemMessagesProviderService implements SystemMessagesProvider {

	@Autowired
	private transient MessageSource messageSource;

	@Override
	public SystemMessages getSystemMessages(final SystemMessagesInfo systemMessagesInfo) {
		CustomizedSystemMessages systemMessages = new CustomizedSystemMessages();
		systemMessages.setSessionExpiredCaption(messageSource.getMessage("vaadin.sessionExpired.caption", null, systemMessagesInfo.getLocale()));
		systemMessages.setSessionExpiredMessage(messageSource.getMessage("vaadin.sessionExpired.message", null, systemMessagesInfo.getLocale()));
		systemMessages.setInternalErrorCaption(messageSource.getMessage("vaadin.internalError.caption", null, systemMessagesInfo.getLocale()));
		systemMessages.setInternalErrorMessage(messageSource.getMessage("vaadin.internalError.message", null, systemMessagesInfo.getLocale()));
		systemMessages.setCookiesDisabledCaption(messageSource.getMessage("vaadin.cookiesDisabled.caption", null, systemMessagesInfo.getLocale()));
		systemMessages.setCookiesDisabledMessage(messageSource.getMessage("vaadin.cookiesDisabled.message", null, systemMessagesInfo.getLocale()));
		return systemMessages;
	}

}
