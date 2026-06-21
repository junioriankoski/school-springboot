# School Management API

A REST API built with Spring Boot for managing students, books, and teachers, 
featuring a layered architecture, DTOs, and centralized error handling.

## Tech Stack
- Java 21
- Spring Boot 3
- Spring Data JPA
- H2 Database (in-memory)
- Maven

## Architecture
The project follows a layered structure:
- **Controller** — handles HTTP requests and responses
- **Service** — contains business logic
- **Repository** — handles database access
- **DTOs** (Request/Response) — control what data enters and leaves the API

This separation keeps business logic independent from HTTP concerns, 
making the codebase easier to test, maintain, and extend.

## Endpoints

### Students (`/alunos`)
- `GET /alunos` — list all students
- `GET /alunos/{id}` — get a student by id
- `POST /alunos` — create a student
- `PUT /alunos/{id}` — update a student
- `DELETE /alunos/{id}` — delete a student

### Books (`/livros`)
- `GET /livros` — list all books
- `GET /livros/{id}` — get a book by id
- `POST /livros` — create a book
- `PUT /livros/{id}` — update a book
- `DELETE /livros/{id}` — delete a book

### Teachers (`/professores`)
- `GET /professores` — list all teachers
- `GET /professores/{id}` — get a teacher by id
- `POST /professores` — create a teacher
- `PUT /professores/{id}` — update a teacher
- `DELETE /professores/{id}` — delete a teacher

## Error Handling
Custom exceptions and a global exception handler (`@ControllerAdvice`) 
return clean, consistent JSON error responses with proper HTTP status codes, 
instead of exposing raw stack traces.

Example: requesting a student that doesn't exist returns a `404` with a 
clear error message instead of a generic `500`.

## How to Run

\`\`\`bash
./mvnw spring-boot:run
\`\`\`

The API will be available at `http://localhost:8080`.

A set of sample students, books, and teachers is automatically loaded 
on startup via a `DataLoader`.

## Author
Junior Iankoski — [GitHub](https://github.com/junioriankoski)

## Status
🚧 Work in progress — currently learning entity relationships 
(`@ManyToOne`, `@OneToMany`) and expanding test coverage.