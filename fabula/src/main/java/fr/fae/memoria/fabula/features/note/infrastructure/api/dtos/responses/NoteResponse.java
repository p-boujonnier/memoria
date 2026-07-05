package fr.fae.memoria.fabula.features.note.infrastructure.api.dtos.responses;

import java.util.UUID;

public record NoteResponse(
        UUID id,
        String title,
        String content,
        UUID authorId,
        String subject
) {}
