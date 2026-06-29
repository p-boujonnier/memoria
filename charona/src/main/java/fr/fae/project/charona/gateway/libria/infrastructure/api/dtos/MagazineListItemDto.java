package fr.fae.project.charona.gateway.libria.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MagazineListItemDto(
        Integer id,
        Integer issue,
        @JsonAlias("cover_url") String coverUrl
) {}