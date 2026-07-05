package fr.fae.memoria.fabula.features.note.application.services;

import fr.fae.memoria.fabula.features.note.domain.models.Note;
import fr.fae.memoria.fabula.features.note.domain.repositories.INoteRepository;
import fr.fae.memoria.fabula.features.note.domain.services.INoteService;
import fr.fae.memoria.fabula.features.personage.domain.models.Personage;
import fr.fae.memoria.fabula.features.personage.domain.repositories.IPersonageRepository;
import fr.fae.memoria.fabula.features.personage.domain.services.IPersonageService;
import fr.fae.memoria.fabula.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements INoteService {

    private final INoteRepository noteRepository;
    private final IPersonageRepository personageRepository;

    public NoteServiceImpl(
            INoteRepository noteRepository,
            IPersonageRepository personageRepository
    ) {
        this.noteRepository = noteRepository;
        this.personageRepository = personageRepository;
    }

    @Override
    public ServiceResponse<Note> create(Note note, UUID authorId) {
        try {
            Personage author = personageRepository.findById(authorId).orElse(null);
            if (author == null) {
                return new ServiceResponse<>("404", "Personnage auteur introuvable", null);
            }

            note.setAuthor(author);

            return new ServiceResponse<>("201", "Note créée avec succès", noteRepository.save(note));
        }
        catch (Exception e) {
            return new ServiceResponse<>("500", "Erreur lors de la création de la note", null);
        }
    }

    @Override
    public ServiceResponse<Note> update(UUID id, Note entity, UUID authorId) {
        try {
            Note note = noteRepository.findById(id).orElse(null);
            if (note == null) {
                return new ServiceResponse<>("404", "Note introuvable", null);
            }

            Personage author = personageRepository.findById(authorId).orElse(null);
            if (author == null) {
                return new ServiceResponse<>("404", "Personnage auteur introuvable", null);
            }

            note.setAuthor(author);
            note.setSubject(entity.getSubject());
            note.setTitle(entity.getTitle());
            note.setContent(entity.getContent());

            return new ServiceResponse<>("200", "Note mise à jour avec succès", noteRepository.save(note));
        }
        catch (Exception e) {
            return new ServiceResponse<>("500", "Erreur lors de la mise à jour de la note", null);
        }
    }

    @Override
    public ServiceResponse<Note> findById(UUID id) {
        try {
            return noteRepository.findById(id)
                    .map(note -> new ServiceResponse<>("200", "Note trouvée avec succès", note))
                    .orElse(new ServiceResponse<>("404", "Note introuvable", null));
        }
        catch (Exception e) {
            return new ServiceResponse<>("500", "Erreur lors de la recherche de la note", null);
        }
    }

    @Override
    public ServiceResponse<List<Note>> findAll() {
        try {
            return new ServiceResponse<>("200", "Notes trouvées avec succès", noteRepository.findAll());
        }
        catch (Exception e) {
            return new ServiceResponse<>("500", "Erreur lors de la recherche des notes", null);
        }
    }

    @Override
    public ServiceResponse<List<Note>> findByAuthorId(UUID authorId) {
        try {
            return new ServiceResponse<>("200", "Notes trouvées avec succès", noteRepository.findByAuthorId(authorId));
        }
        catch (Exception e) {
            return new ServiceResponse<>("500", "Erreur lors de la recherche des notes", null);
        }
    }

    @Override
    public ServiceResponse<List<Note>> findBySubject(String subject) {
        try {
            return new ServiceResponse<>("200", "Notes trouvées avec succès", noteRepository.findBySubject(subject));
        }
        catch (Exception e) {
            return new ServiceResponse<>("500", "Erreur lors de la recherche des notes", null);
        }
    }

    @Override
    public ServiceResponse<Boolean> delete(UUID id) {
        try {
            if (noteRepository.findById(id).isEmpty()) {
                return new ServiceResponse<>("404", "Note introuvable", null);
            }
            noteRepository.deleteById(id);
            return new ServiceResponse<>("200", "Note supprimée avec succès", true);
        }
        catch (Exception e) {
            return new ServiceResponse<>("500", "Erreur lors de la suppression de la note", null);
        }
    }
}
