# AGENTS.md — ExpenseTracker

## Project Identity

Spring Boot 4.1.0 REST API for personal expense tracking. Java 21. Maven build. PostgreSQL backend.

**Package root:** `com.demouser.expensetracker`
**Source:** `src/main/java/com/demouser/expensetracker/`

## Build & Run

```bash
./mvnw clean compile          # compile (no test)
./mvnw spring-boot:run        # run (port 8083)
./mvnw test                   # run tests
./mvnw clean package -DskipTests  # build JAR
```

Server runs on **port 8083** (`server.port=8083` in application.properties).

## Architecture

```
controller/  →  service/  →  repository/  →  entity/  (JPA)
    ↓                            ↓
  dto/                    PostgreSQL (localhost:5434)
```

- **Entities:** `User`, `Expense`, `Category` — JPA `@Entity` classes
- **DTOs:** Java `record` types with Jakarta Bean Validation (`@NotBlank`, `@NotNull`, etc.)
- **Services:** Business logic + entity↔DTO mapping. No `@Transactional` currently.
- **Controllers:** `@RestController` exposing `/api/v1/users` and `/api/v1/expenses`

## Known Build Issues (Do Not Repeat)

- `spring-boot-starter-data-jpa-test` and `spring-boot-starter-validation-test` in `pom.xml` **do not exist** — they break `mvn compile`. Remove them.
- Spring Security is **commented out** in `pom.xml`. The application currently has **no authentication**.
- There is a duplicate `spring-boot-starter-security` comment block (lines 73-76) that should be removed.

## Application State

The code has known issues that will be addressed incrementally. When making changes:

- `ExpenseService.create()` does not set `user` on the expense — expenses are orphaned (no user_id FK). Add `UserRepository` lookup via `dto.userId()`.
- `ExpenseService.updateById()` silently ignores `expenseDate` and `category` fields.
- Services use generic `RuntimeException` for "not found" — returns HTTP 500 instead of 404. Create `ResourceNotFoundException` with `@ResponseStatus(NOT_FOUND)`.
- `Category.toString()` references the lazy `expenses` collection — triggers full DB load. Remove it.
- No `@Transactional` on service classes — multi-repo calls are not atomic.
- Password is stored as plain text in `User` entity. `PasswordEncoder` (BCrypt) must be injected into `UserService` once Security is enabled.

## Dependencies

Only these are real (will resolve):
- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-validation`
- `postgresql` (runtime)

## Entity Relationships

```
User  ──1:N──>  Expense  <──N:1──  Category
```

- `User` has `@OneToMany(mappedBy = "user") List<Expense>`
- `Category` has `@OneToMany(mappedBy = "category") List<Expense>`
- `Expense` has `@ManyToOne` to both `User` and `Category`

## Constraints

- **No changes without user permission.** This is a learning project. When asked to investigate, read-only first. Only make edits when explicitly told.
- No CI/CD pipeline exists. No lint, formatter, or typecheck config.
- No test coverage — only a single `contextLoads()` test.
