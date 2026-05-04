package com.example.jres.repository;

import com.example.jres.model.Conversation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversationRepository extends Neo4jRepository<Conversation, String> {
    Optional<Conversation> findByUserId(String userId);
}
