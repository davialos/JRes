package com.example.jres.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Node("State")
public class StateNode {

    @Id
    private String id;
    private String name;
    private String status;

    public StateNode() {
        this.id = UUID.randomUUID().toString();
    }

    public StateNode(String name, String status) {
        this();
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
