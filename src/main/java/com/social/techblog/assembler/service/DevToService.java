package com.social.techblog.assembler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

@Slf4j
@Service
@ConfigurationProperties(prefix = "devto")
public class DevToService {

    @Autowired()
    @Qualifier("devtocli")
    private WebClient apiClient;

    @lombok.Setter
    private String apiToken;

    public Flux<Map> retrieveDevToArticles() {
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("per_page", "20");
        requestMap.add("page", "1");

        WrapperService wrapperServiceNotifications = (requestMapIns) ->
                apiClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/articles/me/all/").queryParams(requestMapIns).build())
                        .header("api-key", apiToken)
                        .retrieve()
                        .bodyToFlux(Map.class);

        return Flux.from(wrapperServiceNotifications.loadFeed(requestMap));
    }
}
