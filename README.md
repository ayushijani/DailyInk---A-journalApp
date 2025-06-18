# 📝 DailyInk – Journal App (Backend)

A simple Spring Boot backend project for managing journal entries with MongoDB, basic authentication, and CRUD operations.

> 📚 This is a learning project based on the **Engineering Digest** YouTube tutorial series.

---

## 🚀 What This Project Includes

- Spring Boot REST API setup  
- MongoDB Atlas integration  
- CRUD operations for journal entries  
- User entity with authentication  
- Spring Security configuration  
- Basic unit testing using JUnit & Mockito  
- Logging using Logback and SLF4J  

---

## 📁 API Features

- `POST /register` – Register a new user  
- `POST /login` – Authenticate user and get JWT  
- `GET /journals` – Get all journal entries (authenticated)  
- `POST /journals` – Add a new journal entry  
- `PUT /journals/{id}` – Update an existing journal  
- `DELETE /journals/{id}` – Delete a journal

---

## 🧰 Tech Stack

- Java  
- Spring Boot  
- MongoDB Atlas  
- Spring Security (Basic Auth)  
- JUnit & Mockito  
- Logback & SLF4J  

---

## 🛠️ How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/ayushijani/DailyInk---A-journalApp.git
   cd DailyInk---A-journalApp
