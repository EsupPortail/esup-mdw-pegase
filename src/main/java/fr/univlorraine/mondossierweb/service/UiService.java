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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.FluxSink.OverflowStrategy;
import reactor.core.publisher.UnicastProcessor;

@Service
@SuppressWarnings("serial")
public class UiService implements VaadinServiceInitListener {

	/* Événements UIs */
	private final UnicastProcessor<List<UiInfo>> uiInfosProcessor = UnicastProcessor.create();
	private final FluxSink<List<UiInfo>> uiInfosSink = uiInfosProcessor.sink(OverflowStrategy.LATEST);
	@Getter
	private final transient Flux<List<UiInfo>> uiInfosFlux = uiInfosProcessor.defaultIfEmpty(List.of()).replay(1).autoConnect();

	/* Liste d'UiInfos */
	@Getter
	private final transient List<UiInfo> uiInfos = Collections.synchronizedList(new ArrayList<>());

	/**
	 * UI informations.
	 */
	@Data
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public class UiInfo {
		@EqualsAndHashCode.Include
		private int id;
		private String username;
		private String ip;
		private String location;
		private String browser;
	}

	/**
	 * @param  ui ui
	 * @return    informations sur l'ui
	 */
	private UiInfo createUiInfo(final UI ui) {
		final UiInfo uiInfo = new UiInfo();
		uiInfo.setId(System.identityHashCode(ui));
		uiInfo.setUsername(getUsernameFromUI(ui));
		uiInfo.setIp(ui.getSession().getBrowser().getAddress());
		uiInfo.setLocation(ui.getInternals().getActiveViewLocation().getPathWithQueryParameters());
		uiInfo.setBrowser(getBrowserFromUI(ui));
		return uiInfo;
	}

	/**
	 * @param  ui UI
	 * @return    nom d'utilisateur associé à l'ui
	 */
	private String getUsernameFromUI(final UI ui) {
		return Optional.ofNullable(ui)
			.map(UI::getSession)
			.map(VaadinSession::getSession)
			.map(session -> session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY))
			.filter(SecurityContext.class::isInstance)
			.map(SecurityContext.class::cast)
			.map(SecurityContext::getAuthentication)
			.map(Authentication::getName)
			.orElse("-");
	}

	/**
	 * @param  ui UI
	 * @return    navigateur associé à l'ui
	 */
	private String getBrowserFromUI(final UI ui) {
		return Optional.ofNullable(ui)
			.map(UI::getSession)
			.map(VaadinSession::getBrowser)
			.map(browser -> {
				final StringBuilder sb = new StringBuilder();
				if (browser.isChrome()) {
					sb.append("Chrome");
				} else if (browser.isFirefox()) {
					sb.append("Firefox");
				} else if (browser.isIE()) {
					sb.append("IE");
				} else if (browser.isEdge()) {
					sb.append("Edge");
				} else if (browser.isSafari()) {
					sb.append("Safari");
				} else if (browser.isOpera()) {
					sb.append("Opera");
				} else {
					return browser.getBrowserApplication();
				}
				sb.append(' ')
					.append(browser.getBrowserMajorVersion());
				if (browser.getBrowserMinorVersion() > 0) {
					sb.append('.')
						.append(browser.getBrowserMinorVersion());
				}
				return sb.toString();
			})
			.orElse("-");
	}

	/**
	 * @see com.vaadin.flow.server.VaadinServiceInitListener#serviceInit(com.vaadin.flow.server.ServiceInitEvent)
	 */
	@Override
	public void serviceInit(final ServiceInitEvent event) {
		event.getSource().addUIInitListener(uiInitEvent -> registerUi(uiInitEvent.getUI()));
	}

	/**
	 * Gère une nouvelle UI.
	 * @param ui ui à enregistrer
	 */
	public void registerUi(final UI ui) {
		UiInfo uiInfo = createUiInfo(ui);
		uiInfos.add(uiInfo);

		/* Notifie l'ajout d'ui. */
		uiInfosSink.next(uiInfos);

		/* Suit les changements de vue */
		Registration trackViewChangesRegistration = ui.addAfterNavigationListener(event -> {
			uiInfo.setLocation(event.getLocation().getPathWithQueryParameters());
			uiInfosSink.next(uiInfos);
		});

		/* Désinscrit l'ui lorsqu'elle est détachée */
		ui.addDetachListener(event -> {
			trackViewChangesRegistration.remove();
			uiInfos.remove(uiInfo);
			uiInfosSink.next(uiInfos);
		});
	}

}
