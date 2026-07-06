package fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.dtos.responses;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ConditionResponse(
        UUID id,
        String index,
        String nameEn,
        String nameFr,
        String[] descEn,
        String[] descFr,
        boolean srdSource,
        boolean locked,
        OffsetDateTime updatedAt
) {}
