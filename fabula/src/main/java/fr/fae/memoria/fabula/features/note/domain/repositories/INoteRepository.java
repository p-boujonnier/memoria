package fr.fae.memoria.fabula.features.note.domain.repositories;

import fr.fae.memoria.fabula.features.note.domain.models.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface INoteRepository {

    Note save(Note note);

    Optional<Note> findById(UUID id);

    List<Note> findAll();

    List<Note> findByAuthorId(UUID authorId);

    List<Note> findBySubject(String subject);

    void deleteById(UUID id);

}
