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
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import fr.univlorraine.mondossierweb.ui.layout.CardHeader;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;
import lombok.Getter;


@SuppressWarnings("serial")
public class Card extends VerticalLayout {

	@Getter
	private final CardHeader titre = new CardHeader();
	
	@Getter
	private boolean useAlt = false;
	
	@Getter
	private final VerticalLayout alt = new VerticalLayout();
	
	@Getter
	private final HorizontalLayout titreLayout = new HorizontalLayout();
	
	@Getter
	private final Button altButton = new Button("", VaadinIcon.ANGLE_DOWN.create());

	public Card() {
		super();
	}
	
	public Card(Icon titleIcon, String libelle, boolean hasAltVisibleComponent) {
		super();
		addClassName("flip");
		if(titleIcon != null) {
			titre.setIcon(titleIcon);
		}
		titre.setText(libelle);
		titre.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.MAIN_HEADER_COLOR);
		titre.setClassName("card-title-bordered");
		if(!hasAltVisibleComponent) {
			useAlt = false;
			this.add(titre);
		}else {
			useAlt = true;
			titreLayout.addAndExpand(titre);
			altButton.getStyle().set(CSSColorUtils.COLOR,CSSColorUtils.MAIN_HEADER_COLOR);
			titreLayout.add(altButton);
			this.add(titreLayout);
			
			this.add(alt);
			alt.getStyle().set(CSSColorUtils.PADDING, "0");
			alt.setVisible(false);
			altButton.addClickListener(e -> changeAlt());
			titre.addClickListener(e -> changeAlt());
			titre.getStyle().set("cursor", "pointer");
		}
	}
	
	public void changeAlt() {
		if(alt.isVisible()) {
			hideAlt();
		} else {
			displayAlt();
		}
	}
	
	public void displayAlt() {
		alt.setVisible(true);
		altButton.setIcon(VaadinIcon.ANGLE_UP.create());
		titre.setClassName("card-title-bordered");
	}
	
	public void hideAlt() {
		alt.setVisible(false);
		altButton.setIcon(VaadinIcon.ANGLE_DOWN.create());
		titre.setClassName("card-title-bordered-closed");
	}
	

	public void addAlt(Component... components) {
			alt.add(components);
	}
	
	public void updateStyle() {
			this.getStyle().set("border", "0.1em solid");
 
			this.getStyle().set("border-color", "lightgray");
			this.getStyle().set(CSSColorUtils.MARGIN, "0.5em auto 0.5em auto");
			this.getStyle().set(CSSColorUtils.BORDER_RADIUS, "0.5em");
			
			this.getStyle().set(CSSColorUtils.COLOR, "#343a40");
			
			this.getStyle().set(CSSColorUtils.PADDING, "1em 2em");
			
			this.setHeight("fit-content");
			
			this.setWidth("100%");
			this.setMaxWidth("50em");
			
	}

}
