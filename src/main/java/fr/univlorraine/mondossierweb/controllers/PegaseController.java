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
package fr.univlorraine.mondossierweb.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.univlorraine.mondossierweb.services.PegaseService;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.CheminDTO;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.ObjetMaquetteDTO;
import fr.univlorraine.mondossierweb.utils.Utils;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PegaseController {


	@Autowired
	private PegaseService pegaseService;



	/**
	 * 
	 * @param codeApprenant
	 * @param codeChemin
	 * @param codePeriode
	 * @return le cursus de l'apprenant pour le chemin et la période en paramètre
	 */
	public List<ObjetMaquetteDTO> getCursus(String codeApprenant, String codeFormation, String codeRacinePeda, String codePeriode) {
		List<ObjetMaquetteDTO> listObj;
		log.info("Récupération de la liste cursus dans Pégase");
		// Correction du chemin pour en replaçant le séparateur
		//String codeCheminChc = formatChemin(codeChemin);
		// Récupération du cursus
		listObj = Utils.convertObjetMaquetteListToDTO(pegaseService.getCursus(codeApprenant, codePeriode), codeFormation, codeRacinePeda, codePeriode, pegaseService.getEtablissement());
		// suppression de la racine
		if(listObj != null && !listObj.isEmpty()) {
			listObj = listObj.get(0).getChildObjects();
		}
		return listObj;
	}


	/**
	 * 
	 * @param codeApprenant
	 * @param codeChemin
	 * @param codePeriode
	 * @param avecControle
	 * @return les notes de l'étudiant pour le codeApprenant, le chemin, la période en paramètre
	 */
	public List<CheminDTO> getNotes(String codeApprenant, String codeChemin, String codePeriode, boolean avecControle) {

		log.info("Récupération de la liste notes dans Pégase");
		// Correction du chemin pour en replaçant le séparateur
		String codeCheminChc = formatChemin(codeChemin);
		// Récupération des notes
		return Utils.convertCheminToDTO(pegaseService.getNotes(codeApprenant, codePeriode,codeCheminChc), codeCheminChc, avecControle);
	}

	/**
	 * 
	 * @param codeChemin
	 * @return chemin formaté pour l'API Pégase
	 */
	private String formatChemin(String codeChemin) {
		return codeChemin.replace("→", ">");
	}

}
