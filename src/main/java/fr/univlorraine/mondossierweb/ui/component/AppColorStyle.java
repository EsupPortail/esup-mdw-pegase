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
package fr.univlorraine.mondossierweb.ui.component;

import com.helger.css.decl.CSSRGB;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;

import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

@CssImport(value = "./styles/app-color.css")
@Tag("style")
@NoArgsConstructor
@SuppressWarnings("serial")
public class AppColorStyle extends Component {

	private static final String STYLE_TEMPLATE = "html {--app-color-rgb: %s,%s,%s;}";

	@Getter
	private String color;

	public AppColorStyle(final String color) {
		setColor(color);
	}

	public void setColor(final String color) {
		this.color = color;
		CSSRGB rgbColor = CSSColorUtils.getRGBColor(color);
		getElement().setText(String.format(STYLE_TEMPLATE, rgbColor.getRed(), rgbColor.getGreen(), rgbColor.getBlue()));
	}

}
