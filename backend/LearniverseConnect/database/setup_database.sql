-- Ensure you're logged in as the PostgreSQL superuser (postgres) before running this script
-- Run with the following command: psql -U postgres -f setup_database.sql

-- If service isn't running, start it with:
-- Windows : pg_ctl -D "C:\Program Files\PostgreSQL\16\data" start (adjust path as needed)
-- Mac : brew services start postgresql@16
-- Linux : sudo service postgresql start

-- Step 1: Create the database (if it doesn't already exist)
CREATE DATABASE IF NOT EXISTS learniverse;

-- Step 2: Create a user (if it doesn't exist) with a secure password
DO
$$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'learniverse_user') THEN
        CREATE USER learniverse_user WITH PASSWORD 'password';
    END IF;
END
$$;

-- Step 3: Grant privileges to the user
GRANT ALL PRIVILEGES ON DATABASE learniverse TO learniverse_user;

-- Connect to the database
\c learniverse;

-- Step 4: Create the tables
CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS provider (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    currency VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS course (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    level VARCHAR(50),
    keywords TEXT,
    ects_credits DECIMAL(5,2),
    hours_per_week INT,
    closest_session DATE,
    related_certification VARCHAR(255),
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS course_provider (
    id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    provider_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    FOREIGN KEY (provider_id) REFERENCES provider(id) ON DELETE CASCADE
);