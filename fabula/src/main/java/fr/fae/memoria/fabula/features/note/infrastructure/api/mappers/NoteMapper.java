package fr.fae.memoria.fabula.features.note.infrastructure.api.mappers;

import fr.fae.memoria.fabula.features.note.domain.models.Note;
import fr.fae.memoria.fabula.features.note.infrastructure.api.dtos.requests.NoteRequest;
import fr.fae.memoria.fabula.features.note.infrastructure.api.dtos.responses.NoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    @Mapping(target = "author", ignore = true)
    default Note toEntity(NoteRequest request) {
        return Note.builder()
                .title(request.title())
                .content(request.content())
                .subject(request.subject())
                .build();
    }

    NoteResponse toResponse(Note note);
}
