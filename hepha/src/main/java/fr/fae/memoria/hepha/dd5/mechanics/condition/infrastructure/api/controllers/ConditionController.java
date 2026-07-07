package fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.controllers;

import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.services.IConditionService;
import fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.dtos.requests.ConditionRequest;
import fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.dtos.responses.ConditionResponse;
import fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.mappers.ConditionMapper;
import fr.fae.memoria.hepha.shared.EntitySummary;
import fr.fae.memoria.hepha.shared.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dd5/conditions")
public class ConditionController {

    IConditionService conditionService;
    ConditionMapper mapper;

    public ConditionController(IConditionService conditionService, ConditionMapper mapper) {
        this.conditionService = conditionService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<EntitySummary>>> getConditions() {
        return ResponseEntity.status(200).body(
                conditionService.findAll()
                        .map(conditions -> conditions
                                .stream()
                                .map(mapper::toSummary)
                                .toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<ConditionResponse>> getConditionById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.status(200).body(
                conditionService.findById(id).map(mapper::toResponse)
        );
    }



    @PostMapping
    public ResponseEntity<ServiceResponse<ConditionResponse>> createCondition(
            @RequestBody ConditionRequest request
    ) {
        return ResponseEntity.status(200).body(
                conditionService.create(mapper.toEntity(request)).map(mapper::toResponse)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<ConditionResponse>> updateCondition(
            @PathVariable UUID id,
            @RequestBody ConditionRequest request
    ) {
        return ResponseEntity.status(200).body(
                conditionService.update(id, mapper.toEntity(request)).map(mapper::toResponse)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteCondition(
            @PathVariable UUID id
    ) {
        return ResponseEntity.status(200).body(
                conditionService.deleteById(id)
        );
    }

}