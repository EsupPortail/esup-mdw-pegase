/**
 *
 *  ESUP-Portail MONDOSSIERWEB - Copyright (c) 2020 ESUP-Portail consortium
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
package fr.univlorraine.mondossierweb.test.integration.layout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import fr.univlorraine.mondossierweb.service.AppUserDetailsService;
import fr.univlorraine.mondossierweb.test.integration.AbstractIT;
import fr.univlorraine.mondossierweb.utils.security.SecurityUtils;

public class DrawerMenuIT extends AbstractIT {

	@Test
	public void drawerMenuUser() {
		webDriverGet("");

		Assertions.assertNotNull(webDriver.findElement(By.xpath("//*[text()='Accueil']/ancestor::a")),
			"Le bouton 'Accueil' est introuvable.");
		Assertions.assertNotNull(webDriver.findElement(By.xpath("//*[text()='Documentation']/ancestor::a")),
			"Le bouton 'Documentation' est introuvable.");

		Assertions.assertThrows(NoSuchElementException.class,
			() -> webDriver.findElement(By.xpath("//*[text()='Connexions']/ancestor::a")),
			"Le bouton 'Connexions' ne doit pas être accessible aux simples utilisateurs.");
	}

	@Test
	public void drawerMenuAdmin() {
		webDriverGet("", SecurityUtils.ROLE_SUPERADMIN, SecurityUtils.ROLE_ETUDIANT);

		Assertions.assertNotNull(webDriver.findElement(By.xpath("//*[text()='Connexions']/ancestor::a")),
			"Le bouton 'Connexions' est introuvable.");
	}

}
