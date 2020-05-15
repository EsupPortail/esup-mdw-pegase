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
package fr.univlorraine.mondossierweb.utils;

import com.vaadin.flow.component.textfield.TextField;

/**
 * Outils pour la gestion des préférences de l'application
 * @author Charlie Dubois
 */
public final class CmpUtils {

	public static void formatTextField(TextField tf) {
		tf.getStyle().set("margin", "0em");
	}
	
	public static void setLongTextField(TextField tf) {
		formatTextField(tf);
		tf.setWidthFull();
		tf.setMaxWidth("25em");
	}
	
	public static void setModerateTextField(TextField tf) {
		formatTextField(tf);
		tf.setWidthFull();
		tf.setMaxWidth("20em");
	}

}
