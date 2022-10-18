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
package fr.univlorraine.mondossierweb.services;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@SuppressWarnings("serial")
@Slf4j
public class ExportService implements Serializable {


	@Autowired
	private transient PegaseService pegaseService;

	
	public ByteArrayInputStream getPhoto(String codeApprenant, String codeFormation) {
		File file = pegaseService.recuperePhoto(codeApprenant, codeFormation);

		return getStream(file,codeApprenant, codeFormation, "Photo");
	}
	
	
	/**
	 * Génération d'un pdf
	 * @param codeApprenant
	 * @param codeFormation
	 * @return
	 */
	public ByteArrayInputStream  getCertificat(String codeApprenant, String codeFormation) {
		
		File file = pegaseService.certificatDeScolarite(codeApprenant, codeFormation);

		return getStream(file,codeApprenant, codeFormation, "certificat de scolarité");

	}
	
	/**
	 * Génération d'un pdf
	 * @param codeApprenant
	 * @param codeFormation
	 * @return
	 */
	public ByteArrayInputStream  getAttestation(String codeApprenant, String codePeriode) {
		
		File file = pegaseService.attestationDePaiement(codeApprenant, codePeriode);

		return getStream(file,codeApprenant, codePeriode, "attestation de paiement");

	}

	private ByteArrayInputStream getStream(File file, String codeApprenant, String codeFormation, String document) {
		if(file!=null) {
			try {

				FileInputStream fileInputStream=new FileInputStream(file);
				byte[] data=new byte[(int) file.length()];
				try (BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)){
					bufferedInputStream.read(data,0,data.length);
				}
				log.info("PDF generated {} {} ", codeApprenant, codeFormation);
				return new ByteArrayInputStream(data);
			} catch (IOException e) {
				log.info("Erreur à la génération du {} pour : {} {}",document, codeApprenant, codeFormation, e);
			}
		} else {
			log.info("Erreur à la génération du {} pour : {} {}", document, codeApprenant, codeFormation);
		}
		return null;
	}

}
