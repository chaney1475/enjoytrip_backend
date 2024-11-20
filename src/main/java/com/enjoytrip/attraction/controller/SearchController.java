package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.controller.request.LocalSearchRequest;
import com.enjoytrip.attraction.controller.request.OpenAISearchRequest;
import com.enjoytrip.attraction.controller.response.*;
import com.enjoytrip.attraction.service.SearchService;
import com.enjoytrip.attraction.service.command.LocalSearchCommand;
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
@RequestMapping("/api/v1/attractions/search")
public class SearchController {

    private SearchService searchService;

    @PostMapping("/openai")
    public ResponseEntity<List<OpenAISearchResponse>> getAttractions(@RequestBody OpenAISearchRequest openAISearchRequest) {
        List<OpenAISearchResponse> attractions = searchService
                .OpenAiSearch(OpenAISearchCommand.from(openAISearchRequest))
                .stream().map(OpenAISearchResponse::from).toList();

        return ResponseEntity.ok(attractions);
    }

    @PostMapping("/areas")
    public ResponseEntity<Mono<List<AreaResponse>>> getArea() {
        Mono<List<AreaResponse>> areaResponse = searchService.getArea()
                .map(areaDto -> areaDto.stream()
                        .map(dto -> new AreaResponse(dto.getCode(), dto.getName()))
                        .collect(Collectors.toList()));

        return ResponseEntity.ok(areaResponse);
    }

    @PostMapping("/sigungus")
    public ResponseEntity<Mono<List<SigunguResponse>>> getSigungu(@RequestBody String areaCode) {
        Mono<List<SigunguResponse>> sigunguResponse = searchService.getSigungu(areaCode)
                .map(sigunguDto -> sigunguDto.stream()
                        .map(dto -> new SigunguResponse(dto.getCode(), dto.getName()))
                        .collect(Collectors.toList()));

        return ResponseEntity.ok(sigunguResponse);
    }

    @PostMapping("/local")
    public ResponseEntity<Mono<List<LocalSearchResponse>>> searchAttractionByLocal(@RequestBody LocalSearchRequest localSearchRequest) {
        Mono<List<LocalSearchResponse>> localSearchResponse = searchService.getLocalSearch(LocalSearchCommand.from(localSearchRequest))
                .map(localSearchDtos -> localSearchDtos
                                .stream()
                                .map(LocalSearchResponse::from)
                        .collect(Collectors.toList()));

        return ResponseEntity.ok(localSearchResponse);
    }
}
