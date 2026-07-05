package fr.fae.memoria.charona.gateway.fabula.note.services;

import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.requests.NoteCreateRequest;
import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.requests.NoteUpdateRequest;
import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.responses.NoteResponse;
import fr.fae.memoria.charona.gateway.fabula.note.infrastructure.clients.NoteClient;
import fr.fae.memoria.charona.gateway.fabula.personage.api.dtos.responses.PersonageResponse;
import fr.fae.memoria.charona.gateway.fabula.personage.infrastructure.clients.PersonageClient;
import fr.fae.memoria.charona.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteProxyService {

    private final NoteClient noteClient;
    private final PersonageClient personageClient;

    public NoteProxyService(NoteClient noteClient, PersonageClient personageClient) {
        this.noteClient = noteClient;
        this.personageClient = personageClient;
    }

    public ServiceResponse<List<NoteResponse>> getAllNotes() {
        return noteClient.getNotes();
    }

    public ServiceResponse<NoteResponse> getNoteById(UUID requesterId, UUID noteId, boolean isMj) {
        ServiceResponse<NoteResponse> note = noteClient.getNoteById(noteId);
        if (note.getData() == null) {
            return note;
        }

        ServiceResponse<Void> ownershipCheck = checkAuthorOwnership(requesterId, note.getData().authorId(), isMj);
        if (ownershipCheck != null) {
            return new ServiceResponse<>(ownershipCheck.getCode(), ownershipCheck.getMessage(), null);
        }

        return note;
    }

    public ServiceResponse<List<NoteResponse>> getNotesByAuthorId(UUID requesterId, UUID authorId, boolean isMj) {
        ServiceResponse<Void> ownershipCheck = checkAuthorOwnership(requesterId, authorId, isMj);
        if (ownershipCheck != null) {
            return new ServiceResponse<>(ownershipCheck.getCode(), ownershipCheck.getMessage(), null);
        }
        return noteClient.getNotesByAuthorId(authorId);
    }

    public ServiceResponse<List<NoteResponse>> getNotesBySubject(String subject) {
        return noteClient.getNotesBySubject(subject);
    }

    public ServiceResponse<NoteResponse> createNote(UUID requesterId, NoteCreateRequest request, boolean isMj) {
        ServiceResponse<Void> ownershipCheck = checkAuthorOwnership(requesterId, request.authorId(), isMj);
        if (ownershipCheck != null) {
            return new ServiceResponse<>(ownershipCheck.getCode(), ownershipCheck.getMessage(), null);
        }
        return noteClient.createNote(request);
    }

    public ServiceResponse<NoteResponse> updateNote(UUID requesterId, UUID noteId, NoteUpdateRequest request, boolean isMj) {
        ServiceResponse<Void> ownershipCheck = checkAuthorOwnership(requesterId, request.authorId(), isMj);
        if (ownershipCheck != null) {
            return new ServiceResponse<>(ownershipCheck.getCode(), ownershipCheck.getMessage(), null);
        }
        return noteClient.updateNote(noteId, request);
    }

    public ServiceResponse<Void> deleteNote(UUID requesterId, UUID noteId, boolean isMj) {
        ServiceResponse<NoteResponse> note = noteClient.getNoteById(noteId);
        if (note.getData() == null) {
            return new ServiceResponse<>(note.getCode(), note.getMessage(), null);
        }

        ServiceResponse<Void> ownershipCheck = checkAuthorOwnership(requesterId, note.getData().authorId(), isMj);
        if (ownershipCheck != null) {
            return ownershipCheck;
        }

        return noteClient.deleteNote(noteId);
    }

    private ServiceResponse<Void> checkAuthorOwnership(UUID requesterId, UUID authorId, boolean isMj) {
        if (isMj) {
            return null;
        }

        ServiceResponse<PersonageResponse> personage = personageClient.getById(authorId);
        if (personage.getData() == null) {
            return new ServiceResponse<>("404", "Personnage auteur introuvable", null);
        }
        if (!personage.getData().ownerId().equals(requesterId)) {
            return new ServiceResponse<>("403", "Ce personnage ne vous appartient pas", null);
        }
        return null;
    }
}