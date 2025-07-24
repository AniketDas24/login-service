# 💪 Login Service – Gym Progress Tracker

Welcome to the **Login Service** for the **Gym Progress Tracker** app – a secure and scalable authentication microservice that ensures your users are verified and authorized before they flex their data!

## 📽️ Working Demo

🎥 [Click here to watch the demo](https://drive.google.com/file/d/1x84ZVGFYbSIgCZcN3p8RinCNhLXIdVyB/view?usp=sharing)

---

## 🔐 What It Does

This service manages **admin creation, user registration, email verification**, and **JWT-based role-based authentication**, ensuring only valid users and authorized roles can access the system.

### 🔁 Updated Workflow

1. 🛡️ **Admin Creation**  
   - Only one admin can be created via the `/admin/createAdmin` endpoint.  
   - The response includes a **JWT token** with `ADMIN` privileges.  
   - This JWT must be used in the `Authorization` header to register users.

2. 📝 **User Registration (Admin-Only)**  
   - Admin sends a request to `/api/v1/register` with user details.  
   - The system validates the request using the **admin’s JWT**.  
   - If valid, a new user is saved in the database, and a **JWT-based email verification token** is generated.

3. 📧 **Email Verification**  
   - A verification email is sent to the user with a **JWT link**.  
   - Clicking the link calls `/api/v1/register/confirmToken?token=...`.  
   - This verifies the user account if the token is valid and unexpired.

4. ✅ **User Login (Post Verification Only)**  
   - Only after the user verifies their email, their JWT becomes usable.  
   - Verified users can log in and receive a JWT for accessing protected routes.

5. 🔐 **Role-Based Access**  
   - Admins and users have different roles.  
   - Routes are protected using Spring Security’s `hasRole()` or `hasAuthority()` checks.

---

## 🧰 Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JavaMailSender**
- **JWT (JSON Web Token)**

---

## 📦 API Endpoints

| Method | Endpoint                            | Access       | Description                                |
|--------|-------------------------------------|--------------|--------------------------------------------|
| POST   | `/admin/createAdmin`                | Public       | Create a single admin and receive a JWT     |
| POST   | `/api/v1/register`                  | Admin Only   | Register a new user                         |
| GET    | `/api/v1/register/confirmToken`     | Public       | Confirm user's email via token              |
| POST   | `/login`                            | Verified User Only | Authenticate and receive JWT         |
| GET    | `/admin/*`                          | Admin Only   | Admin-protected endpoints                   |
| GET    | `/user/*`                           | User Only    | Verified user-protected endpoints           |

---

## 🛡️ Security First

- **Strict role-based access control** with JWT.
- Only **admins** can register users.
- JWT tokens include roles (`ADMIN`, `USER`) and are time-bound.
- **Unverified users** are blocked from logging in.
- Email verification is required before JWT becomes usable.

---

## 🚀 Getting Started

```bash
# Clone the repository
git clone https://github.com/AniketDas24/login-service.git

# Navigate into the project
cd login-service

# Run the application
./mvnw spring-boot:run
