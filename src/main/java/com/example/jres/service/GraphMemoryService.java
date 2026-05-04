package com.example.jres.service;

import com.example.jres.model.Conversation;
import com.example.jres.model.MemoryFact;
import com.example.jres.model.StateNode;
import com.example.jres.repository.ConversationRepository;
import com.example.jres.repository.MemoryFactRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GraphMemoryService {

    private final ConversationRepository conversationRepository;
    private final MemoryFactRepository memoryFactRepository;

    public GraphMemoryService(ConversationRepository conversationRepository,
                              MemoryFactRepository memoryFactRepository) {
        this.conversationRepository = conversationRepository;
        this.memoryFactRepository = memoryFactRepository;
    }

    public Conversation loadOrCreateConversation(String userId) {
        Optional<Conversation> conversation = conversationRepository.findByUserId(userId);
        return conversation.orElseGet(() -> conversationRepository.save(new Conversation(userId)));
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public MemoryFact createMemoryFact(String content) {
        MemoryFact fact = new MemoryFact(content);
        return memoryFactRepository.save(fact);
    }

    public StateNode buildState(String name, String status) {
        return new StateNode(name, status);
    }
}
