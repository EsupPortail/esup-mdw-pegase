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
package fr.univlorraine.mondossierweb.test.utils;

import com.vaadin.flow.component.textfield.TextField;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import fr.univlorraine.mondossierweb.utils.PrefUtils;
import fr.univlorraine.mondossierweb.utils.Utils;
import fr.univlorraine.pegase.insext.model.CibleInscription;
import fr.univlorraine.pegase.insext.model.Formation;
import fr.univlorraine.pegase.insext.model.ObjetFormationOuGroupement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests Utils.
*
* @author Charlie Dubois */
@ExtendWith(SpringExtension.class)
@Slf4j
class TestUtils {
	
	/** Test de la class CmpUtils. */
	@Test
	void testFormatTextField() {
		TextField tf = new TextField();
		CmpUtils.formatTextField(tf);
		assertThat(tf.getStyle().get(CSSColorUtils.MARGIN), is(equalTo("0em")));
	}
	
	@Test
	void testShortTextField() {
		TextField tf = new TextField();
		CmpUtils.setShortTextField(tf);
		assertThat(tf.getStyle().get(CSSColorUtils.MARGIN), is(equalTo("0em")));
		assertEquals("100%",tf.getWidth());
		assertEquals("15em",tf.getMaxWidth());
	}
	
	/** Test de la class PrefUtils */
	@Test
	void testgetBooleanValue() {
		assertEquals(true,PrefUtils.getBooleanValue("true"));
		assertEquals(false,PrefUtils.getBooleanValue("false"));
	}
	
	/** Test de la class Utils. */
	@Test
	void testDisplayNote() {
		assertEquals("15/20", Utils.displayNote(new BigDecimal(15),20, true));
		assertEquals("10/20", Utils.displayNote(new BigDecimal(10),20, true));
		assertEquals("12/20", Utils.displayNote(new BigDecimal("12.0"),20, true));
		assertEquals("5.6/20", Utils.displayNote(new BigDecimal("5.6"),20, true));
	}
	
	@Test
	void testGetCodeChemin() {
		CibleInscription cible = new CibleInscription();
		Formation f = new Formation();
		f.setCode("FORM");
		cible.setFormation(f);
		assertEquals("FORM", Utils.getCodeChemin(cible));
		List<ObjetFormationOuGroupement> chemin = new LinkedList<>();
		ObjetFormationOuGroupement obj1 = new ObjetFormationOuGroupement();
		obj1.setCode("obj1");
		chemin.add(obj1);
		ObjetFormationOuGroupement obj2 = new ObjetFormationOuGroupement();
		obj2.setCode("obj2");
		chemin.add(obj2);
		cible.setChemin(chemin);
		assertEquals("FORM>obj1>obj2", Utils.getCodeChemin(cible));
	}
	
	@Test
	void testGetFileName() {
		assertEquals("fichier", Utils.getFileName("fichier.txt"));
		assertEquals(null, Utils.getFileName("fichier"));
	}
	
	@Test
	void testGetFileExtension() {
		assertEquals(".txt", Utils.getFileExtension("fichier.txt"));
		assertEquals(null, Utils.getFileExtension("fichier"));
	}
	
	@Test
	void testFormatDateToDisplay() {
		LocalDate localDate = LocalDate.of(2015, 3, 25);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date d = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		assertEquals("25/03/2015", Utils.formatDateToDisplay(d));
		assertEquals("", Utils.formatDateToDisplay(null));
	}
	
	@Test
	void testEstPassee() {
		assertEquals(true, Utils.estPassee("1990-01-01"));
		assertEquals(false, Utils.estPassee("2999-12-31"));
	}

	
	@Test
	void testFormatStringDateToDisplay() {
		assertEquals("25/03/2022", Utils.formatStringDateToDisplay("2022-03-25"));
		assertEquals("25/03/2022", Utils.formatStringDateToDisplay("25/03/2022"));
		assertEquals("", Utils.formatStringDateToDisplay(""));
	}
}
