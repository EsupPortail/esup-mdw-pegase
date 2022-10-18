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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinService;

/**
 * UI Cookie Service
 *
 * @author Matthieu Manginot
 */
@SuppressWarnings("serial")
@Service
public class CookieService implements Serializable {
	/** Cookie data separator. */
	public static final String COOKIE_SEPARATOR = "-";
	/** Cookie width separator. */
	public static final String COOKIE_WIDTH_SEPARATOR = "_";

	@Autowired
	private transient NotificationService notificationService;
	@Autowired
	private transient BuildProperties buildProperties;

	/**
	 * Ajout d'un cookie.
	 *
	 * @param name
	 *            name
	 * @param value
	 *            value
	 */
	public void addCookie(final Optional<String> name, final String value) {
		if(name.isPresent()) {
			Cookie cookie = new Cookie(buildProperties.getArtifact() + COOKIE_SEPARATOR + name.get(), value != null ? value : "");
			/* Durée de vie - 5j */
			cookie.setMaxAge(60 * 60 * 24 * 5);
			cookie.setSecure(VaadinService.getCurrentRequest().isSecure());
			cookie.setPath("/");
			cookie.setHttpOnly(true);
			/* Save */
			VaadinService.getCurrentResponse().addCookie(cookie);
		} else {
			Assert.hasText("", "name cannot be null!");
		}
	}

	/**
	 * Gestion des cookies : Lecture et sauvegarde.
	 * Ordre, visibilités et tailles des colonnes pour chaque grid.
	 *
	 * @param <T>
	 * @param grid
	 *            grid à gérér dans les cookies
	 */
	public <T> void cookies(final Grid<T> grid) {
		if (VaadinService.getCurrentRequest() != null) {
			/* Récupération des cookies */
			Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
			/* Pour chaque grid */
			Arrays.asList(grid).forEach(g -> {
				LinkedHashSet<String> ids = new LinkedHashSet<>();
				/* Récupération du cookie */
				Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(buildProperties.getArtifact() + COOKIE_SEPARATOR + g.getId().orElse(null))).findFirst().orElse(null);
				/* Si présent */
				if (cookie != null && StringUtils.hasText(cookie.getValue())) {
					LinkedHashSet<String> idWidthCookies = new LinkedHashSet<>();
					Collections.addAll(idWidthCookies, cookie.getValue().split(COOKIE_SEPARATOR));
					HashMap<String, String> idWidthMap = new HashMap<>();
					idWidthCookies.forEach(s -> {
						String[] t = s.split(COOKIE_WIDTH_SEPARATOR);
						/* Conservation des ids uniquement présent dans la grid */
						if (t.length > 0 && g.getColumnByKey(t[0]) != null) {
							ids.add(t[0]);
							if (t.length == 1) {
								idWidthMap.put(t[0], "");
							} else if (t.length == 2) {
								idWidthMap.put(t[0], t[1]);
							}
						}
					});

					if (!idWidthMap.isEmpty()) {
						/* Taille des colonnes */
						g.getColumns().forEach(c -> {
							String width = idWidthMap.get(c.getKey());
							if (StringUtils.hasText(width) && !width.equals("null")) {
								c.setWidth(width);
							} else {
								c.setAutoWidth(true);
							}
							/* Ajout des colonnes manquantes */
							if (!ids.contains(c.getKey())) {
								ids.add(c.getKey());
							}
						});
						/* Ordres des colonnes */
						g.setColumnOrder(ids.stream().map(g::getColumnByKey).collect(Collectors.toList()));
					}
				}
				/* Ajout d'un listener sur l'ordre des colonnes pour mise à jour du cookie */
				g.addColumnReorderListener(e -> addCookie(g.getId(),
					e.getColumns().stream().filter(Objects::nonNull).map(c -> c.getKey() + COOKIE_WIDTH_SEPARATOR + c.getWidth()).collect(Collectors.joining(COOKIE_SEPARATOR))));
				/* Ajout d'un listener sur la taille des colonnes pour mise à jour du cookie */
				g.addColumnResizeListener(e -> addCookie(g.getId(),
					g.getColumns().stream().filter(Objects::nonNull).map(c -> c.getKey() + COOKIE_WIDTH_SEPARATOR + c.getWidth()).collect(Collectors.joining(COOKIE_SEPARATOR))));
			});
		}
	}

	/** Suppression des cookies liés à l'application. */
	public void removeAllCookies() {
		/* Récupération des cookies */
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		/* Suppression */
		Arrays.stream(cookies).filter(c -> c.getName().startsWith(buildProperties.getArtifact() + COOKIE_SEPARATOR))
		.forEach(cookie -> addCookie(Optional.of(cookie.getName().replace(buildProperties.getArtifact() + COOKIE_SEPARATOR, "")), ""));
		/* Notification */
		notificationService.success(VaadinService.getCurrent().getInstantiator().getI18NProvider().getTranslation("cookies.removeAll.success", Locale.getDefault()));
	}
}
