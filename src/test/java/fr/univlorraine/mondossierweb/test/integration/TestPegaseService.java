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
package fr.univlorraine.mondossierweb.test.integration;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.univlorraine.mondossierweb.service.AccessTokenService;
import fr.univlorraine.mondossierweb.service.PegaseService;
import fr.univlorraine.pegase.model.chc.ObjetMaquetteExtension;
import fr.univlorraine.pegase.model.coc.Chemin;
import fr.univlorraine.pegase.model.insgestion.ApprenantEtInscriptions;
import lombok.extern.slf4j.Slf4j;

/** Tests du PegaseService.
*
* @author Charlie Dubois */
@ExtendWith(SpringExtension.class)
@Import({PegaseService.class, AccessTokenService.class})
@TestPropertySource("classpath:application.properties")
@Slf4j
public class TestPegaseService {
	
	private static final String CODE_APPRENANT_TEST = "000000035";
	
	private static final String CHEMIN_NOTES_FORMATE = "F-ING-HYD>F-ING-HYD-A4";

	private static final String PERIODE_CURSUS_TEST = "PER-2020";
	
	private static final String PERIODE_NOTES_TEST = "PER-2020";


	@Resource
	private PegaseService pegaseService;
	
	@Resource
	private AccessTokenService accessTokenService;
	

	
	/** Initialisation. */
	@BeforeAll
	public static void setUp() {
		
	}

	
	/** Teste la méthode getCursus. */
	@Test
	public void testGetCursus() {
		log.debug("Test PegaseService getCursus");
		List<List<ObjetMaquetteExtension>> cursus = pegaseService.getCursus(CODE_APPRENANT_TEST, PERIODE_CURSUS_TEST);
		log.debug("Cursus : {}",cursus);
		assertThat(cursus, is(notNullValue()));
		assertThat(cursus, is(not(empty())));
	}
	
	/** Teste la méthode getNotes. */
	@Test
	public void testGetNotes() {
		log.debug("Test PegaseService getNotes");
		List<Chemin> notes = pegaseService.getNotes(CODE_APPRENANT_TEST,PERIODE_NOTES_TEST , CHEMIN_NOTES_FORMATE);
		log.debug("Notes : {}",notes);
		assertThat(notes, is(notNullValue()));
		assertThat(notes, is(not(empty())));
	}
	
	/** Teste la méthode recupererDossierApprenant. */
	@Test
	public void testRecupererDossierApprenant() {
		log.debug("Test PegaseService recupererDossierApprenant");
		ApprenantEtInscriptions dossier = pegaseService.recupererDossierApprenant(CODE_APPRENANT_TEST);
		log.debug("Dossier : {}",dossier);
		assertThat(dossier, is(notNullValue()));
		assertThat(dossier.getApprenant(), is(notNullValue()));
		assertThat(dossier.getApprenant().getCode(), equalTo(CODE_APPRENANT_TEST));
		assertThat(dossier.getInscriptions(), is(notNullValue()));
		assertThat(dossier.getInscriptions(), is(not(empty())));
	}

	
}
