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


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univlorraine.mondossierweb.controllers.PegaseController;
import fr.univlorraine.mondossierweb.services.PegaseService;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.CheminDTO;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.ObjetMaquetteDTO;
import fr.univlorraine.pegase.model.chc.ObjetMaquetteExtension;
import fr.univlorraine.pegase.model.coc.Chemin;
import lombok.extern.slf4j.Slf4j;

/** Tests du PegaseController.
*
* @author Charlie Dubois */
@ExtendWith(SpringExtension.class)
@Import({PegaseController.class})
@Slf4j
public class TestPegaseController {
	
	private static final String CODE_APPRENANT_TEST = "000000001";
	
	private static final String CHEMIN_CURSUS_NON_FORMATE = "F-ING-HYD→F-ING-HYD-A4";
	
	private static final String CHEMIN_NOTES_NON_FORMATE = "F-ING-HYD→F-ING-HYD-A4";
	
	private static final String PERIODE_CURSUS_TEST = "PER-2020";

	private static final String PERIODE_NOTES_TEST = "PER-2020";

	private static final String FICHIER_CURSUS_JSON = "src/test/resources/cursus.json";
	
	private static final String FICHIER_NOTES_JSON = "src/test/resources/notes.json";


	@MockBean
	private PegaseService pegaseService;

	@Resource
	private PegaseController pegaseController;
	
	private static List<List<ObjetMaquetteExtension>> maquette1;
	
	private static List<Chemin> notes1;
	
	/** Initialisation. */
	@BeforeAll
	public static void setUp() {
		
		// Maquette qui remplace le retour de l'API cursus Pégase
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		try {
			maquette1 = mapper.readValue(new File(FICHIER_CURSUS_JSON), new TypeReference<List<List<ObjetMaquetteExtension>>>() {});
		} catch (IOException e) {
			log.error("Erreur à la lecture de {} pour MonDossierWebServiceTest",FICHIER_CURSUS_JSON,e);
		}

		
		// Notes et résultats qui remplace le retour de l'API notes Pégase
		try {
			notes1 = mapper.readValue(new File(FICHIER_NOTES_JSON), new TypeReference<List<Chemin>>() {});
		} catch (IOException e) {
			log.error("Erreur à la lecture de {} pour MonDossierWebServiceTest",FICHIER_NOTES_JSON,e);
		}
		
	}

	
	/** Teste la méthode getCursus. */
	@Test
	void testGetCursus() {
		log.debug("Test PegaseController getCursus");
		given(pegaseService.getCursus(anyString(), anyString())).willReturn(maquette1);
		List<ObjetMaquetteDTO> cursus = pegaseController.getCursus(CODE_APPRENANT_TEST,CHEMIN_CURSUS_NON_FORMATE,PERIODE_CURSUS_TEST);		
		assertThat(cursus, is(notNullValue()));
		assertThat(cursus, is(not(empty())));
		List<ObjetMaquetteDTO> cursusFromMap = pegaseController.getCursus(CODE_APPRENANT_TEST,CHEMIN_CURSUS_NON_FORMATE,PERIODE_CURSUS_TEST);		
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
