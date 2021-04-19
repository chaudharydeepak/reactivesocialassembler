package com.social.techblog.assembler.service;

import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;

import java.util.Map;

@FunctionalInterface
public interface WrapperService {
    Flux<Map> loadFeed(MultiValueMap<String, String> requestObjectString);
}
