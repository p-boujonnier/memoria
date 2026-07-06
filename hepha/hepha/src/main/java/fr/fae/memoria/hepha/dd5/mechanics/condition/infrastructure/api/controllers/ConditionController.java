package fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.controllers;

import fr.fae.memoria.hepha.dd5.mechanics.condition.domain.services.IConditionService;
import fr.fae.memoria.hepha.dd5.mechanics.condition.infrastructure.api.mappers.ConditionMapper;
import fr.fae.memoria.hepha.shared.EntitySummary;
import fr.fae.memoria.hepha.shared.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ServiceResponse<EntitySummary>> getConditionById(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.status(200).body(
                conditionService.findById(id).map(mapper::toSummary)
        );
    }


}