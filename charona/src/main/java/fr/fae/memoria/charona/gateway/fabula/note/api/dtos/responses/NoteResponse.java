package fr.fae.memoria.charona.gateway.fabula.note.api.dtos.responses;

import java.util.UUID;

public record NoteResponse(
        UUID id,
        String title,
        String content,
        UUID authorId,
        String subject
) {}
