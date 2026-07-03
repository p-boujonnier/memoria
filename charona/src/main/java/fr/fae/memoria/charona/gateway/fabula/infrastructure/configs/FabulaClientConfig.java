package fr.fae.memoria.charona.gateway.fabula.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class FabulaClientConfig {

    @Value("${fabula.base-url}")
    private String fabulaBaseUrl;

    @Value("${internal.token}")
    private String internalToken;

    @Bean("fabulaRestClient")
    public RestClient fabulaRestClient() {
        return RestClient.builder()
                .baseUrl(fabulaBaseUrl)
                .defaultHeader("X-Internal-Token", internalToken)
                .build();
    }
}