# Learniverse Setup Guide

Welcome to the Learniverse project!
Here's everything you need to get the backend and frontend running smoothly on your own machine. Buckle up - it's mostly painless.

---

## Requirements

- **Java 21**
- **PostgreSQL** (with a database named `learniverse`)
- **Node.js** + **npm** (for frontend)
- Optional: **Flyway CLI** (only if you want to run migrations manually)

---

## Backend Setup (Spring Boot)

1. **Clone the repo**

2. **Create a `.env` file** in the backend folder (or copy .env.example)

```bash
cp .env.example .env
```

3. **Create the database**

```sql
CREATE DATABASE learniverse;
```

4. **Ensure your PostgreSQL user has access**

(The username/password should match your `.env` file)

5. **Run the backend**

```bash
./gradlew bootRun
```

> If you're on windows, use `gradlew.bat bootRun` instead

6. **Make sure you have Java 21**

If you're getting weird errors, check your java version with:

```bash
java -version
```


## Frontend Setup (Vite + React)

1. **Navigate to the frontend directory**

```bash
cd frontend/learniverse-connect
```

2. **Install dependencies**

```bash
npm install
```

3. **Start the dev server**

```bash
npm run dev
```

> By default, this runs on `http://localhost:5173/Group13-AppdevAndWebtech/`

## API URL Switching

Right now, the frontend is configured to talk to the backend running on the **university server**. If you want to run everything locally, just update the Axios base URL:

> `src/api/axios.js`
```js
const api = axios.create({
	baseURL: 'https://localhost:8443/api',
	withCredentials: true,
});
```

> The server should update automatically.

## Flyway Notes

If you're using `Flyway CLI` outside of Spring Boot, you'll need to set the environment variables directly in your shell:

```bash
export FLYWAY_DB_URL=jdbc:postgresql://localhost:5432/learniverse
export FLYWAY_DB_USER=postgres
export FLYWAY_DB_PASSWORD=your_password
```

Then you can run:

```bash
./gradlew flywayMigrate
```

## TLS / HTTPS Warning

The backend uses a **self-signed certificate** If you're using Firefox - you'll be fine.

However
- Chrome & Safari (esp. on macOS) will silently block cookies and requests unless you **import and trust** the certificates manually
- For the easiest experience: **Use Firefox**. Seriously. Just do it.

## TL;DR

 **Task**                 | **Command**
--------------------------|-------------------------------------------
Backend dev server        | `./gradlew bootRun`
Frontend dev server       | `npm run dev`
Flyway migrate (optional) | `./gradlew flywayMigrate`
Local DB required         | PostgreSQL, with a DB named `learniverse`
Java version              | `Java 21 required`
Trouble with HTTPS?       | Use Firefox

---

Still confused? Stuck?

> Create an issue at `https://github.com/wkhaddon/Group13-AppdevAndWebtech/issues`, or ask.