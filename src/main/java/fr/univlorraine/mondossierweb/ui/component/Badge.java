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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import fr.univlorraine.mondossierweb.utils.CssUtils;

public class Badge extends Span {

    private  Button clearButton;
    private Span valueSpan;
    private String value;

    public Badge(String value, Boolean closable) {
        super();
        clearButton = new Button(VaadinIcon.CLOSE_SMALL.create());
        clearButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST,
                ButtonVariant.LUMO_TERTIARY_INLINE);
        clearButton.getStyle().set("margin-inline-start",
                "var(--lumo-space-xs)");
        // Accessible button name
        clearButton.getElement().setAttribute("aria-label",
                "Clear filter: " + value);
        // Tooltip
        clearButton.getElement().setAttribute("title",
                "Clear filter: " + value);

        // Visible si closable
        clearButton.setVisible(closable);
        clearButton.getStyle().set(CssUtils.COLOR, CssUtils.SECOND_TXT_COLOR);

        valueSpan = new Span(value);
        this.value = value;

        this.add(valueSpan);
        this.add(clearButton);
        this.getElement().getThemeList().add("badge contrast pill");

        // Add handler for removing the badge
        clearButton.addClickListener(event -> {
            this.getElement().removeFromParent();
        });


        updateStyle();
    }

    private void updateStyle() {
        this.getStyle().set(CssUtils.PADDING_LEFT, "0.5em");
        this.getStyle().set(CssUtils.PADDING_RIGHT, "0.5em");
        this.getStyle().set(CssUtils.BORDER_WIDTH, CssUtils.THIN);
        this.getStyle().set(CssUtils.COLOR, CssUtils.SECOND_TXT_COLOR);
        this.getStyle().set(CssUtils.BORDER_RADIUS, "0.4em");
        this.getStyle().set(CssUtils.BORDER_STYLE, CssUtils.DOTTED);
        this.getStyle().set(CssUtils.BACKGROUND_COLOR, CssUtils.WHITE);
    }

    public void setReadOnly(boolean readOnly) {
        clearButton.setVisible(!readOnly);
    }

    public String getValue(){
        return value;
    }
}
