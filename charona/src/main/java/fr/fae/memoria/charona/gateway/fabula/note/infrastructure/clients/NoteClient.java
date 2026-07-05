package fr.fae.memoria.charona.gateway.fabula.note.infrastructure.clients;

import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.requests.NoteCreateRequest;
import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.requests.NoteUpdateRequest;
import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.responses.NoteResponse;
import fr.fae.memoria.charona.shared.ServiceResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Component
public class NoteClient {

    private final RestClient restClient;

    public NoteClient(@Qualifier("noteRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public ServiceResponse<List<NoteResponse>> getNotes() {
        return restClient.get()
                .uri("/api/notes")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ServiceResponse<List<NoteResponse>> getNotesByAuthorId(UUID authorId) {
        return restClient.get()
                .uri("/api/notes/author/{authorId}", authorId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ServiceResponse<List<NoteResponse>> getNotesBySubject(String subject) {
        return restClient.get()
                .uri("/api/notes/subject/{subject}", subject)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ServiceResponse<NoteResponse> getNoteById(UUID noteId) {
        return restClient.get()
                .uri("/api/notes/{noteId}", noteId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ServiceResponse<NoteResponse> createNote(NoteCreateRequest request) {
        return restClient.post()
                .uri("/api/notes")
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ServiceResponse<NoteResponse> updateNote(UUID noteId, NoteUpdateRequest request) {
        return restClient.put()
                .uri("/api/notes/{noteId}", noteId)
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ServiceResponse<Void> deleteNote(UUID noteId) {
        return restClient.delete()
                .uri("/api/notes/{noteId}", noteId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}