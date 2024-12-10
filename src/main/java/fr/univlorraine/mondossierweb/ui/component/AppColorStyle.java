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
package fr.univlorraine.mondossierweb.ui.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CssImport(value = "./styles/app-color.css")
@Tag("style")
@NoArgsConstructor
@Slf4j
@SuppressWarnings("serial")
public class AppColorStyle extends Component {

	private static final String STYLE_TEMPLATE = "html {--main-color: %s;--header-card-separator-color: %s;--second-color : %s;--btn-color: %s;--txt-color: %s;background-color:%s;}";

	@Getter
	private String color;

	public AppColorStyle(final String mainColor, final String secondColor, final String headerCardSepColor, 
		final String txtColor, final String btnColor, final String backgroundColor) {
		setColor( mainColor,  secondColor, headerCardSepColor, btnColor,  txtColor, backgroundColor);
	}

	public void setColor(final String mainColor, final String secondColor, final String headerCardSepColor, 
		final String txtColor, final String btnColor, final String backgroundColor) {
		getElement().setText(String.format(STYLE_TEMPLATE, mainColor,headerCardSepColor,secondColor,btnColor,txtColor,backgroundColor));
	}

}
