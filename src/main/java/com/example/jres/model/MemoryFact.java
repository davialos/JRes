package com.example.jres.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.Instant;
import java.util.UUID;

@Node("MemoryFact")
public class MemoryFact {

    @Id
    private String id;
    private String content;
    private Instant createdAt;

    public MemoryFact() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }

    public MemoryFact(String content) {
        this();
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
