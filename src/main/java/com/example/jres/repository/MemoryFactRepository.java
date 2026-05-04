package com.example.jres.repository;

import com.example.jres.model.MemoryFact;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryFactRepository extends Neo4jRepository<MemoryFact, String> {
}
