package fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.persistence;

import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.models.Condition;
import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.repositories.IConditionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ConditionRepositoryJpaAdapter implements IConditionRepository {

    private final ConditionJpaRepository conditionJpaRepository;

    public ConditionRepositoryJpaAdapter(ConditionJpaRepository conditionJpaRepository) {
        this.conditionJpaRepository = conditionJpaRepository;
    }

    @Override
    public Condition save(Condition condition) {
        return conditionJpaRepository.save(condition);
    }

    @Override
    public Optional<Condition> findById(UUID id) {
        return conditionJpaRepository.findById(id);
    }

    @Override
    public List<Condition> findAll() {
        return conditionJpaRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        conditionJpaRepository.deleteById(id);
    }
}