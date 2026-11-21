# Food Waste Management System

A Spring Boot REST API that connects **food donors** with **NGOs** so surplus food reaches people in need instead of going to waste.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-REST%20API-6DB33F)
![Spring Security](https://img.shields.io/badge/Spring%20Security-RBAC-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36)

---

## Overview

Every day, restaurants, caterers, events and households throw away food that is still perfectly good, while NGOs struggle to source meals for the people they serve. This project provides the backend that bridges the two:

- **Donors** list surplus food they want to give away.
- **NGOs** browse what's available and request it for distribution.
- **Admins** oversee users and donations to keep the platform healthy.

Access is controlled with role-based authorization, so each user only sees and does what their role allows.

---

## Features

### Donor
- Register and log in
- Donate food
- View donated food
- Delete a food listing

### NGO
- Register and log in
- Browse the list of available food
- Request food from donors
- Cancel a request

### Admin
- Log in to the system
- View all food donations
- View, look up and delete users

### Security
- Authentication and authorization with **Spring Security**
- Three roles: `DONOR`, `NGO`, `ADMIN`
- Role-protected API endpoints

---

## Tech Stack

| Layer            | Technology                    |
| ---------------- | ----------------------------- |
| Language         | Java 17                       |
| Framework        | Spring Boot                   |
| Security         | Spring Security               |
| Persistence      | Spring Data JPA, Hibernate    |
| Database         | MySQL                         |
| Build tool       | Maven (Maven Wrapper included)|
| API testing      | Postman                       |
| Version control  | Git & GitHub                  |

---

## Project Structure

```
src/main/java/com/example/foodmanagement
├── controller   # REST API controllers
├── dto          # Data Transfer Objects
├── entity       # JPA entities (database tables)
├── repository   # Spring Data repositories
├── service      # Business logic
├── security     # Spring Security configuration
└── exception    # Global exception handling
```

---

## API Endpoints

> Endpoints are grouped by the role that uses them. Protected endpoints require authentication.

### Users

| Method | Endpoint               | Description           | Access |
| ------ | ---------------------- | --------------------- | ------ |
| POST   | `/api/users/register`  | Register a new user   | Public |

### Food (Donor)

| Method | Endpoint                  | Description                  | Access |
| ------ | ------------------------- | ---------------------------- | ------ |
| POST   | `/api/foods/donate`       | Donate (list) surplus food   | Donor  |
| GET    | `/api/foods/foodlist`     | View donated / available food| Donor, NGO |
| DELETE | `/api/foods/delete/{id}`  | Delete a food listing        | Donor  |

### Requests (NGO)

| Method | Endpoint                          | Description                | Access |
| ------ | --------------------------------- | -------------------------- | ------ |
| POST   | `/api/requests/request{foodid}`   | Request a specific food item | NGO  |
| DELETE | `/api/requests/delete/{id}`       | Delete / cancel a request  | NGO    |

### Admin

| Method | Endpoint                        | Description          | Access |
| ------ | ------------------------------- | -------------------- | ------ |
| GET    | `/api/admin/showusers`          | List all users       | Admin  |
| GET    | `/api/admin/showusers/{id}`     | Get a user by ID     | Admin  |
| DELETE | `/api/admin/deleteusers/{id}`   | Delete a user        | Admin  |

---

## Getting Started

### Prerequisites

- **JDK 17** or later
- **MySQL** 8.x (running locally or remotely)
- **Maven** (optional, the included `mvnw` wrapper works too)
- An IDE such as IntelliJ IDEA or Eclipse (optional)
- **Postman** (or any HTTP client) for testing

### 1. Clone the repository

```bash
git clone https://github.com/theaquibjaved/food-waste-management-system.git
cd food-waste-management-system
```

### 2. Create the database

```sql
CREATE DATABASE food_waste_db;
```

### 3. Configure the application

Edit `src/main/resources/application.properties` with your MySQL details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/food_waste_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Replace the database name, username and password with your own. Avoid committing real credentials to version control.

### 4. Run the application

Using the Maven Wrapper:

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Or run the main Spring Boot class directly from your IDE.

By default the API is available at **http://localhost:8080**.

### 5. Build a JAR (optional)

```bash
./mvnw clean package
java -jar target/*.jar
```

---

## Testing the API

Use **Postman** to exercise the endpoints:

1. **Register** users with each role (`DONOR`, `NGO`, `ADMIN`) via `POST /api/users/register`.
2. **Authenticate** as the relevant user on each request (set the credentials in Postman's *Authorization* tab).
3. As a **Donor**, create a listing with `POST /api/foods/donate`.
4. As an **NGO**, view the list with `GET /api/foods/foodlist` and request an item.
5. As an **Admin**, review users with `GET /api/admin/showusers`.

Example request body for registration:

```json
{
  "name": "Helping Hands NGO",
  "email": "contact@helpinghands.org",
  "password": "StrongPassword123",
  "role": "NGO"
}
```

> The exact field names may differ. Check the DTO classes in `src/main/java/.../dto` for the current schema.

---

## Typical Workflow

```
 Donor                    System                     NGO
   │  POST /foods/donate    │                          │
   │ ─────────────────────► │  food saved & listed     │
   │                        │ ◄─────────────────────── │  GET /foods/foodlist
   │                        │ ◄─────────────────────── │  POST /requests/request...
   │                        │  request recorded        │
   ▼                        ▼                          ▼
        Surplus food reaches people who need it
```

---

## Roadmap

Ideas for future improvements:

- [ ] JWT-based authentication
- [ ] Food expiry tracking and automatic removal of expired listings
- [ ] Request approval / status workflow (pending, accepted, rejected, collected)
- [ ] Location-based matching of donors and nearby NGOs
- [ ] Email / SMS notifications
- [ ] Pagination, filtering and search on food listings
- [ ] Swagger / OpenAPI documentation
- [ ] Unit and integration tests
- [ ] Docker and Docker Compose setup

---

## Contributing

Contributions are welcome!

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## Author

**Aquib Javed**
GitHub: [@theaquibjaved](https://github.com/theaquibjaved)

---

> *Reducing food waste, one donation at a time.*
