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
package fr.univlorraine.mondossierweb.test.integration.view;

import java.net.URI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import fr.univlorraine.mondossierweb.test.integration.AbstractIT;

public class RouteNotFoundErrorViewIT extends AbstractIT {

	@BeforeEach
	private void beforeEach() {
		webDriverGet("unavailable");
	}

	@Test
	public void routeNotFound() {
		String currentRoute = URI.create(webDriver.getCurrentUrl()).getPath();
		Assertions.assertEquals("/", currentRoute, "Le navigateur devrait être redirigé vers l'accueil.");

		webDriverWait(1).withMessage("Une notification devrait être visible.")
			.until(ExpectedConditions.presenceOfElementLocated(By.tagName("vaadin-notification")));
	}

}
