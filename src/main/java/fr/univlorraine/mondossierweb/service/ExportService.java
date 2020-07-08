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
		
		File cert = pegaseService.certificatDeScolarite(codeApprenant, codeFormation);

		if(cert!=null) {
			try {

				FileInputStream fileInputStream=new FileInputStream(cert);
				byte[] data=new byte[(int) cert.length()];
				BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);

				bufferedInputStream.read(data,0,data.length);
				bufferedInputStream.close();
				log.info("PDF generated.");
				return new ByteArrayInputStream(data);
			} catch (IOException e) {
				log.info("Erreur à la génération du certificat de scolarité pour : {} {}", codeApprenant, codeFormation, e);
			}
		} else {
			log.info("Erreur à la génération du certificat de scolarité pour : {} {}", codeApprenant, codeFormation);
		}
		return null;

	}

}
