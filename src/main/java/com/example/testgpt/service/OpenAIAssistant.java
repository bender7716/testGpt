package com.example.testgpt.service;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Collections;

public class OpenAIAssistant {
    private static final String API_URL = "https://api.openai.com/v1/files";
    private static final String API_KEY = "YOUR_API_KEY"; // Замените на ваш API-ключ

    private final RestTemplate restTemplate;

    public OpenAIAssistant() {
        this.restTemplate = new RestTemplate();
    }

    public String uploadFile(String filePath, String purpose) {
        File file = new File(filePath);
        FileSystemResource fileResource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + API_KEY);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);
        body.add("purpose", purpose);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to upload file: " + response.getStatusCode());
        }
    }

    public static void main(String[] args) {
        OpenAIAssistant assistant = new OpenAIAssistant();
        try {
            String response = assistant.uploadFile("path/to/your/file.txt", "fine-tune");
            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}