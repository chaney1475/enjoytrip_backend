package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.service.MapService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("api/v1/map")
@AllArgsConstructor
public class MapController {

    private final MapService kakaoMapService;

    @GetMapping("/view")
    public Mono<ResponseEntity<String>> getMap(@RequestParam Map<String, String> params) {
        return kakaoMapService.getKakaoMap(params)
                .map(script -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "text/javascript") // Content-Type 설정
                        .body(script))
                .onErrorResume(error -> Mono.just(
                        ResponseEntity.status(500)
                                .body("Kakao Map API 호출 중 문제가 발생했습니다.")
                ));
    }
}
