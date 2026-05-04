package com.example.jres.controller;

import com.example.jres.dto.ChatRequest;
import com.example.jres.dto.ChatResponse;
import com.example.jres.service.ConversationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConversationController.class)
public class ConversationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConversationService conversationService;

    @Test
    void chatEndpointReturnsResponse() throws Exception {
        ChatResponse mockResponse = new ChatResponse("Hello from AI", "conversation-123");
        when(conversationService.handleConversation(any(ChatRequest.class))).thenReturn(mockResponse);

        ChatRequest request = new ChatRequest();
        request.setUserId("user-1");
        request.setMessage("Hello");

        mockMvc.perform(post("/api/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("Hello from AI"))
                .andExpect(jsonPath("$.conversationId").value("conversation-123"));
    }
}
