package fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.mappers;

import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.models.Condition;
import fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.dtos.requests.ConditionRequest;
import fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.dtos.responses.ConditionResponse;
import fr.fae.memoria.hepha.shared.EntitySummary;
import org.mapstruct.Mapper;

@Mapper
public interface ConditionMapper {

    default Condition toEntity(ConditionRequest conditionRequest) {
        if (conditionRequest == null) {
            return null;
        }
        return Condition.builder()
                .index(conditionRequest.index())
                .nameEn(conditionRequest.nameEn())
                .nameFr(conditionRequest.nameFr())
                .descEn(conditionRequest.descEn())
                .descFr(conditionRequest.descFr())
                .srdSource(false)
                .locked(false)
                .build();
    }

    ConditionResponse toResponse(Condition condition);

    EntitySummary toSummary(Condition condition);
}