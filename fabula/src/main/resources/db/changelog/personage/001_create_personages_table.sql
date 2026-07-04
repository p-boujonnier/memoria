--liquibase formatted sql

--changeset Fae:001-create-personages-table
CREATE TABLE fabula.personages (
                                   id uuid PRIMARY KEY,
                                   first_name VARCHAR(50) NOT NULL,
                                   last_name VARCHAR(50) NOT NULL,
                                   age INTEGER,
                                   owner_id uuid NOT NULL
);