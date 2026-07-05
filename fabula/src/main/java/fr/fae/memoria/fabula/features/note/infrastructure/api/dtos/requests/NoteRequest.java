package fr.fae.memoria.fabula.features.note.infrastructure.api.dtos.requests;

import java.time.OffsetDateTime;
import java.util.UUID;

public record NoteRequest(
        String title,
        String content,
        UUID authorId,
        String subject
) {}
