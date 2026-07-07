package fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.persistence;

import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.models.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConditionJpaRepository extends JpaRepository<Condition, UUID> {
}