package fr.fae.memoria.charona.gateway.libria.infrastructure.api.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record MagazineDetailDto(
        Integer id,
        Integer issue,
        String source,
        String magazine,
        @JsonAlias("total_pages") Integer totalPages,
        String language,
        Integer year,
        @JsonAlias("pdf_url") String pdfUrl,
        @JsonAlias("cover_url") String coverUrl
) {
}