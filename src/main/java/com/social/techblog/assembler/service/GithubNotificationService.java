package com.social.techblog.assembler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@ConfigurationProperties(prefix = "github")
public class GithubNotificationService {

    @Autowired()
    @Qualifier("githcli")
    private WebClient apiClient;

    @lombok.Setter
    private String apiToken;

    public Flux<Map> getNotifications() {
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("per_page", "20");
        requestMap.add("page", "1");
        requestMap.add("since", LocalDate.now().minusDays(1).toString());

        List<Flux<Map>> responeFluxList = new ArrayList<>();

        WrapperService wrapperServiceNotifications = (requestMapIns) ->
                apiClient.get()
                        .uri(uriBuilder -> uriBuilder.path("notifications").queryParams(requestMapIns).build())
                        .header("Authorization", apiToken)
                        .retrieve()
                        .bodyToFlux(Map.class);
        responeFluxList.add(wrapperServiceNotifications.loadFeed(requestMap)/*.log()*/.onErrorContinue((errorThrowable, error) -> log.error("encountered error notifications: {}", errorThrowable.getLocalizedMessage())));

        WrapperService wrapperServiceEvents = (requestMapIns) ->
                apiClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/users/chaudharydeepak/events").queryParams(requestMapIns).build())
                        .header("Authorization", apiToken)
                        .retrieve()
                        .bodyToFlux(Map.class);
        responeFluxList.add(wrapperServiceEvents.loadFeed(requestMap)/*.log()*/.onErrorContinue((errorThrowable, error) -> log.error("encountered error events: {}", errorThrowable.getLocalizedMessage())));

        return Flux.concat(responeFluxList).timeout(Duration.ofSeconds(10));
    }
}
