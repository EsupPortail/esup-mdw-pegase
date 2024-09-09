package fr.univlorraine.mondossierweb.ui.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import fr.univlorraine.mondossierweb.utils.CSSColorUtils;

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
        clearButton.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.SECOND_TXT_COLOR);

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
        this.getStyle().set(CSSColorUtils.PADDING_LEFT, "0.5em");
        this.getStyle().set(CSSColorUtils.PADDING_RIGHT, "0.5em");
        this.getStyle().set(CSSColorUtils.BORDER_WIDTH, CSSColorUtils.THIN);
        this.getStyle().set(CSSColorUtils.COLOR, CSSColorUtils.SECOND_TXT_COLOR);
        this.getStyle().set(CSSColorUtils.BORDER_RADIUS, "0.4em");
        this.getStyle().set(CSSColorUtils.BORDER_STYLE, CSSColorUtils.DOTTED);
        this.getStyle().set(CSSColorUtils.BACKGROUND_COLOR, CSSColorUtils.WHITE);
    }

    public void setReadOnly(boolean readOnly) {
        clearButton.setVisible(!readOnly);
    }

    public String getValue(){
        return value;
    }
}
