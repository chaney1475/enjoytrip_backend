package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.controller.request.AttractionCreateRequest;
import com.enjoytrip.attraction.controller.response.AttractionResponse;
import com.enjoytrip.attraction.controller.response.TopNthAttractionResponse;
import com.enjoytrip.attraction.service.AttractionService;
import com.enjoytrip.attraction.service.command.AttractionCreateCommand;
import com.enjoytrip.auth.annotation.Authenticated;
import com.enjoytrip.auth.annotation.LoginRequired;
import com.enjoytrip.auth.domain.AuthClaims;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @LoginRequired
    @PostMapping
    public ResponseEntity<AttractionResponse> createAttraction(@Parameter(hidden = true) @Authenticated AuthClaims claims, @RequestBody AttractionCreateRequest attractionRequest) {

        AttractionResponse attractionResponse = AttractionResponse.from(
                attractionService.insertAttraction(AttractionCreateCommand.from(claims.getUserId(), attractionRequest))
        );

        return ResponseEntity.created(URI.create("api/attractions/" + attractionResponse.getAttractionId())).body(attractionResponse);
    }

    @LoginRequired
    @GetMapping
    public ResponseEntity<List<AttractionResponse>> getUserAttractions(@Parameter(hidden = true)@Authenticated AuthClaims claims) {

        List<AttractionResponse> attractionResponses =
                attractionService.getUserAttractions(claims.getUserId())
                        .stream().map(AttractionResponse::from).toList();

        return ResponseEntity.ok(attractionResponses);
    }

    @LoginRequired
    @DeleteMapping("/{attractionId}")
    public ResponseEntity<String> deleteUserAttraction(@Parameter(hidden = true)@Authenticated AuthClaims claims, @PathVariable Long attractionId) {

        attractionService.deleteUserAttraction(attractionId, claims.getUserId());

        return ResponseEntity.ok("관심목록에서 관광지 삭제 성공");
    }

    @GetMapping("/top")
    public ResponseEntity<List<TopNthAttractionResponse>> getTopNthAttractions() {

        List<TopNthAttractionResponse> topNthAttractionResponses =
                attractionService.getTopNthAttractions()
                        .stream().map(TopNthAttractionResponse::from).toList();

        return ResponseEntity.ok(topNthAttractionResponses);
    }
}
