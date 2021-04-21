package fr.univlorraine.mondossierweb.ui.view.inscriptions;

import java.util.List;

import fr.univlorraine.pegase.model.chc.ObjetMaquetteExtension;
import lombok.Data;

@Data
public class ObjetMaquetteDTO {

	private ObjetMaquetteExtension objet;
	private String code;
	private String codeChemin;
	private String libelle;
	private List<ObjetMaquetteDTO> childObjects;
	private Boolean acquis;
	private Boolean affecte;
	private Boolean iaValide;
	
	/*public List<ObjetMaquetteDTO> getChildObjects(ObjetMaquetteDTO o) {
		return childObjects;
	}*/
}
