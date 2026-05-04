package com.example.jres.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LangraphService {

    private final String apiUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public LangraphService(@Value("${langraph.api.url}") String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getResponse(String userId, String message, String conversationState) {
        // Placeholder implementation for Langraph integration.
        // Replace with a real Langraph HTTP client or SDK call as needed.
        Map<String, Object> request = Map.of(
                "userId", userId,
                "message", message,
                "state", conversationState
        );

        try {
            Map<?, ?> result = restTemplate.postForObject(apiUrl, request, Map.class);
            if (result != null && result.containsKey("response")) {
                return result.get("response").toString();
            }
        } catch (Exception ignored) {
            // Fallback if the Langraph endpoint is not available.
        }

        return "[Langraph placeholder] Processed message: " + message;
    }
}
