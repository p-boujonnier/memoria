package fr.fae.memoria.fabula.features.personage.domain.services;

import fr.fae.memoria.fabula.features.personage.domain.models.Personage;
import fr.fae.memoria.fabula.shared.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface IPersonageService {
    ServiceResponse<Personage> create(Personage personage);

    ServiceResponse<Personage> findById(UUID id);

    ServiceResponse<List<Personage>> findAll();

    ServiceResponse<List<Personage>> findByOwnerId(UUID ownerId);

    ServiceResponse<Boolean> delete(UUID id);

    ServiceResponse<Personage> update(UUID id, Personage entity);
}
