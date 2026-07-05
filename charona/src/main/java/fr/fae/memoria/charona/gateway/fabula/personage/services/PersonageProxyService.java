package fr.fae.memoria.charona.gateway.fabula.personage.services;

import fr.fae.memoria.charona.gateway.fabula.personage.api.dtos.requests.PersonageRequest;
import fr.fae.memoria.charona.gateway.fabula.personage.api.dtos.responses.PersonageResponse;
import fr.fae.memoria.charona.gateway.fabula.personage.infrastructure.clients.PersonageClient;
import fr.fae.memoria.charona.shared.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonageProxyService {

    private final PersonageClient fabulaClient;

    public PersonageProxyService(PersonageClient fabulaClient) {
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
