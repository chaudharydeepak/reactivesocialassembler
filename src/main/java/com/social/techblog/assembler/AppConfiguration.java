package com.social.techblog.assembler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfiguration {

    @Bean("sofcli")
    public WebClient getStackOverflowClient() {
        return WebClient.create("https://api.stackexchange.com/2.2/search/advanced");
    }

    @Bean("githcli")
    public WebClient getGithubClient() {
        return WebClient.create("https://api.github.com/");
    }

    @Bean("devtocli")
    public WebClient getGDevToClient() {
        return WebClient.create("https://dev.to/api/");
    }
}
