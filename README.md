# Learniverse

Welcome to **Learniverse** – a modern web platform for discovering, organizing, and enrolling in online courses across a wide range of categories. This project is built with a focus on modularity, security, and scalability.

---

## 📚 Overview

Learniverse allows users to:

- Browse and favorite courses
- Join provider organizations (with approval workflows)
- Manage purchases and enrollments
- Administer course offerings via provider roles
- Support multi-currency organizations and access control

Backend is powered by **Java + Spring Boot**, and the frontend is built with **React + Vite**. PostgreSQL is used as the primary database, and Flyway handles schema migrations.

---

## 🔧 Getting Started

To get up and running, follow the step-by-step instructions in [`SETUP.md`](./SETUP.md), which includes:

- Environment variable configuration
- Running backend and frontend locally
- Database setup and seeding
- API docs via Swagger/OpenAPI

Example snippet to start the backend:

```
./gradlew bootRun
```

And for the frontend:

```
npm install
npm run dev
```

---

## 🧠 Architecture

The project has a modular monorepo structure, separating concerns across core features:

- `/backend`: Spring Boot app with JWT auth, Flyway migrations, and role-based access control
- `/frontend`: Vite-based React app using SCSS modules and Axios for API integration

For a detailed breakdown of the application architecture and data model, see [`DESIGN.md`](./DESIGN.md).

---

## 🚀 Deployment

Deployment instructions for production (including SSH steps, env config, and manual backend redeployment) are in [`DEPLOY.md`](./DEPLOY.md).

This guide covers:

- Pushing to `main` for frontend auto-deployment
- Pulling and restarting backend via SSH
- Resetting or migrating the database
- Server environment sanity checks

---

## 🔒 Security

- JWT-based authentication for all protected endpoints
- Global and organization-level role checks enforced on backend
- Backend is the source of truth – never trusts the frontend
- Sensitive operations require elevated roles (`SUPPORT`, `ADMIN`, etc.)

---

## 🛠️ Tech Stack

- **Backend**: Java 21, Spring Boot, PostgreSQL, Flyway
- **Frontend**: React, Vite, SCSS Modules
- **Auth**: JWT + Role-based access control
- **Docs**: OpenAPI 3.0 (Swagger)

---

## 🧪 Testing & Validation

While tests are still in progress, backend supports:

- Integration testing via Spring’s `@WebMvcTest`
- Swagger UI validation for all endpoints
- Flyway ensures schema consistency on startup

---

## 📄 Documentation

- [`SETUP.md`](./SETUP.md): How to install, configure, and run the project
- [`DESIGN.md`](./DESIGN.md): Database schema, API design, and app architecture
- [`DEPLOY.md`](./DEPLOY.md): Production setup, deployment, and update flow

---

## 💬 Contributing

Feel free to fork and experiment. Feature ideas or improvements? Open an issue or PR.
