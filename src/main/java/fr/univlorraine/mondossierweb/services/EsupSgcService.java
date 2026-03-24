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

@Service
@Slf4j
public class EsupSgcService {

    private final RestTemplate restTemplate = new RestTemplate();
    private transient String esupSgcPhotoUrl;
    @Autowired
    private transient ConfigController configController;

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
