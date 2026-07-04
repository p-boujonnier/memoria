--liquibase formatted sql

--changeset Fae:001-create-notes-table
CREATE TABLE fabula.notes (
                              id uuid PRIMARY KEY,
                              title VARCHAR(150) NOT NULL,
                              content TEXT NOT NULL,
                              author_id uuid NOT NULL,
                              subject VARCHAR(150),
                              created_at TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP NOT NULL,
                              CONSTRAINT fk_note_author FOREIGN KEY (author_id) REFERENCES fabula.personages (id) ON DELETE CASCADE
);