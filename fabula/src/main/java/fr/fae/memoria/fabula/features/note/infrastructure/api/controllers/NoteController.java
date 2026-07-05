package fr.fae.memoria.fabula.features.note.infrastructure.api.controllers;

import fr.fae.memoria.fabula.features.note.domain.services.INoteService;
import fr.fae.memoria.fabula.features.note.infrastructure.api.dtos.requests.NoteRequest;
import fr.fae.memoria.fabula.features.note.infrastructure.api.dtos.responses.NoteResponse;
import fr.fae.memoria.fabula.features.note.infrastructure.api.mappers.NoteMapper;
import fr.fae.memoria.fabula.shared.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final INoteService noteService;
    private final NoteMapper mapper;

    public NoteController(INoteService noteService, NoteMapper mapper) {
        this.noteService = noteService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<NoteResponse>>> findAll() {
        return ResponseEntity.ok(
                noteService
                        .findAll()
                        .map(notes -> notes
                                .stream()
                                .map(mapper::toResponse)
                                .toList()
                        )
        );
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<ServiceResponse<List<NoteResponse>>> findByAuthorId(@PathVariable UUID authorId) {
        return ResponseEntity.ok(
                noteService
                        .findByAuthorId(authorId)
                        .map(notes -> notes
                                .stream()
                                .map(mapper::toResponse)
                                .toList()
                        )
        );
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<ServiceResponse<List<NoteResponse>>> findBySubject(@PathVariable String subject) {
        return ResponseEntity.ok(
                noteService
                        .findBySubject(subject)
                        .map(notes -> notes
                                .stream()
                                .map(mapper::toResponse)
                                .toList()
                        )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<NoteResponse>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                noteService
                        .findById(id)
                        .map(mapper::toResponse)
        );
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<NoteResponse>> create(@RequestBody NoteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                noteService.create(mapper.toEntity(request), request.authorId())
                        .map(mapper::toResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<NoteResponse>> update(@PathVariable UUID id, @RequestBody NoteRequest request) {
        return ResponseEntity.ok(
                noteService
                        .update(id, mapper.toEntity(request), request.authorId())
                        .map(mapper::toResponse)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Boolean>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(
                noteService
                        .delete(id)
        );
    }

}

