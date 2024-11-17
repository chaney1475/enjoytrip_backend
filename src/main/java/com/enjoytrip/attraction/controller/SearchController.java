package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.controller.request.OpenAISearchRequest;
import com.enjoytrip.attraction.controller.response.AreaResponse;
import com.enjoytrip.attraction.controller.response.OpenAISearchResponse;
import com.enjoytrip.attraction.controller.response.SigunguResponse;
import com.enjoytrip.attraction.service.SearchService;
import com.enjoytrip.attraction.service.command.OpenAISearchCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/attractions/search")
public class SearchController {

    private SearchService searchService;

    @PostMapping("/openai")
    public ResponseEntity<List<OpenAISearchResponse>> getAttractions(@RequestBody OpenAISearchRequest openAISearchRequest) {
        List<OpenAISearchResponse> attractions = searchService
                .OpenAiSearchService(OpenAISearchCommand.from(openAISearchRequest))
                .stream().map(OpenAISearchResponse::from).toList();

        return ResponseEntity.ok(attractions);
    }

    @PostMapping("/areas")
    public ResponseEntity<Mono<List<AreaResponse>>> getArea() {
        Mono<List<AreaResponse>> areaResponse = searchService.getAreaService()
                .map(areaDto -> areaDto.stream()
                        .map(dto -> new AreaResponse(dto.getCode(), dto.getName()))
                        .collect(Collectors.toList()));

        return ResponseEntity.ok(areaResponse);
    }

    @PostMapping("/sigungus")
    public ResponseEntity<Mono<List<SigunguResponse>>> getSigungu(@RequestBody String areaCode) {
        System.out.println(areaCode);

        Mono<List<SigunguResponse>> sigunguResponse = searchService.getSigunguService(areaCode)
                .map(sigunguDto -> sigunguDto.stream()
                        .map(dto -> new SigunguResponse(dto.getCode(), dto.getName()))
                        .collect(Collectors.toList()));

        return ResponseEntity.ok(sigunguResponse);
    }
}
