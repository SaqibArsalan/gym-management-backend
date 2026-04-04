# Gym Management Backend

A multi-module Spring Boot backend for managing all aspects of a gym — members, memberships, classes, sessions, staff, authentication, and an AI-powered assistant.

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Modules](#modules)
  - [gym-core](#gym-core)
  - [gym-identity](#gym-identity)
  - [gym-membership](#gym-membership)
  - [gym-classes-and-sessions](#gym-classes-and-sessions)
  - [gym-staff](#gym-staff)
  - [gym-ai](#gym-ai)
- [API Endpoints](#api-endpoints)
- [Authentication Flow](#authentication-flow)
- [Database Configuration](#database-configuration)
- [Getting Started](#getting-started)
- [Features](#features)

---

## Overview

This is the backend system for a full-featured gym management application. It is built as a Maven multi-module project using **Kotlin** and **Spring Boot 3**, with **PostgreSQL** as the database and **JWT** for authentication. It also integrates with **OpenAI** to provide an AI gym assistant.

---

## Tech Stack

| Category       | Technology                          |
|----------------|-------------------------------------|
| Language       | Kotlin 1.9.25                       |
| Framework      | Spring Boot 3.4.2                   |
| JDK            | Java 17                             |
| Build Tool     | Maven (multi-module)                |
| Database       | PostgreSQL                          |
| ORM            | Spring Data JPA / Hibernate         |
| Security       | Spring Security 6.4.2               |
| Authentication | JWT (JJWT 0.12.6)                   |
| AI Integration | OpenAI API (GPT-4o-mini)            |
| HTTP Client    | Spring WebFlux WebClient            |
| ID Generation  | friendly-id 1.1.0                   |
| Testing        | JUnit 5, kotlin-test-junit5         |

---

## Project Structure

```
gym-management-backend/
├── gym-core/                     # Application entry point (aggregator)
├── gym-identity/                 # Authentication, users, and roles
├── gym-membership/               # Membership plans and subscriptions
├── gym-classes-and-sessions/     # Fitness classes and sessions
├── gym-staff/                    # Staff management
├── gym-ai/                       # AI chat assistant
├── application.properties        # Shared configuration
├── pom.xml                       # Parent POM
└── mvnw / mvnw.cmd               # Maven wrapper
```

---

## Modules

### gym-core

The main Spring Boot application entry point. It aggregates all other modules and bootstraps the application.

**Key dependencies:** All other modules, PostgreSQL JDBC driver, JJWT

---

### gym-identity

Handles user registration, authentication, roles, and JWT token issuance.

**Structure:**
```
gym-identity/
└── src/main/kotlin/com/gym/identity/
    ├── advice/          # Global exception handling
    ├── config/          # Security, CORS, JWT filter configuration
    ├── controller/      # REST controllers + DTOs
    ├── exception/       # Custom exception classes
    ├── model/           # User, Role, UserRoles entities
    ├── repository/      # JPA repositories
    ├── service/         # Business logic
    └── util/            # JWT token provider utility
```

**Entities:** `User`, `Role`, `UserRoles`

**Key services:**
- `AuthenticationService` — validates credentials and issues JWT tokens
- `UsersService` — CRUD for users with email uniqueness validation
- `RoleService` — role management and assignment
- `JwtTokenProvider` — token generation and validation (24-hour expiry)

---

### gym-membership

Manages membership plans (tiers/pricing) and member subscriptions.

**Structure:**
```
gym-membership/
└── src/main/kotlin/com/gym/membership/
    ├── advice/
    ├── controller/      # Plan and subscription controllers + DTOs
    ├── exception/
    ├── model/           # MembershipPlans, MembershipSubscription
    ├── repository/
    └── service/
```

**Entities:** `MembershipPlans`, `MembershipSubscription`

**Key services:**
- `MembershipPlanService` — CRUD for membership tiers
- `MembershipSubscriptionService` — handle subscriptions, calculate expiry dates, analytics (active members, new sign-ups)

---

### gym-classes-and-sessions

Schedules and manages gym classes and individual training sessions.

**Structure:**
```
gym-classes-and-sessions/
└── src/main/kotlin/com/gym/classes/
    ├── Repository/
    ├── controller/      # Class and session controllers + DTOs
    ├── exception/
    ├── model/           # GymClass, Session, Attendance
    └── service/
```

**Entities:** `GymClass`, `Session`, `Attendance`

**Key services:**
- `ClassManagementService` — CRUD and search for gym classes
- `SessionManagementService` — schedule sessions linked to classes

---

### gym-staff

Manages gym employees and personnel records.

**Structure:**
```
gym-staff/
└── src/main/kotlin/com/gym/staff/
    ├── advice/
    ├── controller/      # Staff controller + DTOs
    ├── exception/
    ├── model/           # Staff entity
    ├── repository/
    └── service/
```

**Entities:** `Staff` (name, userId, salary, hireDate)

**Key services:**
- `StaffService` — CRUD and name-based search for staff

---

### gym-ai

Integrates with the OpenAI API to provide an AI-powered gym assistant via chat.

**Structure:**
```
gym-ai/
└── src/main/kotlin/com/gym/ai/
    ├── controller/      # AI chat controller + DTOs
    └── service/         # OpenAI WebClient integration
```

**Key details:**
- Uses GPT-4o-mini with a system prompt: *"You are a helpful gym assistant"*
- Async HTTP calls via Spring WebFlux `WebClient`
- Gracefully handles API errors with a fallback message

---

## API Endpoints

### Authentication — `/v1/auth`

| Method | Endpoint         | Description                            |
|--------|------------------|----------------------------------------|
| POST   | `/v1/auth/login` | Authenticate and receive a JWT token   |

### Users — `/v1/identity/users`

| Method | Endpoint                           | Description            |
|--------|------------------------------------|------------------------|
| GET    | `/v1/identity/users`               | Get all users          |
| GET    | `/v1/identity/users/{userId}`      | Get user by ID         |
| POST   | `/v1/identity/users`               | Create a new user      |
| GET    | `/v1/identity/users/search?name=`  | Search users by name   |

### Roles — `/v1/role`

| Method | Endpoint                     | Description              |
|--------|------------------------------|--------------------------|
| POST   | `/v1/role`                   | Create a new role        |
| POST   | `/v1/role/assign-role-to-user` | Assign a role to a user |

### Membership Plans — `/v1/memberships/plans`

| Method | Endpoint                          | Description                    |
|--------|-----------------------------------|--------------------------------|
| POST   | `/v1/memberships/plans`           | Create a membership plan       |
| GET    | `/v1/memberships/plans`           | Get all membership plans       |
| GET    | `/v1/memberships/plans/{planId}`  | Get a specific plan            |
| GET    | `/v1/memberships/plans/dropdown`  | Get plans for dropdown (UI)    |

### Membership Subscriptions — `/v1/memberships`

| Method | Endpoint                                   | Description                        |
|--------|--------------------------------------------|------------------------------------|
| POST   | `/v1/memberships`                          | Create a subscription              |
| GET    | `/v1/memberships`                          | Get all subscriptions              |
| GET    | `/v1/memberships/{id}`                     | Get subscription by ID             |
| GET    | `/v1/memberships/user/{userId}`            | Get user's active memberships      |
| GET    | `/v1/memberships/active-subscriptions`     | Count active subscriptions         |
| GET    | `/v1/memberships/members`                  | Count total active members         |
| GET    | `/v1/memberships/new-signups`              | Count new sign-ups this month      |

### Classes — `/v1/classes`

| Method | Endpoint                      | Description                    |
|--------|-------------------------------|--------------------------------|
| POST   | `/v1/classes`                 | Create a gym class             |
| GET    | `/v1/classes`                 | Get all classes                |
| GET    | `/v1/classes/{id}`            | Get class details              |
| GET    | `/v1/classes/search?name=`    | Search classes by name         |

### Sessions — `/v1/sessions`

| Method | Endpoint                              | Description                         |
|--------|---------------------------------------|-------------------------------------|
| POST   | `/v1/sessions/classes/{classId}`      | Create a session for a class        |
| GET    | `/v1/sessions`                        | Get all sessions                    |

### Staff — `/v1/staff`

| Method | Endpoint                    | Description                  |
|--------|-----------------------------|------------------------------|
| POST   | `/v1/staff`                 | Add a staff member           |
| GET    | `/v1/staff`                 | Get all staff                |
| GET    | `/v1/staff/{userId}`        | Get staff by user ID         |
| GET    | `/v1/staff/search?name=`    | Search staff by name         |

### AI Assistant — `/v1/ai`

| Method | Endpoint       | Description                        |
|--------|----------------|------------------------------------|
| POST   | `/v1/ai/chat`  | Send a message to the AI assistant |

---

## Authentication Flow

1. Client sends credentials (email + password) to `POST /v1/auth/login`
2. Server validates password against the BCrypt-encoded hash in the database
3. A JWT token is generated containing:
   - **Subject:** user email
   - **Claims:** user ID, scopes
   - **Expiry:** 24 hours
4. The token is returned along with user info and assigned roles
5. Client includes the token in subsequent requests as:
   ```
   Authorization: Bearer <token>
   ```
6. `JwtAuthenticationFilter` intercepts every request and validates the token
7. On successful validation, the security context is populated

---

## Database Configuration

The application connects to a PostgreSQL database. Default settings in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gym_management
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.show-sql=true
```

**Key tables:**

| Table                      | Module                    | Description                        |
|----------------------------|---------------------------|------------------------------------|
| `users`                    | gym-identity              | User accounts and credentials      |
| `role`                     | gym-identity              | Roles with JSONB scope arrays      |
| `user_roles`               | gym-identity              | User-role join table               |
| `membership_plans`         | gym-membership            | Available membership tiers         |
| `membership_subscriptions` | gym-membership            | Member subscription records        |
| `classes`                  | gym-classes-and-sessions  | Gym class definitions              |
| `sessions`                 | gym-classes-and-sessions  | Individual class sessions          |
| `staff`                    | gym-staff                 | Staff/employee records             |

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+ (or use the included `mvnw` wrapper)
- PostgreSQL 12+ running on `localhost:5432`
- OpenAI API key (required for the AI module)

### 1. Database Setup

```sql
CREATE DATABASE gym_management;
```

Update `application.properties` if your PostgreSQL credentials differ from the defaults.

### 2. Build

```bash
./mvnw clean install
```

### 3. Run

```bash
./mvnw spring-boot:run -pl gym-core
```

The application will start on `http://localhost:8080`.

### 4. CORS

The backend is pre-configured to accept requests from `http://localhost:3000` (standard frontend dev port). Update `CorsConfig.kt` in `gym-identity` to change the allowed origin.

---

## Features

- ✅ **JWT Authentication** — Stateless, token-based security with 24-hour expiry
- ✅ **Role-Based Access Control** — Flexible role and scope management
- ✅ **Membership Management** — Plans with pricing/duration and member subscriptions
- ✅ **Class & Session Scheduling** — Manage fitness classes and schedule sessions
- ✅ **Staff Management** — Employee records with hire dates and salaries
- ✅ **AI Gym Assistant** — OpenAI-powered chat endpoint for gym-related queries
- ✅ **Analytics Endpoints** — Active members count, new sign-ups, active subscriptions
- ✅ **Search** — Name-based search for users, staff, and classes
- ✅ **Centralised Exception Handling** — Per-module `@ControllerAdvice` classes
- ✅ **CORS Support** — Ready for frontend integration
