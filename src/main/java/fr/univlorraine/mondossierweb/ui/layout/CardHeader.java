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
package fr.univlorraine.mondossierweb.ui.layout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import fr.univlorraine.mondossierweb.utils.CSSColorUtils;

@SuppressWarnings("serial")
public class CardHeader extends HorizontalLayout {
	Div libDiv = new Div();
	
	public CardHeader() {
		libDiv.getStyle().set("font-size", "var(--lumo-font-size-xl)");
		libDiv.getStyle().set(CSSColorUtils.MARGIN_TOP, CSSColorUtils.AUTO);
		libDiv.getStyle().set(CSSColorUtils.MARGIN_BOTTOM, CSSColorUtils.AUTO);
		getStyle().set("min-height", "2.5em");
	}

	public void setText(String libelle) {
		libDiv.setText(libelle);
		add(libDiv);
	}

	public void setIcon(Icon icon) {
		icon.setColor(CSSColorUtils.SECOND_COLOR);
		icon.getStyle().set(CSSColorUtils.MARGIN_TOP, CSSColorUtils.AUTO);
		icon.getStyle().set(CSSColorUtils.MARGIN_BOTTOM, CSSColorUtils.AUTO);
		add(icon);
	}

}
