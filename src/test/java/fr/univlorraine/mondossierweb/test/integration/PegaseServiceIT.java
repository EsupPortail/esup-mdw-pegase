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
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
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
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Import({PegaseService.class, AccessTokenService.class})
@TestPropertySource("classpath:test.properties")
@TestInstance(Lifecycle.PER_CLASS)
@Slf4j
public class PegaseServiceIT {
	

	@Autowired
	private PegaseService pegaseService;
	@Autowired
	private AccessTokenService accessTokenService;	
	
	@MockBean
    private ConfigController configController;
	
	@Value("${apprenant}")
	private String codeApprenant;
	@Value("${etablissement}")
	private String codeEtab;
	@Value("${pj.photo}")
	private String codePjPhoto;
	@Value("${chemin}")
	private String chemin;
	@Value("${periode}")
	private String periode;
	@Value("${accesstoken.duration}")
	private String accessTokenDuration;
	@Value("${accesstoken.username}")
	private String accessTokenUsername;
	@Value("${accesstoken.password}")
	private String accessTokenPassword;
	@Value("${accesstoken.url}")
	private String accessTokenUrl;
	@Value("${apiIns.url}")
	private String apiInsUrl;
	@Value("${apiChc.url}")
	private String apiChcUrl;
	@Value("${apiCoc.url}")
	private String apiCocUrl;
	@Value("${apiPai.url}")
	private String apiPaiUrl;
	
	/** Initialisation. */
	@BeforeAll
	public void setUp() {
		//final Logger logger = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
	    //logger.setLevel(Level.INFO);
		initParameters();
	}

	/** Teste la méthode getCursus. */
	@Test
	void testGetCursus() {
		log.info("Test PegaseService getCursus");
		List<List<ObjetMaquetteExtension>> cursus = pegaseService.getCursus(codeApprenant, periode);
		log.debug("Cursus : {}",cursus);
		assertThat(cursus, is(notNullValue()));
		assertThat(cursus, is(not(empty())));
	}
	
	/** Teste la méthode getNotes. */
	@Test
	void testGetNotes() {
		log.info("Test PegaseService getNotes");
		List<Chemin> notes = pegaseService.getNotes(codeApprenant, periode , chemin);
		log.debug("Notes : {}",notes);
		assertThat(notes, is(notNullValue()));
		assertThat(notes, is(not(empty())));
	}
	
	/** Teste la méthode recupererDossierApprenant. */
	@Test
	void testRecupererDossierApprenant() {
		log.info("Test PegaseService recupererDossierApprenant");
		ApprenantEtInscriptions dossier = pegaseService.recupererDossierApprenant(codeApprenant);
		log.debug("Dossier : {}",dossier);
		assertThat(dossier, is(notNullValue()));
		assertThat(dossier.getApprenant(), is(notNullValue()));
		assertThat(dossier.getApprenant().getCode(), equalTo(codeApprenant));
		assertThat(dossier.getInscriptions(), is(notNullValue()));
		assertThat(dossier.getInscriptions(), is(not(empty())));
	}

	private void initParameters() {
		given(configController.getAccesTokenDuration()).willReturn(accessTokenDuration);
		given(configController.getAccesTokenUsername()).willReturn(accessTokenUsername);
		given(configController.getAccesTokenPassword()).willReturn(accessTokenPassword);
		given(configController.getAccesTokenUrl()).willReturn(accessTokenUrl);
		accessTokenService.refreshParameters();
		given(configController.getApiInsUrl()).willReturn(apiInsUrl);
		given(configController.getApiChcUrl()).willReturn(apiChcUrl);
		given(configController.getApiCocUrl()).willReturn(apiCocUrl);
		given(configController.getApiPaiUrl()).willReturn(apiPaiUrl);
		pegaseService.refreshApiParameters();
		given(configController.getEtablissement()).willReturn(codeEtab);
		given(configController.getIdPjPhoto()).willReturn(codePjPhoto);
		given(configController.getPegaseDemoApprenant()).willReturn(codeApprenant);
		pegaseService.refreshPegaseParameters();
		log.info("Test PegaseService getCursus token : {}",accessTokenService.getToken());
	}
}
