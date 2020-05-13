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

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import fr.univlorraine.mondossierweb.test.integration.AbstractIT;

public class AProposViewIT extends AbstractIT {

	private static final String ROLE_TEST1 = "ROLE_TEST1";
	private static final String ROLE_TEST2 = "ROLE_TEST2";

	@BeforeEach
	private void beforeEach() {
		webDriverGet("apropos", ROLE_TEST1, ROLE_TEST2);
	}

	@Test
	public void userForm() {
		List<WebElement> textFields = webDriver.findElements(By.tagName("vaadin-text-field"));

		WebElement usernameTF = textFields.stream()
			.filter(textField -> "Nom d'utilisateur".equals(textField.getAttribute("label")))
			.findFirst()
			.orElse(null);
		Assertions.assertNotNull(usernameTF, "Champ 'Nom d'utilisateur' introuvable");
		Assertions.assertEquals(TEST_USER, usernameTF.getAttribute("value"), "Nom d'utilisateur incorrect");

		WebElement authoritiesTF = textFields.stream()
			.filter(textField -> "Rôles".equals(textField.getAttribute("label")))
			.findFirst()
			.orElse(null);
		Assertions.assertNotNull(authoritiesTF, "Champ 'Rôles' introuvable");
		Assertions.assertEquals(ROLE_TEST1 + ", " + ROLE_TEST2, authoritiesTF.getAttribute("value"), "Rôles incorrects");
	}

}
