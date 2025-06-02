-- Whispr Database - Postgres 14
-- This file contains the SQL commands to create the Whispr database schema.

-- Reset the database to a clean state

DO
$$
DECLARE
    schema_name text := current_schema();
BEGIN
    EXECUTE format('DROP SCHEMA IF EXISTS %I CASCADE', schema_name);
    EXECUTE format('CREATE SCHEMA %I', schema_name);
END;
$$ language 'plpgsql';


-- ------------------------------------------------------
-- Schema creation
-- ------------------------------------------------------

CREATE TABLE accounts (
    id UUID DEFAULT gen_random_uuid(),
    username VARCHAR(30) NOT NULL,
    display_name VARCHAR(50) NOT NULL,
    bio TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_blocked BOOLEAN NOT NULL DEFAULT FALSE,
    preferences TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    CONSTRAINT account_pk PRIMARY KEY (id),
    CONSTRAINT username_format CHECK (username ~ '^[a-z0-9._]+$')
);

CREATE UNIQUE INDEX accounts_username_idx ON accounts (username);


-- ------------------------------------------------------
-- Seed data
-- ------------------------------------------------------

INSERT INTO accounts
    (username, display_name, bio, is_active, is_blocked, preferences)
VALUES
    ('john_doe', 'John Doe', 'Hello, I am John!', TRUE, FALSE, '{"theme": "dark"}'),
    ('jane_smith', 'Jane Smith', 'Welcome to my profile!', TRUE, FALSE, '{"theme": "light"}'),
    ('alice_jones', 'Alice Jones', 'Just a regular user.', TRUE, FALSE, '{"theme": "dark"}'),
    ('bob_brown', 'Bob Brown', 'I love coding!', TRUE, FALSE, '{"theme": "light"}'),
    ('charlie_black', 'Charlie Black', 'Exploring the world of tech.', TRUE, FALSE, '{"theme": "dark"}');
