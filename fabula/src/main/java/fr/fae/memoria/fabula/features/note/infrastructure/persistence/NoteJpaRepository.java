package fr.fae.memoria.fabula.features.note.infrastructure.persistence;

import fr.fae.memoria.fabula.features.note.domain.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NoteJpaRepository extends JpaRepository<Note, UUID> {
    List<Note> findByAuthorId(UUID authorId);

    List<Note> findBySubject(String subject);
}
