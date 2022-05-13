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
package fr.univlorraine.mondossierweb.test.model.services;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.univlorraine.mondossierweb.controllers.PegaseController;
import fr.univlorraine.mondossierweb.service.PegaseService;
import fr.univlorraine.mondossierweb.ui.view.inscriptions.ObjetMaquetteDTO;
import fr.univlorraine.pegase.model.chc.ObjetMaquetteExtension;
import lombok.extern.slf4j.Slf4j;

/** Tests du controller mainController.
*
* @author Charlie Dubois */
@ExtendWith(SpringExtension.class)
@Import({PegaseController.class})
@Slf4j
public class MonDossierWebServiceTest {

	@MockBean
	private PegaseService pegaseService;

	
	@Resource
	private PegaseController pegaseController;

	private static List<ObjetMaquetteDTO> cursus1;
	
	private static List<List<ObjetMaquetteExtension>> maquette1;
	
	/** Initialisation. */
	@BeforeAll
	public static void setUp() {
		cursus1 = new LinkedList<ObjetMaquetteDTO> ();
		List<ObjetMaquetteExtension> maquette0 = new LinkedList<ObjetMaquetteExtension> ();
		maquette1 = Arrays.asList(maquette0);
		
	}

	
	/** Teste la méthode getCursus. */
	@Test
	public void testGetCursus() {
		log.info("service {}",pegaseService);
		given(pegaseService.getCursus(anyString(), anyString())).willReturn(maquette1);
		assertThat(pegaseController.getCursus("000000001","F-ING-HYD→F-ING-HYD-A4","PER-2020"), is(cursus1));
	}

}
