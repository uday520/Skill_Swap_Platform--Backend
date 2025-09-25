# 🧑‍🤝‍🧑 SkillSwap – Peer-to-Peer Learning Platform

SkillSwap is a **Spring Boot backend application** where users can **teach skills they know** and **learn skills they want** from peers.  
It’s a community-driven platform to connect, share knowledge, and grow together.

---

## 🚀 Features

* 🔐 User Authentication (JWT)  
* 📚 Skill Management (add/update)  
* 🔎 Search Users by Skills  
* 🤝 Peer-to-Peer Skill Exchange  
* 🗂️ REST APIs for integration  
* 📅 Schedule learning sessions  
* ⭐ Reviews & Ratings for peers

---

## 🛠 Tech Stack

* **Backend:** Spring Boot, Spring Data JPA  
* **Database:** PostgreSQL  
* **Security:** Spring Security + JWT  
* **Build Tool:** Maven  
* **Testing:** Postman  

---

## ⚡ API Endpoints

### 🔑 Authentication

* `POST /api/auth/register` → Register a new user  
* `POST /api/auth/login` → Login & get JWT token  
* `POST /api/auth/logout` → Logout user (invalidate token/session)  

### 👤 Users

* `GET /api/users/{id}` → Get user by ID  
* `GET /api/users` → Get all users  
* `GET /api/users/search?skill=Java` → Find users by skill  
* `PUT /api/users/{id}` → Update user profile  
* `DELETE /api/users/{id}` → Delete a user  

### 🎯 Skills

* `GET /api/skills` → List all skills  
* `GET /api/skills/{id}` → Get skill by ID  
* `POST /api/skills` → Create a new skill  
* `PUT /api/skills/add-to-user/{userId}` → Add skills to a user  
* `DELETE /api/skills/remove-from-user/{userId}/{skillId}` → Remove a skill from a user  

### 📅 Sessions / Peer-to-Peer Matching

* `POST /api/sessions` → Schedule a learning session  
* `GET /api/sessions/{id}` → Get session details  
* `GET /api/sessions/user/{userId}` → Get all sessions for a user  
* `PUT /api/sessions/{id}` → Update session (time, status)  
* `DELETE /api/sessions/{id}` → Cancel a session  
* `GET /api/match?skill=Python` → Find peer-to-peer matches by skill  

### ⭐ Reviews

* `POST /api/reviews` → Add a review for a peer  
* `GET /api/reviews/user/{userId}` → Get all reviews for a user  
* `GET /api/reviews/{id}` → Get review by ID  

---

## 💻 Getting Started

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/SkillSwap.git
cd SkillSwap
````

### 2️⃣ Configure Database

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/PeertoPeer
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

### 3️⃣ Run Project

```bash
mvn spring-boot:run
```

Visit 👉 `http://localhost:8080`

---

## 📌 Sample JSON

### Register User

```json
{
  "username": "aaravk",
  "email": "aarav.kapoor@example.com",
  "password": "Pass@123",
  "name": "Aarav Kapoor",
  "bio": "Full-stack dev teaching Java/Spring Boot",
  "location": "Mumbai",
  "skillsCanTeach": ["Java", "Spring Boot"],
  "skillsWantLearn": ["React", "Docker"]
}
```

### Add Skills to User

```json
{
  "skills": ["Python", "Machine Learning"]
}
```

### Schedule Session

```json
{
  "teacherId": 1,
  "learnerId": 2,
  "skill": "Java",
  "sessionTime": "2025-09-28T15:00:00",
  "mode": "Online"
}
```

### Add Review

```json
{
  "reviewerId": 2,
  "revieweeId": 1,
  "rating": 5,
  "comment": "Great mentor, explained concepts clearly!"
}
```

---

## 🏗 Future Enhancements

* 🌍 Real-time chat between learners & teachers
* 📅 Automated session reminders
* 🎨 Improved frontend (React/Angular)
* ⭐ Advanced rating & recommendation system
