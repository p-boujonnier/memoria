--liquibase formatted sql

--changeset Fae:001-create-roles-table
CREATE TABLE charona.roles (
    id serial PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    label VARCHAR(50) NOT NULL,
    description TEXT NULL,
    assignable BOOLEAN NOT NULL DEFAULT FALSE,
    askable BOOLEAN NOT NULL DEFAULT FALSE,
    creation_date TIMESTAMP NOT NULL DEFAULT NOW()
);