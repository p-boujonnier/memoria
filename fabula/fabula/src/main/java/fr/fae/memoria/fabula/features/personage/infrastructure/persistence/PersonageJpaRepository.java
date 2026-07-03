package fr.fae.memoria.fabula.features.personage.infrastructure.persistence;

import fr.fae.memoria.fabula.features.personage.domain.models.Personage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonageJpaRepository extends JpaRepository<Personage, UUID> {
    List<Personage> findByOwnerId(UUID ownerId);
}
