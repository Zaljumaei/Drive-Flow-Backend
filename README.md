# DriveFlow Backend

DriveFlow is a Spring Boot backend for a multitenant driving school management system.  
The project is designed as a realistic portfolio project for demonstrating backend development skills with Java, Spring Boot, JPA/Hibernate, PostgreSQL, DTOs, service-layer business logic, unit testing, and multitenancy.

## Project Goal

Many driving schools manage students, instructors, lessons and vehicles through different tools such as paper, Excel, WhatsApp, or separate software systems. DriveFlow aims to provide a centralized backend for managing these core processes in one system.

The first version focuses on a clean and maintainable backend architecture rather than a complete commercial product.

> **Note:** The current active development takes place on the `develop` branch. The `main` branch may not always contain the latest implementation state.

## Main Features

Planned and partially implemented features include:

- Driving school management
- Student management
- Instructor management
- Vehicle management
- Practical lesson management
- Theory topic and theory lesson management
- Theory attendance tracking
- Multitenant data separation
- DTO-based API design
- Service-layer business logic
- Unit tests with JUnit and Mockito

## Multitenancy Concept

DriveFlow is designed as a multitenant system.

In this project:

- One tenant represents one driving school.
- Tenant-specific data belongs to exactly one driving school.
- Tables such as students, instructors, vehicles, lessons, are tenant-scoped.
- During early development, the tenant is resolved from a request header.
- Later, the tenant will be resolved from the authenticated user or JWT token.

Current simplified approach:

```text
X-Tenant-ID: 1
```

Future approach:

```text
JWT -> authenticated user -> tenantId -> tenant-scoped data access
```

The service layer is responsible for ensuring that data is created, read, updated, and deleted only within the current tenant.

## Tech Stack

- Java 25
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- JUnit 5
- Mockito
- Docker / Docker Compose planned

## Architecture

The project follows a feature-based package structure.

Example:

```text
com.zaljumaei.driveflow
├── drivingschool
│   ├── domain
│   │   └── DrivingSchool.java 
│   ├── repository
│   │   └── DrivingSchoolRepository.java
│   ├── controller
│   ├── service 
│   │   ├── DrivingSchoolService.java
│   │   └── DrivingSchoolServiceImpl.java   
│   └── dto
│       ├── DrivingSchoolMapper.java
│       ├── DrivingSchoolRequest.java
│       └── DrivingSchoolResponse.java
│
├── student
├── instructor
├── lesson
├── vehicle
└── tenant
    ├── TenantContext.java
    ├── TenantFilter.java
 
```

## Domain Model Overview

Core domain objects:

- `DrivingSchool`
- `Student`
- `Instructor`
- `Vehicle`
- `PracticalLesson`
- `TheoryTopic`
- `TheoryLesson`
- `TheoryAttendance`

Important design decisions:

- `DrivingSchool` represents the tenant.
- Tenant-scoped entities reference a driving school through `tenant_id`.
- Practical lessons and theory lessons are modeled separately.
- Theory topics are stored separately from concrete theory lesson sessions.
- A student may attend the same theory topic multiple times through different lesson sessions.
- Student account/progress information is calculated from lessons, and attendances instead of being stored as duplicated summary data.

## Testing

The project includes unit tests for service-layer logic using JUnit 5 and Mockito.

The goal of the tests is to verify:

- Successful create/update/read operations
- Not-found cases
- Duplicate validation
- Correct repository interaction
- Correct mapping between request, entity, and response
- Tenant-safe access in later development steps

## Current Development Status

Current focus:

- Domain modeling
- Database modeling
- DTO design
- Mapper design
- Tenant context and tenant filtering
- Service-layer implementation
- Unit testing with JUnit and Mockito

Upcoming steps:

- Complete CRUD operations for core entities
- Add tenant-safe repository queries
- Add controller endpoints
- Add validation
- Add integration tests
- Add authentication and JWT-based tenant resolution
- Add Angular frontend
- Add Docker Compose setup

## How to Run

This section will be updated once the backend setup is finalized.

Planned local setup:

```bash
mvn clean install
mvn spring-boot:run
```

With Docker Compose planned:

```bash
docker compose up
```

## Learning Goals

This project is also used as a portfolio project to demonstrate practical software engineering skills:

- Clean Spring Boot architecture
- REST API design
- DTO usage
- JPA/Hibernate relationships
- Multitenancy basics
- Service-layer business logic
- Unit testing with Mockito
- Database modeling
- Realistic product thinking

## Roadmap

- [ ] Complete CRUD for all entities
- [ ] Add controller layer
- [ ] Implement practical lesson scheduling
- [ ] Implement theory topics and theory lessons
- [ ] Implement theory attendance tracking
- [ ] Add tenant-safe repository methods
- [ ] Add validation
- [ ] Add JWT authentication
- [ ] Add Angular frontend (Another Repository)
- [ ] Add Docker Compose
- [ ] Add CI pipeline

## Author

Zakaria Al-Jumaei