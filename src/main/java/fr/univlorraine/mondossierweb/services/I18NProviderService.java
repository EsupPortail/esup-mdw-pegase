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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.vaadin.flow.i18n.I18NProvider;

import lombok.Getter;

/**
 * Service de messages I18N.
 * @author Adrien Colson
 */
@Service
@SuppressWarnings("serial")
public class I18NProviderService implements I18NProvider {

	@Autowired
	private transient MessageSource messageSource;

	@Getter
	private final List<Locale> providedLocales = List.of(Locale.FRANCE);

	/**
	 * @see com.vaadin.flow.i18n.I18NProvider#getTranslation(java.lang.String, java.util.Locale, java.lang.Object[])
	 */
	@Override
	public String getTranslation(final String key, final Locale locale, final Object... params) {
		Object[] convertedParams = Stream.of(params)
			.map(param -> {
				if (param instanceof LocalDateTime) {
					return Date.from(((LocalDateTime) param).atZone(ZoneId.systemDefault()).toInstant());
				} else if (param instanceof LocalDate) {
					return Date.from(((LocalDate) param).atTime(LocalTime.now()).atZone(ZoneId.systemDefault()).toInstant());
				} else if (param instanceof LocalTime) {
					return Date.from(((LocalTime) param).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
				} else {
					return param;
				}
			})
			.toArray();
		return messageSource.getMessage(key, convertedParams, locale);
	}

}
