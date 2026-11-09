# Personal CRM

Small Spring Boot backend for keeping personal contacts. It intentionally exposes RPC-style routes, making it a useful refactoring exercise.

## Run

Requires Java 21+ and Maven 3.9+.

```bash
mvn spring-boot:run
```

The embedded H2 database and seed records start with the application. The H2 console is at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:crm`).

## Current API (intentionally non-RESTful)

| Method | Route | Purpose |
|---|---|---|
| GET | `/crm/contacts?stage=ACTIVE&q=maya` | list/search contacts |
| POST | `/crm/contact/new` | create a contact |
| POST | `/crm/contact/{id}/change` | update a contact |
| POST | `/crm/contact/{id}/touched` | set `lastContactedOn` to today |
| POST | `/crm/contact/{id}/remove` | delete a contact |

Example request:

```bash
curl -X POST http://localhost:8080/crm/contact/new \
  -H 'Content-Type: application/json' \
  -d '{"name":"Ada Lovelace","email":"ada@example.com","company":"Analytical Engines","stage":"LEAD"}'
```
