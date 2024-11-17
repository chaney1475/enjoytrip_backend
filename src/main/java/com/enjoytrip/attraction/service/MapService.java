package com.enjoytrip.attraction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapService {

    @Value("${API_KEY.MAP}")
    private String KAKAO_MAP_API_KEY;

    private final WebClient webClient;

    public Mono<String> getKakaoMap(Map<String, String> params) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        params.forEach(multiValueMap::add);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("dapi.kakao.com")
                        .path("/v2/maps/sdk.js")
                        .queryParam("appkey", KAKAO_MAP_API_KEY)
                        .queryParams(multiValueMap)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    System.err.println("MapService: Kakao Map API 호출 오류: " + e.getMessage());
                    return Mono.just("Kakao Map API 호출 중 문제가 발생했습니다.");
                });
    }
}