package com.validator.htmlvalidator.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class OpenaiService {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    public String getOpenaiResponse(String input) {
        // Prepare HTTP request
        String requestUrl = apiUrl + "?model=" + model;
        String requestBody = "{\"prompt\": \"" + input + "\"}";

        // Add API key to headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Make HTTP POST request
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = new RestTemplate().postForEntity(requestUrl, entity, String.class);

        // Return response body
        return response.getBody();
    }
}
