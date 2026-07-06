package fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ConditionRequest(
        @NotBlank(message = "L'index est obligatoire")
        String index,

        @NotBlank(message = "Le nom anglais est obligatoire")
        String nameEn,

        @NotBlank(message = "Le nom français est obligatoire")
        String nameFr,

        @Size(max = 20, message = "Maximum 20 descriptions en anglais")
        String[] descEn,

        @Size(max = 20, message = "Maximum 20 descriptions en français")
        String[] descFr
) {}
