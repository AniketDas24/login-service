# 💪 Login Service – Gym Progress Tracker

Welcome to the **Login Service** for the **Gym Progress Tracker** app – a secure and scalable authentication microservice that ensures your users are verified before they flex their data!

## 📽️ Working Demo

🎥 [Click here to watch the demo](https://drive.google.com/file/d/1x84ZVGFYbSIgCZcN3p8RinCNhLXIdVyB/view?usp=sharing)

---
## 🔐 What It Does

This service handles **user registration and verification** via **email confirmation**, enforcing security before users are allowed to log in. Here's how it works:

1. 📝 **User Registration**  
   - Users call the `/api/v1/register` API with their details.  
   - Data is saved in a **PostgreSQL** database.  
   - A **unique verification token** is generated for the user.

2. 📧 **Email Verification**  
   - An email is sent to the user with a **confirmation link** containing the token.  
   - The token has a **limited validity period** to enhance security.

3. ✅ **Verification Flow**  
   - When the user clicks the confirmation link (`/api/v1/register/confirmToken?token=...`), their account is **activated**.  
   - If the token is **expired or invalid**, verification fails and the user is prompted to re-register.

4. 🔐 **Login Enforcement**  
   - Only **verified users** can successfully log in.  
   - This ensures that fake or inactive accounts don’t make it past the front door.

---

## 🧰 Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **PostgreSQL**
- **Spring Data JPA**
- **JavaMail Sender**

---

## 📦 API Endpoints

| Method | Endpoint                            | Description                      |
|--------|-------------------------------------|----------------------------------|
| POST   | `/api/v1/register`                  | Register a new user              |
| GET    | `/api/v1/register/confirmToken`     | Confirm email verification token |

---


# 🛡️ Security First

- All tokens are **time-bound** to prevent misuse.
- **Unverified users** are blocked from logging in.
- Emails use **TLS-encrypted SMTP**.

# 🚀 Getting Started

```bash
# Clone the repository

git clone **https://github.com/AniketDas24/login-service.git**

# Navigate into the project
cd login-service

# Run the application
./mvnw spring-boot:run
```

# 📌 Future Improvements

- Password reset flow
- JWT-based login session handling
- Rate limiting on registration attempts
- Logging and monitoring integration

