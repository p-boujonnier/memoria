package fr.fae.memoria.hepha.dd5.mechanics.condition.domain.repositories;

import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.models.Condition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IConditionRepository {
    Condition save(Condition condition);

    Optional<Condition> findById(UUID id);

    Optional<Condition> findByIndex(String index);

    List<Condition> findAll();

    void deleteById(UUID id);
}