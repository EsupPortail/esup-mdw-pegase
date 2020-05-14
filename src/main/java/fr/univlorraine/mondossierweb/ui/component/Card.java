package fr.univlorraine.mondossierweb.ui.component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import fr.univlorraine.mondossierweb.ui.layout.TextHeader;

@Tag("mdw-card")
@SuppressWarnings("serial")
public class Card extends VerticalLayout {

	private final TextHeader titre = new TextHeader();

	public Card() {
		super();
	}

	public Card(String titre) {
		super();
		this.titre.setText(titre);
		this.add(titre);
		
	}

}
