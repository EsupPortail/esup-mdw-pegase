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

import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.Getter;


@SuppressWarnings("serial")
public class TextLabel extends VerticalLayout {

	@Getter
	private final NativeLabel label = new NativeLabel();
	@Getter
	private final NativeLabel value = new NativeLabel();


	public TextLabel() {
		super();
		this.setClassName("text-label");
		label.setClassName("label-titre");
		this.add(label);
		value.setClassName("label-valeur");
		this.add(value);
	}
	
	public void setLabel(String text) {
		label.setText(text);
	}
	
	public void setValue(String text) {
		value.setText(text);
	}

	public void updateStyle() {
		// NOTHING TO DO
	}

}
