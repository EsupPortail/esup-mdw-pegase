/**
 *
 *  ESUP-Portail MONDOSSIERWEB - Copyright (c) 2020 ESUP-Portail consortium
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
package fr.univlorraine.mondossierweb.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

import fr.univlorraine.mondossierweb.model.app.entity.HistoriqueRecherche;
import fr.univlorraine.mondossierweb.model.app.entity.HistoriqueRecherchePK;
import fr.univlorraine.mondossierweb.model.app.repository.HistoriqueRechercheRepository;
import fr.univlorraine.mondossierweb.model.ldap.entity.LdapPerson;
import lombok.AllArgsConstructor;
import lombok.Data;

@org.springframework.stereotype.Service
@SuppressWarnings("serial")
public class RechercheEtudiantService  implements Serializable {


	@Autowired
	protected transient LdapService ldapService;
	@Autowired
	private transient SecurityService securityService;
	
	@Autowired
	private transient HistoriqueRechercheRepository historiqueRechercheRepository;
	
	@Value("${recherche.historique.actif}")
	private transient boolean historique;	
	@Value("${recherche.historique.nb}")
	private transient int nbHistorique;	
	
	@Data
	@AllArgsConstructor
	public static class RechercheEtudiantFilter implements Serializable {
		private String name;
	}
	
	/**
	 * @return le dataProvider
	 */
	public ListDataProvider<LdapPerson> createLdapPersonDataProvider(RechercheEtudiantFilter filter) {
	/*return DataProvider.fromFilteringCallbacks(query -> {
			String filter = query.getFilter().orElse("");
			return ldapService.searchStudentFromString(filter).stream();
		}, query  -> {
			String filter = query.getFilter().orElse(null);
		    return 100;
		});*/
		return DataProvider.fromStream(ldapService.searchStudentFromString(filter.name).stream());		
	}
	
	public void accesDossier(LdapPerson etudiant) {
		// Si l'historique des acces est activé
		if(historique) {
			String uid = securityService.getUsername().orElse(null);
			// Récupération de la liste des acces depuis la recherche
			LinkedList<HistoriqueRecherche> lhr = getHistoriqueRecherche(uid); 
			
			// Si ce n'est pas le même dossier que le dernier éléments accédé
			if(lhr.isEmpty() || !lhr.getLast().getCodeApprenant().equals(etudiant.getCodeApprenant())) {
				// Sauvegarder dans l'historique si optiona activée
				HistoriqueRecherche hr = new HistoriqueRecherche();
				HistoriqueRecherchePK hrpk =new HistoriqueRecherchePK();
				hrpk.setDateCreate(LocalDateTime.now());
				hrpk.setUsername(uid);
				hr.setCodeApprenant(etudiant.getCodeApprenant());
				hr.setDisplayName(etudiant.getDisplayName());
				hr.setMail(etudiant.getMail());
				hr.setId(hrpk);
				hr = historiqueRechercheRepository.save(hr);
				lhr.addLast(hr);
				
				// Suppression des historiques supérieurs à nbHistorique

				while (lhr.size() > nbHistorique) {
					historiqueRechercheRepository.deleteById(lhr.getFirst().getId());
					lhr.removeFirst();
				}
			}

		}
		UI.getCurrent().navigate("etatcivil/" + etudiant.getCodeApprenant());
	}
	
	private LinkedList<HistoriqueRecherche> getHistoriqueRecherche(String uid) {
		List<HistoriqueRecherche> lhr = historiqueRechercheRepository.findByIdUsernameOrderByIdDateCreateAsc(uid);
		LinkedList<HistoriqueRecherche> llhr = new LinkedList<HistoriqueRecherche> ();
		llhr.addAll(lhr);
		return llhr;
	}
	
}
