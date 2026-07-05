package fr.fae.memoria.charona.gateway.fabula.personage.api.controllers;

import fr.fae.memoria.charona.gateway.fabula.personage.api.dtos.requests.PersonageRequest;
import fr.fae.memoria.charona.gateway.fabula.personage.api.dtos.responses.PersonageResponse;
import fr.fae.memoria.charona.gateway.fabula.personage.services.PersonageProxyService;
import fr.fae.memoria.charona.shared.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fabula/personages")
public class PersonageController {

    private final PersonageProxyService proxyService;

    public PersonageController(PersonageProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<List<PersonageResponse>>> findMine(
            Authentication authentication
    ) {
        UUID ownerId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(proxyService.findByOwnerId(ownerId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<PersonageResponse>> findById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(proxyService.findById(id));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<PersonageResponse>> create(
            @Valid @RequestBody PersonageRequest request,
            Authentication authentication
    ) {
        UUID ownerId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(proxyService.create(ownerId, request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<PersonageResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody PersonageRequest request
    ) {
        return ResponseEntity.ok(proxyService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ServiceResponse<Boolean>> delete(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(proxyService.delete(id));
    }
}
