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
package fr.univlorraine.mondossierweb.test.integration.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import fr.univlorraine.mondossierweb.test.integration.AbstractIT;

public class ParametresViewIT extends AbstractIT {

	@BeforeEach
	private void beforeEach() {
		webDriverGet("");
	}

	@Test
	public void btnNotif() {
		WebElement button = webDriver.findElement(By.xpath("//vaadin-button[text()='Tester les notifications']"));
		Assertions.assertNotNull(button, "Le bouton 'Tester les notifications' est introuvable.");

		button.click();
		button.click();

		webDriverWait(1).withMessage("Après deux clics, deux notifications devraient être visibles.")
			.until(ExpectedConditions.numberOfElementsToBe(By.tagName("vaadin-notification"), 2));
	}

}
