package fr.fae.memoria.charona.gateway.fabula.api.dtos.requests;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record PersonageRequest(
        @NotBlank @Size(min = 2, max = 30) String firstName,
        @NotBlank @Size(min = 2, max = 30) String lastName,
        @NotNull @Min(0) @Max(2000) Integer age,
        UUID ownerId
) {}
