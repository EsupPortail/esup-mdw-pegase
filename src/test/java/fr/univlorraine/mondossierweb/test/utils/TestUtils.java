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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vaadin.flow.component.textfield.TextField;

import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import fr.univlorraine.mondossierweb.utils.CmpUtils;
import lombok.extern.slf4j.Slf4j;

/** Tests Utils.
*
* @author Charlie Dubois */
@ExtendWith(SpringExtension.class)
@Slf4j
public class TestUtils {
	
	/** Teste de la class Utils. */
	@Test
	void testUtils() {
		TextField tf = new TextField();
		CmpUtils.formatTextField(tf);
		assertThat(tf.getStyle().get(CSSColorUtils.MARGIN), is(equalTo("0em")));
	}

}
