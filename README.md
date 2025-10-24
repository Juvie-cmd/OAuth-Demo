# 📘 OAuth Demo

A full-stack OAuth2 demo project with **Spring Boot (Java 21)** backend and **React + Vite** frontend.  
Implements secure OAuth2 login with **Google** and **GitHub**.

---

## 🧩 Tech Stack

### 🖥️ Backend – `OAuth2Demo/`
- Java 21  
- Spring Boot 3.3  
- Spring Security  
- Spring OAuth2 Client  
- Spring Data JPA  
- H2 Database (in-memory)  

### 💻 Frontend – `frontend/`
- React JS  
- Vite  

---

## 🚀 Features

- OAuth2 authentication using Google and GitHub  
- In-memory H2 database for development  

---

## 🛠️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Juvie-cmd/OAuth-Demo.git
cd OAuth-Demo
```

---

## ⚙️ Backend Setup (`OAuth2Demo`)

### 📁 Navigate to the backend folder
```bash
cd OAuth2Demo
```

### 🧰 Prerequisites
- [Java 21](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- (Optional) IDE like IntelliJ IDEA or Eclipse

### ▶️ Run the Backend
Using Maven:
```bash
mvn spring-boot:run
```

Or build and run the JAR:
```bash
mvn clean package
java -jar target/*.jar
```

Backend runs at:  
👉 **http://localhost:8080**

---

## ▶️ Frontend Setup (`frontend`)

### 📁 Navigate to the frontend folder
```bash
cd frontend
```

### 📦 Install Dependencies
```bash
npm install
```

### ▶️ Run the Frontend
```bash
npm start
```

Frontend runs at:  
👉 **http://localhost:3000**

---

## 🔗 OAuth Configuration

Update your backend `application.yml` or `application.properties` file in `OAuth2Demo/src/main/resources/` with your OAuth credentials:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_GOOGLE_CLIENT_ID
            client-secret: YOUR_GOOGLE_CLIENT_SECRET
          github:
            client-id: YOUR_GITHUB_CLIENT_ID
            client-secret: YOUR_GITHUB_CLIENT_SECRET
```

---
