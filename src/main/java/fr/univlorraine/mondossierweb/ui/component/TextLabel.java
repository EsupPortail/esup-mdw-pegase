package fr.univlorraine.mondossierweb.ui.component;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.Getter;


@SuppressWarnings("serial")
public class TextLabel extends VerticalLayout {

	@Getter
	private final Label label = new Label();
	@Getter
	private final Label value = new Label();


	public TextLabel() {
		super();
		//addClassName("flip");
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
			/*this.getStyle().set("border", "0.1em solid");
			this.getStyle().set("border-color", "lightgray");
			this.getStyle().set("margin", "0.5em auto 0.5em auto");
			this.getStyle().set("border-radius", "0.5em");
			this.getStyle().set("color", "#343a40");
			this.getStyle().set("padding", "1em 2em");
			this.setHeight("fit-content");
			this.setWidth("100%");
			this.setMaxWidth("50em");*/
	}

}
