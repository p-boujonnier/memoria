package fr.fae.memoria.hepha.shared;

import java.util.UUID;

public record EntitySummary (
        UUID id,
        String index,
        String nameEn,
        String nameFr
) {}