package fr.fae.memoria.charona.gateway.fabula.note.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class NoteClientConfig {

    @Value("${fabula.base-url}")
    private String fabulaBaseUrl;

    @Value("${internal.token}")
    private String internalToken;

    @Bean("noteRestClient")
    public RestClient noteRestClient() {
        return RestClient.builder()
                .baseUrl(fabulaBaseUrl)
                .defaultHeader("X-Internal-Token", internalToken)
                .build();
    }
}