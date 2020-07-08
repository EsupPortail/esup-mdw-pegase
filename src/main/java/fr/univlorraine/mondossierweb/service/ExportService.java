package fr.univlorraine.mondossierweb.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadin.flow.server.StreamResource;

import lombok.extern.slf4j.Slf4j;

@Service
@SuppressWarnings("serial")
@Slf4j
public class ExportService implements Serializable {


	@Autowired
	private transient PegaseService pegaseService;

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
	public ByteArrayInputStream  getAttestation(String codeApprenant, String codeFormation) {
		
		File file = pegaseService.attestationDePaiement(codeApprenant, codeFormation);

		return getStream(file,codeApprenant, codeFormation, "attestation de paiement");

	}

	private ByteArrayInputStream getStream(File file, String codeApprenant, String codeFormation, String document) {
		if(file!=null) {
			try {

				FileInputStream fileInputStream=new FileInputStream(file);
				byte[] data=new byte[(int) file.length()];
				BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);

				bufferedInputStream.read(data,0,data.length);
				bufferedInputStream.close();
				log.info("PDF generated.");
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
