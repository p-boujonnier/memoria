package fr.fae.memoria.hepha.dd5.mechanics.condition.application.services;

import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.models.Condition;
import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.repositories.IConditionRepository;
import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.services.IConditionService;
import fr.fae.memoria.hepha.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConditionServiceImpl implements IConditionService {

    private final IConditionRepository conditionRepository;

    public ConditionServiceImpl(IConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public ServiceResponse<Condition> create(Condition condition) {
        try {
            return new ServiceResponse<>("201", "Condition créé avec succès", conditionRepository.save(condition));
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la création de la condition", null);
        }
    }

    @Override
    public ServiceResponse<Condition> update(UUID id, Condition condition) {
        try {
            return conditionRepository.findById(id)
                    .map(existing -> {
                        existing.setIndex(condition.getIndex());
                        existing.setNameEn(condition.getNameEn());
                        existing.setNameFr(condition.getNameFr());
                        existing.setDescEn(condition.getDescEn());
                        existing.setDescFr(condition.getDescFr());
                        return new ServiceResponse<>("200", "Condition mise à jour avec succès", conditionRepository.save(existing));
                    })
                    .orElse(new ServiceResponse<>("404", "Condition introuvable", null));
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la mise à jour de la condition", null);
        }
    }

    @Override
    public ServiceResponse<Condition> findById(UUID id) {
        try {
            return conditionRepository.findById(id)
                    .map(condition -> new ServiceResponse<>("200", "Condition trouvée avec succès", condition))
                    .orElse(new ServiceResponse<>("404", "Condition introuvable", null));
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la recherche de la condition", null);
        }
    }

    @Override
    public ServiceResponse<List<Condition>> findAll() {
        try {
            return new ServiceResponse<>("200", "Conditions trouvées avec succès", conditionRepository.findAll());
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la recherche des conditions", null);
        }
    }

    @Override
    public ServiceResponse<Void> deleteById(UUID id) {
        try {
            conditionRepository.deleteById(id);
            return new ServiceResponse<>("200", "Condition supprimée avec succès", null);
        } catch (Exception e) {
            return new ServiceResponse<>("500", "Une erreur est survenue lors de la suppression de la condition", null);
        }
    }
}