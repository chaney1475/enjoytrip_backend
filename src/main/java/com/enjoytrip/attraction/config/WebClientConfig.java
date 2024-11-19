package com.enjoytrip.attraction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        return builder
                .uriBuilderFactory(factory)
                .filter((request, next) -> {
                    System.out.println("Request URI: " + request.url());
                    return next.exchange(request);
                })
                .build();
    }
}
