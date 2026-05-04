package com.example.jres.service;

import com.example.jres.dto.ChatRequest;
import com.example.jres.dto.ChatResponse;
import com.example.jres.model.Conversation;
import com.example.jres.model.MemoryFact;
import com.example.jres.model.StateNode;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    private final GraphMemoryService graphMemoryService;
    private final LangraphService langraphService;

    public ConversationService(GraphMemoryService graphMemoryService, LangraphService langraphService) {
        this.graphMemoryService = graphMemoryService;
        this.langraphService = langraphService;
    }

    public ChatResponse handleConversation(ChatRequest request) {
        Conversation conversation = graphMemoryService.loadOrCreateConversation(request.getUserId());

        StateNode currentState = conversation.getCurrentState();
        if (currentState == null) {
            currentState = graphMemoryService.buildState("start", "initialized");
            conversation.setCurrentState(currentState);
        }

        String responseText = langraphService.getResponse(request.getUserId(), request.getMessage(), currentState.getName());

        MemoryFact memoryFact = graphMemoryService.createMemoryFact(request.getMessage());
        conversation.addMemoryFact(memoryFact);

        StateNode nextState = graphMemoryService.buildState("conversation", "updated");
        conversation.setCurrentState(nextState);
        graphMemoryService.saveConversation(conversation);

        return new ChatResponse(responseText, conversation.getId());
    }
}
