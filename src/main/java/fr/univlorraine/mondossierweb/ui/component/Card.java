package fr.univlorraine.mondossierweb.ui.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import fr.univlorraine.mondossierweb.ui.layout.TextHeader;
import lombok.Getter;


@SuppressWarnings("serial")
public class Card extends VerticalLayout {

	@Getter
	private final TextHeader titre = new TextHeader();
	
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
	
	public Card(String libelle, boolean hasAltVisibleComponent) {
		super();
		titre.setText(libelle);
		if(!hasAltVisibleComponent) {
			useAlt = false;
			this.add(titre);
		}else {
			useAlt = true;
			titreLayout.addAndExpand(titre);
			titreLayout.add(altButton);
			this.add(titreLayout);
			
			this.add(alt);
			alt.setVisible(false);
			altButton.addClickListener(e->{
				changeAlt();
			});
			titre.addClickListener(e->{
				changeAlt();
			});
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
	}
	
	public void hideAlt() {
		alt.setVisible(false);
		altButton.setIcon(VaadinIcon.ANGLE_DOWN.create());
	}
	

	public void addAlt(Component... components) {
			alt.add(components);
	}
	 
	public void updateStyle(boolean isMobile, boolean princ) {
			this.getStyle().set("margin", "1em");
			this.getStyle().set("border-radius", "4px");
			this.getStyle().set("border", "0.1em solid");
			this.getStyle().set("border-color", "var(--lumo-contrast-5pct)");
			this.setHeight("fit-content");
			this.setWidth(!isMobile && !princ ? "46%" :"100%");
	}

}
