package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.controller.request.OpenAISearchRequest;
import com.enjoytrip.attraction.controller.response.OpenAISearchResponse;
import com.enjoytrip.attraction.service.SearchService;
import com.enjoytrip.attraction.service.command.OpenAISearchCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
}
