# JRes

A starter Java Spring Boot project for a simple AI application backed by a state graph and graph database memory store.

## Features

- Spring Boot REST API
- Neo4j-backed graph memory
- Conversation state graph model
- Langraph integration placeholder service
- Unit test structure for controller and service layers

## Run locally

1. Start Neo4j:
   ```bash
   docker compose up -d
   ```
2. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```
3. Call the API:
   ```bash
   curl -X POST http://localhost:8080/api/chat \
     -H "Content-Type: application/json" \
     -d '{"userId":"user-1","message":"Hello"}'
   ```

## Notes

- `LangraphService` is scaffolded as a placeholder and can be wired to an actual Langraph API or SDK.
- The graph memory model uses Spring Data Neo4j entities and relationships.
