package fr.fae.memoria.hepha.dd5.mechanics.condition.domain.services;

import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.models.Condition;
import fr.fae.memoria.hepha.shared.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface IConditionService {
    ServiceResponse<Condition> create(Condition condition);
    ServiceResponse<Condition> update(UUID id, Condition condition);
    ServiceResponse<Condition> findById(UUID id);
    ServiceResponse<List<Condition>> findAll();
    ServiceResponse<Void> deleteById(UUID id);
}