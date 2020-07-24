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

import org.flywaydb.core.internal.util.StringUtils;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import fr.univlorraine.mondossierweb.ui.view.parametres.ParametresView;

/**
 * Outils pour la gestion des préférences de l'application
 * @author Charlie Dubois
 */
public final class CmpUtils {

	public static void formatTextField(TextField tf) {
		tf.getStyle().set("margin", "0em");
		//tf.addClassName("nice-textfield");
		//tf.addThemeName("nice-label");
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

	/*public static void setBackgroundLogo(VerticalLayout view) {
		view.getStyle().set("background-image", "url(./images/logo-back.png)");
		view.getStyle().set("background-repeat", "no-repeat");
		view.getStyle().set("background-position", "bottom right");
		view.getStyle().set("background-size", "20%");

		
	}*/

}
