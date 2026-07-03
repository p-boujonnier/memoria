package fr.fae.memoria.fabula.features.personage.infrastructure.api.mappers;

import fr.fae.memoria.fabula.features.personage.domain.models.Personage;
import fr.fae.memoria.fabula.features.personage.infrastructure.api.dtos.requests.PersonageRequest;
import fr.fae.memoria.fabula.features.personage.infrastructure.api.dtos.responses.PersonageResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonageMapper {

    default Personage toEntity(PersonageRequest request) {
        return Personage.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .age(request.age())
                .ownerId(request.ownerId())
                .build();
    }

    PersonageResponse toResponse(Personage personage);
}
