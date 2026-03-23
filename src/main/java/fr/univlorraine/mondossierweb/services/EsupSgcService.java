package fr.univlorraine.mondossierweb.services;

import fr.univlorraine.mondossierweb.controllers.ConfigController;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class EsupSgcService {

    private transient String esupSgcPhotoUrl;

    @Autowired
    private transient ConfigController configController;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        refreshParameters();
    }

    public void refreshParameters() {
        esupSgcPhotoUrl = configController.getEsupSgcPhotoUrl();
    }

    public boolean isPhotoServiceOperational() {
        return StringUtils.hasText(esupSgcPhotoUrl) && esupSgcPhotoUrl.contains("%s");
    }

    public ByteArrayInputStream getPhoto(String supannEtuId) {
        if(!isPhotoServiceOperational()) {
            return null;
        }
        byte[] photoData;
        String url = String.format(esupSgcPhotoUrl, supannEtuId);
        log.debug("GET PHOTO : {}", url);
        try {
            ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
            photoData = response.getBody();
        } catch(HttpClientErrorException he) {
            log.warn("Récupération de la photo de {} en erreur HTTP {}", supannEtuId, he.getStatusCode());
            photoData = he.getResponseBodyAsByteArray();
        } catch (Exception e) {
            log.error("Une erreur est survenue lors de la récupération de la photo de {}", supannEtuId, e);
            return null;
        }
        if(photoData == null || photoData.length == 0) {
            return null;
        } else {
            return new ByteArrayInputStream(photoData);
        }
    }

}
