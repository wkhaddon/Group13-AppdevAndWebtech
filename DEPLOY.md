# Learniverse Deployment Guide

This guide walks you through the steps required to deploy Learniverse — frontend (via GitHub Pages) and backend (on a remote server via SSH).

---

## Frontend (GitHub Pages)

### Deployment Process:
1. **Push to `main` branch**
2. That’s it. GitHub handles the rest.

### Notes:
- The frontend is located in `frontend/learniverse-connect`
- A GitHub Action automatically builds and deploys to GitHub Pages
- Make sure the `homepage` in `package.json` is set correctly:

```
"homepage": "https://<username>.github.io/<repo>/"
```

> Replace `<username>` and `<repo>` as needed.

---

## Backend (Spring Boot on Remote Server)

### Prerequisites
- SSH access to the server
- Java 21 installed
- PostgreSQL set up with a database named `learniverse`
- Environment variables defined in a `.env` file on the server

---

### Deployment Steps

1. **Connect to the server via SSH:**

```
ssh youruser@yourserver
```

2. **Navigate to the project directory:**

```
cd ~/project/backend/LearniverseConnect
```

3. **Pull latest changes:**

```
git pull origin main
```

4. **(Optional) Fix or update the `.env` file if needed**

```
nano .env
```

5. **Run the backend using Gradle:**

```
./gradlew bootRun
```

> Or use `nohup ./gradlew bootRun &` if you want it to run in the background.

---

### Notes:

- Make sure **Java 21** is available and in your `PATH`
- If Flyway fails on migration due to existing tables, you may need to manually clean or adjust the database
- If TLS is configured, remember: your self-signed cert will not be trusted by most browsers (see `SETUP.md`)

---

## TL;DR

| Part | What to do |
|------|------------|
| Frontend | Push to `main`, GitHub deploys automatically |
| Backend | SSH into server → `git pull` → `./gradlew bootRun` |
| Environment | Check or update `.env` on server |
| Java | Ensure you're using **Java 21** |
