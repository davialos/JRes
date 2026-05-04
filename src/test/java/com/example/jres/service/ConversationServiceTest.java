package com.example.jres.service;

import com.example.jres.dto.ChatRequest;
import com.example.jres.dto.ChatResponse;
import com.example.jres.model.Conversation;
import com.example.jres.model.StateNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConversationServiceTest {

    @Mock
    private GraphMemoryService graphMemoryService;

    @Mock
    private LangraphService langraphService;

    @InjectMocks
    private ConversationService conversationService;

    private Conversation conversation;

    @BeforeEach
    void setUp() {
        conversation = new Conversation("user-1");
        conversation.setCurrentState(new StateNode("start", "initialized"));
    }

    @Test
    void handleConversationReturnsChatResponse() {
        when(graphMemoryService.loadOrCreateConversation(eq("user-1"))).thenReturn(conversation);
        when(langraphService.getResponse(eq("user-1"), eq("Hello"), eq("start"))).thenReturn("Hello from Langraph");
        when(graphMemoryService.createMemoryFact(any(String.class))).thenReturn(null);
        when(graphMemoryService.buildState(any(String.class), any(String.class))).thenReturn(new StateNode("conversation", "updated"));
        when(graphMemoryService.saveConversation(any(Conversation.class))).thenReturn(conversation);

        ChatRequest request = new ChatRequest();
        request.setUserId("user-1");
        request.setMessage("Hello");

        ChatResponse response = conversationService.handleConversation(request);

        assertEquals("Hello from Langraph", response.getResponse());
        assertEquals(conversation.getId(), response.getConversationId());
    }
}
