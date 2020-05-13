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
package fr.univlorraine.mondossierweb.model.services.dataprovider;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

import fr.univlorraine.mondossierweb.model.ldap.entity.LdapPerson;
import fr.univlorraine.mondossierweb.service.LdapService;
import lombok.AllArgsConstructor;
import lombok.Data;

@org.springframework.stereotype.Service
@SuppressWarnings("serial")
public class RechercheEtudiantProviderService  implements Serializable {


	@Autowired
	protected transient LdapService ldapService;
	
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
	
}
