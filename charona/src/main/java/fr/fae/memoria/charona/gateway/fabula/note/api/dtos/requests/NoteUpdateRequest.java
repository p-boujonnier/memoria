package fr.fae.memoria.charona.gateway.fabula.note.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record NoteUpdateRequest(
        @NotBlank @Size(max = 150) String title,
        @NotBlank String content,
        @NotNull UUID authorId,
        @Size(max = 150) String subject
) {}