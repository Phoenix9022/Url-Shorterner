# 🚀 SleekLink URL Shortener

A modern, visually stunning, and lightning-fast URL shortener built with **Java** and **Spring Boot**.

## ✨ Features
- **Beautiful UI:** Custom glassmorphism design with animated elements (no external CSS libraries).
- **Instant Redirects:** Fast and efficient URL resolution.
- **Auto-generated Hashes:** Generates unique 8-character identifiers automatically.
- **Production Ready:** Pre-configured with a `Dockerfile` and PostgreSQL support for easy cloud deployment (e.g., Render, Railway).

## 🛠️ Tech Stack
- **Backend:** Java 21, Spring Boot 3
- **Database:** H2 (Local Development) / PostgreSQL (Production)
- **Frontend:** Vanilla HTML, CSS, JavaScript
- **Deployment:** Docker

## 🚀 Running Locally

### Prerequisites
- Java 21 or higher installed on your machine.

### Instructions
1. Clone the repository to your local machine.
2. Open your terminal in the project directory.
3. Start the application using the Maven wrapper:
   - On Windows: `.\mvnw.cmd spring-boot:run`
   - On Mac/Linux: `./mvnw spring-boot:run`
4. Open your browser and navigate to `http://localhost:8080`.

## ☁️ Deployment (Render)
This project is configured to be deployed easily on cloud platforms like Render.
1. Connect this GitHub repository to Render.
2. Render will automatically detect the `Dockerfile`.
3. Set up a PostgreSQL database on Render.
4. Add the following environment variables to your Web Service:
   - `SPRING_DATASOURCE_URL` (Format: `jdbc:postgresql://<host>/<dbname>`)
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`
5. Deploy!
