# Dental Clinic Management
# 🦷 Dental Clinic Management System (Backend)

## 📌 Project Description

This is a backend service for a dental clinic management system built with Spring Boot.  
The system is designed for **doctors and administrators only** (patients do not log in).  
It provides functionality to manage doctors, patients, appointments (calendar), treatment history, and performed medical procedures.  
Authentication and authorization are handled via **JWT tokens** with role-based access control (`ADMIN`, `DOCTOR`).

---

## 🚀 Features Implemented

### 🔐 User Authentication & Authorization
- JWT-based authentication
- Role-based access control (`ADMIN`, `DOCTOR`)

### 👨‍⚕️ Doctor Management
- Create, update, delete, and view doctors
- Link doctor accounts to system users

### 👥 Patient Management
- Comprehensive patient profiles (name, contact information, etc.)
- Medical history records

### 📅 Appointment Management
- Schedule and manage appointments
- Assign doctor and patient to an appointment

### 🦷 Treatment Records
- Store treatments/procedures performed for a patient
- Linked to specific doctor and patient

### 🔒 Security
- Password hashing (Spring Security + BCrypt)
- JWT filters for secure API access
- DTO-based API architecture

### ✅ Testing
- Service layer tests (with Mockito)
- Controller tests (with `@WebMvcTest`)

---

## 🛠 Tech Stack

- **Language:** Java 17
- **Frameworks:** Spring Boot, Spring Security, Spring Data JPA
- **Database:** PostgreSQL
- **Authentication:** JWT (JSON Web Tokens)
- **Testing:** JUnit 5, Mockito
- **Build Tool:** Maven

---

## ⚙️ How to Run

### 1. Clone the repository
```bash
git clone https://github.com/Daria-Voloshanovska/dental-clinic-management.git
cd dental-clinic-management
````

### 2. Configure the database
Update your PostgreSQL credentials in src/main/resources/application.properties:

`spring.datasource.url=jdbc:postgresql://localhost:5432/dental_clinic`
`spring.datasource.username=your_username`
`spring.datasource.password=your_password`

### 3.Build the project
```bash
mvn clean install
```
### 4.Run the application
```bash
mvn spring-boot:run
```
### 5. Access the API
Default base URL: http://localhost:8080/api

API endpoints are protected with JWT authentication

Example endpoints:
`POST /api/auth/register → Register a new user (admin/doctor)`
`POST /api/auth/login → Get JWT token`
`GET /api/doctors → List doctors (requires JWT)`
`GET /api/patients → List patients (requires JWT)`
`POST /api/appointments → Create appointment (requires JWT)`
