package fr.fae.memoria.charona.gateway.fabula.services;

import fr.fae.memoria.charona.gateway.fabula.api.dtos.requests.PersonageRequest;
import fr.fae.memoria.charona.gateway.fabula.api.dtos.responses.PersonageResponse;
import fr.fae.memoria.charona.gateway.fabula.infrastructure.clients.FabulaClient;
import fr.fae.memoria.charona.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonageProxyService {

    private final FabulaClient fabulaClient;

    public PersonageProxyService(FabulaClient fabulaClient) {
        this.fabulaClient = fabulaClient;
    }

    public ServiceResponse<List<PersonageResponse>> findAll() {
        return fabulaClient.getAll();
    }

    public ServiceResponse<List<PersonageResponse>> findByOwnerId(UUID ownerId) {
        return fabulaClient.getByOwnerId(ownerId);
    }

    public ServiceResponse<PersonageResponse> findById(UUID id) {
        return fabulaClient.getById(id);
    }

    public ServiceResponse<PersonageResponse> create(UUID authenticatedOwnerId, PersonageRequest request){
        PersonageRequest trustedRequest = new PersonageRequest(
                request.firstName(),
                request.lastName(),
                request.age(),
                authenticatedOwnerId
        );
        return fabulaClient.create(trustedRequest);
    }

    public ServiceResponse<PersonageResponse> update(UUID id, PersonageRequest request){
        return fabulaClient.update(id, request);
    }

    public ServiceResponse<Boolean> delete(UUID id) {
        return fabulaClient.delete(id);
    }
}
