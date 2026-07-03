package fr.fae.memoria.charona.gateway.fabula.infrastructure.clients;

import fr.fae.memoria.charona.gateway.fabula.api.dtos.requests.PersonageRequest;
import fr.fae.memoria.charona.gateway.fabula.api.dtos.responses.PersonageResponse;
import fr.fae.memoria.charona.shared.ServiceResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Component
public class FabulaClient {

    private final RestClient restClient;

    public FabulaClient(@Qualifier("fabulaRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public ServiceResponse<List<PersonageResponse>> getAll() {
        return restClient.get()
                .uri("/api/personages")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public ServiceResponse<List<PersonageResponse>> getByOwnerId(UUID ownerId) {
        return restClient.get()
                .uri("/api/personages/owner/{ownerId}", ownerId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public ServiceResponse<PersonageResponse> getById(UUID id) {
        return restClient.get()
                .uri("/api/personages/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public ServiceResponse<PersonageResponse> create(PersonageRequest request) {
        return restClient.post()
                .uri("/api/personages")
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public ServiceResponse<PersonageResponse> update(UUID id, PersonageRequest request) {
        return restClient.put()
                .uri("/api/personages/{id}", id)
                .body(request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public ServiceResponse<Boolean> delete(UUID id) {
        return restClient.delete()
                .uri("/api/personages/{id}", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}