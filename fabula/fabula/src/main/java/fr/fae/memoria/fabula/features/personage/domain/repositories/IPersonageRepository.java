package fr.fae.memoria.fabula.features.personage.domain.repositories;

import fr.fae.memoria.fabula.features.personage.domain.models.Personage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPersonageRepository {
    Personage save(Personage personage);

    Optional<Personage> findById(UUID id);

    List<Personage> findAll();

    List<Personage> findByOwnerId(UUID ownerId);

    void deleteById(UUID id);
}
