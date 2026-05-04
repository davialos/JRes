package com.example.jres.dto;

import jakarta.validation.constraints.NotBlank;

public class ChatRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
