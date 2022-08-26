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
package fr.univlorraine.mondossierweb.utils;



import org.springframework.util.StringUtils;

import com.vaadin.flow.component.textfield.TextField;

import fr.univlorraine.mondossierweb.ui.component.TextLabel;

/**
 * Outils pour la gestion des préférences de l'application
 * @author Charlie Dubois
 */
public final class CmpUtils {
	
	private CmpUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static void formatTextField(TextField tf) {
		tf.getStyle().set(CSSColorUtils.MARGIN, "0em");
	}
	
	public static void formatTextLabel(TextLabel tl) {
		tl.getStyle().set(CSSColorUtils.MARGIN, "0em");
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
	
	public static void setModerateTextLabel(TextLabel tl) {
		formatTextLabel(tl);
	}
	
	public static void setShortTextField(TextField tf) {
		formatTextField(tf);
		tf.setWidthFull();
		tf.setMaxWidth("15em");
	}
	
	public static void valueAndVisibleIfNotNull(TextField cmp, String value) {
		if(StringUtils.hasText(value)) {
			cmp.setValue(value);
			cmp.setVisible(true);
		}	else {
			cmp.setVisible(false);
		}
	}

	public static void valueAndVisibleIfNotNull(TextLabel label, String value) {
		if(StringUtils.hasText(value)) {
			label.setValue(value);
			label.setVisible(true);
		}	else {
			label.setVisible(false);
		}
		
	}


}
