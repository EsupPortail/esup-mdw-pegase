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
package fr.univlorraine.mondossierweb.test.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import fr.univlorraine.mondossierweb.controllers.PegaseController;
import fr.univlorraine.mondossierweb.services.PegaseService;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.CheminDTO;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.ObjetMaquetteDTO;
import fr.univlorraine.pegase.chc.model.CursusDCA;
import fr.univlorraine.pegase.coc.model.Chemin;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/** Tests du PegaseController.
*
* @author Charlie Dubois */
@ExtendWith(SpringExtension.class)
@Import({PegaseController.class})
@Slf4j
public class TestPegaseController {
	
	
	private static final String CODE_ETAB_TEST = "ETAB00";
	
	private static final String CODE_APPRENANT_TEST = "000000001";
	
	private static final String FORMATION_CURSUS = "F-ING-HYD";
	
	private static final String RACINE_CURSUS = "F-ING-HYD-A4";
	
	private static final String CHEMIN_NOTES_NON_FORMATE = "F-ING-HYD→F-ING-HYD-A4";
	
	private static final String PERIODE_CURSUS_TEST = "PER-2020";

	private static final String PERIODE_NOTES_TEST = "PER-2020";

	private static final String FICHIER_CURSUS_JSON = "src/test/resources/cursus.json";
	
	private static final String FICHIER_NOTES_JSON = "src/test/resources/notes.json";

	private static final Type LIST_CHEMIN_TYPE = new TypeToken<List<Chemin>>() {
	}.getType();
	
	private static final Type LIST_CURSUS_DCA_TYPE = new TypeToken<List<CursusDCA>>() {
	}.getType();
	private static List<CursusDCA> maquette1;
	private static List<Chemin> notes1;
	@MockBean
	private PegaseService pegaseService;
	@Resource
	private PegaseController pegaseController;
	
	/** Initialisation. */
	@BeforeAll
	public static void setUp() {
		
		// Maquette qui remplace le retour de l'API cursus Pégase
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		try {
			JsonReader reader = new JsonReader(new FileReader(FICHIER_CURSUS_JSON));
			maquette1 = new Gson().fromJson(reader, LIST_CURSUS_DCA_TYPE);
		} catch (IOException e) {
			log.error("Erreur à la lecture de {} pour MonDossierWebServiceTest",FICHIER_CURSUS_JSON,e);
		}

		
		// Notes et résultats qui remplace le retour de l'API notes Pégase
		try {
			JsonReader reader = new JsonReader(new FileReader(FICHIER_NOTES_JSON));
			notes1 = new Gson().fromJson(reader, LIST_CHEMIN_TYPE);
		} catch (IOException e) {
			log.error("Erreur à la lecture de {} pour MonDossierWebServiceTest",FICHIER_NOTES_JSON,e);
		}
	}

	
	/** Teste la méthode getCursus. */
	@Test
	void testGetCursus() {
		log.debug("Test PegaseController getCursus");
		given(pegaseService.getCursus(anyString())).willReturn(maquette1);
		given(pegaseService.getEtablissement()).willReturn(CODE_ETAB_TEST);
		List<ObjetMaquetteDTO> cursus = pegaseController.getCursus(CODE_APPRENANT_TEST,FORMATION_CURSUS, RACINE_CURSUS, PERIODE_CURSUS_TEST);		
		assertThat(cursus, is(notNullValue()));
		assertThat(cursus, is(not(empty())));
		List<ObjetMaquetteDTO> cursusFromMap = pegaseController.getCursus(CODE_APPRENANT_TEST,FORMATION_CURSUS,RACINE_CURSUS, PERIODE_CURSUS_TEST);		
		assertThat(cursusFromMap, is(notNullValue()));
		assertThat(cursusFromMap, is(not(empty())));
		assertEquals(cursusFromMap.size(), cursus.size());
	}
	
	/** Teste la méthode getCursus. */
	@Test
	void testGetNotes() {
		log.debug("Test PegaseController getNotes");
		given(pegaseService.getNotes(anyString(), anyString() , anyString())).willReturn(notes1);
		List<CheminDTO> notes = pegaseController.getNotes(CODE_APPRENANT_TEST,CHEMIN_NOTES_NON_FORMATE,PERIODE_NOTES_TEST, true);
		assertThat(notes, is(notNullValue()));
		assertThat(notes, is(not(empty())));
		List<CheminDTO> notesFromMap = pegaseController.getNotes(CODE_APPRENANT_TEST,CHEMIN_NOTES_NON_FORMATE,PERIODE_NOTES_TEST, true);
		assertThat(notesFromMap, is(notNullValue()));
		assertThat(notesFromMap, is(not(empty())));
		assertEquals(notesFromMap.size(), notes.size());
	}

	
}
