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
package fr.univlorraine.pegase.api.auth;

import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.oltu.oauth2.client.HttpClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
import org.apache.oltu.oauth2.client.response.OAuthClientResponseFactory;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class OAuthOkHttpClient implements HttpClient {
    private OkHttpClient client;

    public OAuthOkHttpClient() {
        this.client = new OkHttpClient();
    }

    public OAuthOkHttpClient(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public <T extends OAuthClientResponse> T execute(OAuthClientRequest request, Map<String, String> headers,
                                                     String requestMethod, Class<T> responseClass)
            throws OAuthSystemException, OAuthProblemException {

        MediaType mediaType = MediaType.parse("application/json");
        Request.Builder requestBuilder = new Request.Builder().url(request.getLocationUri());

        if(headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("Content-Type")) {
                    mediaType = MediaType.parse(entry.getValue());
                } else {
                    requestBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        RequestBody body = request.getBody() != null ? RequestBody.create(mediaType, request.getBody()) : null;
        requestBuilder.method(requestMethod, body);

        try {
            Response response = client.newCall(requestBuilder.build()).execute();
            return OAuthClientResponseFactory.createCustomResponse(
                    response.body().string(),
                    response.body().contentType().toString(),
                    response.code(),
                    responseClass);
        } catch (IOException e) {
            throw new OAuthSystemException(e);
        }
    }

    @Override
    public void shutdown() {
        // Nothing to do here
    }
}
