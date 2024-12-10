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
package fr.univlorraine.mondossierweb;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.AppShellSettings;
import fr.univlorraine.mondossierweb.controllers.ConfigController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Point d'entrée de l'application Spring Boot.
 */
@SpringBootApplication
@ConfigurationProperties
@EnableScheduling
@Slf4j
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    @Autowired
    private transient ConfigController configController;

    @Value("${app.url}")
    private String appUrl;

    /**
     * Configure le lancement de l'application via un serveur web embarqué.
     */
    public static void main(final String[] args) {
        build(new SpringApplicationBuilder(Application.class)).run(args);
    }

    private static SpringApplicationBuilder build(final SpringApplicationBuilder builder) {
        return builder.profiles("defaults");
    }

    /**
     * Configure le déploiement dans un container web. (WAR)
     */
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return build(builder).sources(Application.class);
    }

    /**
     * Configure l'ajout des favicon.
     */
    @Override
    public void configurePage(final AppShellSettings settings) {
        // Calcul du path pour les favicon
        String path = "";
        try {
            path = new URL(appUrl).getPath();
        } catch (MalformedURLException e) {
            log.warn("Impossible de récupérer le path depuis l'URL de l'application : {}", appUrl);
        }
        log.debug("path : {}", path);
        // Parametrage des favicon
        settings.addFavIcon("icon", path + "/" + configController.getUnivFavicon32Name(), "32x32");
        settings.addFavIcon("icon", path + "/" + configController.getUnivFavicon16Name(), "16x16");
    }
}
