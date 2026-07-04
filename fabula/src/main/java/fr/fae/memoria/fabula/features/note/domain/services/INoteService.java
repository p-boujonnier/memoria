package fr.fae.memoria.fabula.features.note.domain.services;

import fr.fae.memoria.fabula.features.note.domain.models.Note;
import fr.fae.memoria.fabula.shared.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface INoteService {
    ServiceResponse<Note> create(Note note);

    ServiceResponse<Note> findById(UUID id);

    ServiceResponse<List<Note>> findAll();

    ServiceResponse<List<Note>> findByAuthorId(UUID authorId);

    ServiceResponse<List<Note>> findBySubject(String subject);

    ServiceResponse<Note> update(UUID id, Note entity);

    ServiceResponse<Boolean> delete(UUID id);
}
