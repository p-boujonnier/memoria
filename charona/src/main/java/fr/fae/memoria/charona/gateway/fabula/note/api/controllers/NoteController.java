package fr.fae.memoria.charona.gateway.fabula.note.api.controllers;

import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.requests.NoteCreateRequest;
import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.requests.NoteUpdateRequest;
import fr.fae.memoria.charona.gateway.fabula.note.api.dtos.responses.NoteResponse;
import fr.fae.memoria.charona.gateway.fabula.note.services.NoteProxyService;
import fr.fae.memoria.charona.shared.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fabula/notes")
public class NoteController {

    private static final String ROLE_MJ = "ROLE_MJ";

    private final NoteProxyService proxyService;

    public NoteController(NoteProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceResponse<List<NoteResponse>>> getAllNotes() {
        return ResponseEntity.ok(proxyService.getAllNotes());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<NoteResponse>> getNoteById(
            Authentication authentication,
            @PathVariable UUID id
    ) {
        UUID requesterId = extractRequesterId(authentication);
        boolean isMj = hasRole(authentication, ROLE_MJ);
        return ResponseEntity.ok(proxyService.getNoteById(requesterId, id, isMj));
    }

    @GetMapping("/author/{authorId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<List<NoteResponse>>> getNotesByAuthorId(
            Authentication authentication,
            @PathVariable UUID authorId
    ) {
        UUID requesterId = extractRequesterId(authentication);
        boolean isMj = hasRole(authentication, ROLE_MJ);
        return ResponseEntity.ok(proxyService.getNotesByAuthorId(requesterId, authorId, isMj));
    }

    @GetMapping("/subject/{subject}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<List<NoteResponse>>> getNotesBySubject(@PathVariable String subject) {
        return ResponseEntity.ok(proxyService.getNotesBySubject(subject));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<NoteResponse>> createNote(
            Authentication authentication,
            @Valid @RequestBody NoteCreateRequest request
    ) {
        UUID requesterId = extractRequesterId(authentication);
        boolean isMj = hasRole(authentication, ROLE_MJ);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(proxyService.createNote(requesterId, request, isMj));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<NoteResponse>> updateNote(
            Authentication authentication,
            @PathVariable UUID id,
            @Valid @RequestBody NoteUpdateRequest request
    ) {
        UUID requesterId = extractRequesterId(authentication);
        boolean isMj = hasRole(authentication, ROLE_MJ);
        return ResponseEntity.ok(proxyService.updateNote(requesterId, id, request, isMj));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<Void>> deleteNote(
            Authentication authentication,
            @PathVariable UUID id
    ) {
        UUID requesterId = extractRequesterId(authentication);
        boolean isMj = hasRole(authentication, ROLE_MJ);
        return ResponseEntity.ok(proxyService.deleteNote(requesterId, id, isMj));
    }

    private UUID extractRequesterId(Authentication authentication) {
        return UUID.fromString(authentication.getName());
    }

    private boolean hasRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }
}