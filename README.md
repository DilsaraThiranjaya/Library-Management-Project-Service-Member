# Services: Member Service

This repository contains the Spring Boot Member Service microservice for the Library Management System.

## Architectural Purpose

This service is responsible for managing the domain lifecycle of library Members. It utilizes a fully multi-layered architecture:
-   **Controllers:** Expose standard REST endpoints (`/api/members`).
-   **Services:** Handle business logic and mapping.
-   **Repositories:** Spring Data MongoDB interface for NoSQL operations.
-   **DTOs / Mappers:** MapStruct dynamically converts entities to transfer objects.
-   **Aspects:** Centralized AspectJ AOP logging.
-   **Exceptions:** Global `@RestControllerAdvice` handling custom domain runtime errors.

## Technical Stack

-   **Java**: 17 / 25
-   **Spring Boot**: 4.0.3 (or as per parent POM)
-   **Spring Cloud**: 2025.1.0 (Eureka / Config)
-   **Database**: MongoDB
-   **Utilities**: MapStruct (1.6.3), Lombok, Spring Validation

## Configuration

It pulls configurations actively from the Config Server running on `config.platform:9000` (resolvable to `localhost:8888` locally). It automatically attempts to bind to Eureka at `http://localhost:8761/eureka/`.

## Running Locally

Because this service demands a MongoDB database on port 27017, you can spin one up via Docker if you don't have MongoDB installed gracefully on your host machine.

### 1. Start MongoDB via Docker

Run the following command to temporarily start a detached MongoDB instance.

```bash
docker run -d \
  -p 27017:27017 \
  --name library-mongo \
  mongo:latest
```

### 2. Compile & Run Application

Ensure you execute a `clean compile` whenever MapStruct or Lombok domains are altered so annotations are processed properly into the `target` directory.

```bash
mvn clean compile
mvn spring-boot:run
```