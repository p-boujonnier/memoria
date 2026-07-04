package fr.fae.memoria.fabula.features.note.infrastructure.persistence;

import fr.fae.memoria.fabula.features.note.domain.models.Note;
import fr.fae.memoria.fabula.features.note.domain.repositories.INoteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Profile("mock")
@Repository
public class NoteRepositoryMock implements INoteRepository {

    private final Map<UUID, Note> notes = new HashMap<>();

    @Override
    public Note save(Note note) {
        UUID id = note.getId() != null ? note.getId() : UUID.randomUUID();
        notes.put(id, note);
        return note;
    }

    @Override
    public Optional<Note> findById(UUID id) {
        return Optional.ofNullable(notes.get(id));
    }

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(notes.values());
    }

    @Override
    public List<Note> findByAuthorId(UUID authorId) {
        return notes.values().stream()
                .filter(note -> Objects.equals(note.getAuthor().getId(), authorId))
                .toList();
    }

    @Override
    public List<Note> findBySubject(String subject) {
        return notes.values().stream()
                .filter(note -> Objects.equals(note.getSubject(), subject))
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        notes.remove(id);
    }
}