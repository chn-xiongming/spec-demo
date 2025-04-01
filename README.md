# Spring Boot User Management Demo

This project demonstrates a layered architecture implementation of a user management system using Spring Boot, MyBatis, and H2 database.

## Project Structure

```
src/main/java/com/example/demo/
├── Application.java
├── common/
│   └── exception/
│       └── GlobalExceptionHandler.java
├── model/
│   ├── bo/
│   │   └── user/
│   │       └── UserBO.java
│   ├── po/
│   │   └── user/
│   │       └── UserPO.java
│   └── vo/
│       └── user/
│           └── UserVO.java
├── manager/
│   └── business/
│       └── user/
│           ├── UserManager.java
│           └── UserManagerImpl.java
├── repository/
│   └── mapper/
│       └── UserMapper.java
├── service/
│   └── business/
│       └── user/
│           ├── UserService.java
│           └── UserServiceImpl.java
└── web/
    └── user/
        ├── command/
        │   ├── UserCommandController.java
        │   ├── UserCreateCommand.java
        │   └── UserUpdateCommand.java
        └── query/
            └── UserQueryController.java
```

## Features

- User CRUD operations
- Layered architecture (Web, Service, Manager, Repository)
- Input validation and error handling
- Swagger API documentation
- H2 in-memory database
- MyBatis ORM integration
- Unit tests

## Getting Started

### Prerequisites

- JDK 11 or later
- Maven 3.6 or later

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn spring-boot:run`
4. Access the application at http://localhost:8080
5. Access Swagger UI at http://localhost:8080/swagger-ui/

### Running Tests

```bash
mvn test
```

## API Endpoints

### User Management

- `POST /api/v1/users` - Create a new user
- `PUT /api/v1/users/{id}` - Update an existing user
- `DELETE /api/v1/users/{id}` - Delete a user
- `GET /api/v1/users/{id}` - Get user by ID
- `GET /api/v1/users/username/{username}` - Get user by username

## Architecture

- **Web Layer**: Controllers handle HTTP requests/responses
- **Service Layer**: Business logic and transaction management
- **Manager Layer**: Domain operations and object transformations
- **Repository Layer**: Database access using MyBatis

## Database

The application uses H2 in-memory database with the following schema:

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);
