package fr.fae.memoria.charona.gateway.fabula.api.dtos.responses;

import java.util.UUID;

public record PersonageResponse(
   UUID id,
   String firstName,
   String lastName,
   Integer age,
   UUID ownerId
) {}
