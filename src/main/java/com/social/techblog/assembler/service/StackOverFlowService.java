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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@ConfigurationProperties(prefix = "stackoverflow")
public class StackOverFlowService {

    @lombok.Setter
    private List<Object> titles;

    @Autowired()
    @Qualifier("sofcli")
    private WebClient apiClient;

    public Flux<Map> getStackData() {
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("order", "desc");
        requestMap.add("sort", "activity");
        requestMap.add("site", "stackoverflow");
        requestMap.add("page", "1");
        requestMap.add("pagesize", "20");
        requestMap.add("title", "JAVA");

        List<Flux<Map>> responeFluxList = new ArrayList<>();

        WrapperService wrapperService = (requestMapIns) ->
                apiClient.get().uri(uriBuilder -> uriBuilder.queryParams(requestMapIns).build()).retrieve().bodyToFlux(Map.class);

        titles.forEach(currtitle -> {
            requestMap.remove("title");
            requestMap.add("title", (String) currtitle);
            responeFluxList.add(wrapperService.loadFeed(requestMap).log());
        });

//        requestMap.add("title", "Spring Native");
//        Flux<Map> nativeMap = wrapperService.loadFeed(requestMap);
//        requestMap.remove("title");
//        requestMap.add("title", "GraalVM");
//        Flux<Map> graalVMMap = wrapperService.loadFeed(requestMap);
//        requestMap.remove("title");
//        requestMap.add("title", "Apache Kafka");
//        Flux<Map> kafkaMap = wrapperService.loadFeed(requestMap);
//        return Flux.concat(nativeMap,graalVMMap,kafkaMap);
        return Flux.concat(responeFluxList).timeout(Duration.ofSeconds(10));
    }
}
