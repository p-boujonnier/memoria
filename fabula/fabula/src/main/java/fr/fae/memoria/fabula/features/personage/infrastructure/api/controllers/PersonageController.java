package fr.fae.memoria.fabula.features.personage.infrastructure.api.controllers;

import fr.fae.memoria.fabula.features.personage.domain.services.IPersonageService;
import fr.fae.memoria.fabula.features.personage.infrastructure.api.dtos.requests.PersonageRequest;
import fr.fae.memoria.fabula.features.personage.infrastructure.api.dtos.responses.PersonageResponse;
import fr.fae.memoria.fabula.features.personage.infrastructure.api.mappers.PersonageMapper;
import fr.fae.memoria.fabula.shared.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personages")
public class PersonageController {

    // Injected dependencies
    private final IPersonageService personageService;
    private final PersonageMapper mapper;

    public PersonageController(IPersonageService personageService, PersonageMapper mapper) {
        this.personageService = personageService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<PersonageResponse>>> findAll() {
        return ResponseEntity.ok(
                personageService
                        .findAll()
                        .map(personages -> personages
                                .stream()
                                .map(mapper::toResponse)
                                .toList())
        );
    }

    @GetMapping("owner/{ownerId}")
    public ResponseEntity<ServiceResponse<List<PersonageResponse>>> findByOwnerId(@PathVariable UUID ownerId) {
        return ResponseEntity.ok(
                personageService
                        .findByOwnerId(ownerId)
                        .map(personages -> personages
                                .stream()
                                .map(mapper::toResponse)
                                .toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<PersonageResponse>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                personageService
                        .findById(id)
                        .map(mapper::toResponse)
        );
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<PersonageResponse>> create(@RequestBody PersonageRequest request) {
        return ResponseEntity.ok(
                personageService
                        .create(mapper.toEntity(request))
                        .map(mapper::toResponse)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<PersonageResponse>> update(@PathVariable UUID id, @RequestBody PersonageRequest request) {
        return ResponseEntity.ok(
                personageService
                        .update(id, mapper.toEntity(request))
                        .map(mapper::toResponse)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Boolean>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(
                personageService
                        .delete(id)
        );
    }
}
