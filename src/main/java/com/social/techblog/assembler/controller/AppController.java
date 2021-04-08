package com.social.techblog.assembler.controller;

import com.social.techblog.assembler.service.DevToService;
import com.social.techblog.assembler.service.GithubNotificationService;
import com.social.techblog.assembler.service.StackOverFlowService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Map;

@Slf4j
@RestController
public class AppController {

    private final StackOverFlowService stackOverFlowService;
    private final GithubNotificationService githubNotificationService;
    private final DevToService devToService;

    public AppController(StackOverFlowService stackOverFlowService, GithubNotificationService githubNotificationService, DevToService devToService) {
        this.stackOverFlowService = stackOverFlowService;
        this.githubNotificationService = githubNotificationService;
        this.devToService = devToService;
    }

    @CrossOrigin
    @GetMapping(value = "/sov", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map> getStackOverFlow() {
        return stackOverFlowService.getStackData()/*.delayElements(Duration.ofMillis(2500))*/;
    }

    @CrossOrigin
    @GetMapping(value = "/gith", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map> getGithubNotifications() {
        return githubNotificationService.getNotifications()/*.delayElements(Duration.ofMillis(2500))*/;
    }

    @CrossOrigin
    @GetMapping(value = "/devto", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map> getDevtoArticles() {
        return devToService.retrieveDevToArticles()/*.delayElements(Duration.ofMillis(2500))*/;
    }
}
