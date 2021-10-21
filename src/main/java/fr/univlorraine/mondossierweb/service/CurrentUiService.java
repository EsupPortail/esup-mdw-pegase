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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.spring.annotation.UIScope;

import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.FluxSink.OverflowStrategy;
import reactor.core.publisher.UnicastProcessor;

@UIScope
@Service
public class CurrentUiService {

	@Value("${app.color}")
	private transient String appColorDefault;

	@PostConstruct
	private void init() {
		setAppColor(appColorDefault);
		setDarkModeFromMedia();
	}

	/* Theme : Couleur principale */

	private final UnicastProcessor<String> appColorProcessor = UnicastProcessor.create();
	private final FluxSink<String> appColorSink = appColorProcessor.sink(OverflowStrategy.LATEST);
	@Getter
	private final Flux<String> appColorFlux = appColorProcessor.replay(1).autoConnect();

	public void setAppColor(final String value) {
		appColorSink.next(value);
	}

	/* Theme : Mode sombre */

	private final UnicastProcessor<Boolean> darkModeProcessor = UnicastProcessor.create();
	private final FluxSink<Boolean> darkModeSink = darkModeProcessor.sink(OverflowStrategy.LATEST);
	@Getter
	private final Flux<Boolean> darkModeFlux = darkModeProcessor.replay(1).autoConnect();

	public void setDarkMode(final Boolean value) {
		darkModeSink.next(value);
	}

	/**
	 * Détecte la préférence du mode sombre.
	 */
	public void setDarkModeFromMedia() {
		/*UI.getCurrent()
			.getPage()
			.executeJs("return window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches")
			.then(Boolean.class, this::setDarkMode);*/
		this.setDarkMode(false);
	}

}
