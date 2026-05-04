package com.example.jres.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Node("Conversation")
public class Conversation {

    @Id
    private String id;
    private String userId;

    @Relationship(type = "AT_STATE", direction = Relationship.Direction.OUTGOING)
    private StateNode currentState;

    @Relationship(type = "HAS_MEMORY", direction = Relationship.Direction.OUTGOING)
    private List<MemoryFact> memoryFacts = new ArrayList<>();

    public Conversation() {
        this.id = UUID.randomUUID().toString();
    }

    public Conversation(String userId) {
        this();
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public StateNode getCurrentState() {
        return currentState;
    }

    public void setCurrentState(StateNode currentState) {
        this.currentState = currentState;
    }

    public List<MemoryFact> getMemoryFacts() {
        return memoryFacts;
    }

    public void addMemoryFact(MemoryFact fact) {
        this.memoryFacts.add(fact);
    }
}
