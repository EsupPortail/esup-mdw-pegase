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
package fr.univlorraine.mondossierweb.model.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateur;
import fr.univlorraine.mondossierweb.model.app.entity.PreferencesUtilisateurPK;

public interface PreferencesUtilisateurRepository extends JpaRepository<PreferencesUtilisateur, PreferencesUtilisateurPK> {
	

}
