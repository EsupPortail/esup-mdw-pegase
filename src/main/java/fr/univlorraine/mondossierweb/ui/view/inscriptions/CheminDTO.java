package fr.univlorraine.mondossierweb.ui.view.inscriptions;

import java.util.List;

import fr.univlorraine.pegase.model.coc.Chemin;
import lombok.Data;

@Data
public class CheminDTO {

	private Chemin objet;
	private String code;
	private String codeChemin;
	private String libelle;
	private List<CheminDTO> childObjects;
	
	
}
