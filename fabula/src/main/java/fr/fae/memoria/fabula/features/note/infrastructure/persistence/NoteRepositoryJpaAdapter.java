package fr.fae.memoria.fabula.features.note.infrastructure.persistence;

import fr.fae.memoria.fabula.features.note.domain.models.Note;
import fr.fae.memoria.fabula.features.note.domain.repositories.INoteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile({"jpa", "test", "dev"})
@Repository
public class NoteRepositoryJpaAdapter implements INoteRepository {

    private final NoteJpaRepository noteJpaRepository;

    public NoteRepositoryJpaAdapter(NoteJpaRepository noteJpaRepository) {
        this.noteJpaRepository = noteJpaRepository;
    }

    @Override
    public Note save(Note note) {
        return noteJpaRepository.save(note);
    }

    @Override
    public Optional<Note> findById(UUID id) {
        return noteJpaRepository.findById(id);
    }

    @Override
    public List<Note> findAll() {
        return noteJpaRepository.findAll();
    }

    @Override
    public List<Note> findByAuthorId(UUID authorId) {
        return noteJpaRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Note> findBySubject(String subject) {
        return noteJpaRepository.findBySubject(subject);
    }

    @Override
    public void deleteById(UUID id) {
        noteJpaRepository.deleteById(id);
    }
}
