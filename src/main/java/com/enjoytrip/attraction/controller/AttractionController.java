package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.controller.request.AttractionCreateRequest;
import com.enjoytrip.attraction.controller.response.AttractionRankingResponse;
import com.enjoytrip.attraction.controller.response.AttractionResponse;
import com.enjoytrip.attraction.controller.response.TopNthCityResponse;
import com.enjoytrip.attraction.service.AttractionService;
import com.enjoytrip.attraction.service.RankingService;
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
@RequestMapping("/api/v1/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;
    private final RankingService rankingService;

    @LoginRequired
    @PostMapping
    public ResponseEntity<AttractionResponse> createAttraction(@Parameter(hidden = true) @Authenticated AuthClaims claims, @RequestBody AttractionCreateRequest attractionRequest) {

        System.out.println(attractionRequest.toString());

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

        attractionService.deleteUserAttraction(claims.getUserId(), attractionId);

        return ResponseEntity.ok("관심목록에서 관광지 삭제 성공");
    }

    @GetMapping("/top/cities")
    public ResponseEntity<List<TopNthCityResponse>> getTopNthCities() {

        List<TopNthCityResponse> topNthCityResponse =
                attractionService.getTopNthCities()
                        .stream().map(TopNthCityResponse::from).toList();

        return ResponseEntity.ok(topNthCityResponse);
    }

    @GetMapping("/top/attractions")
    public ResponseEntity<List<AttractionRankingResponse>> getAttractionRanking() {

        List<AttractionRankingResponse> attractionRankingResponses =
                rankingService.get("weekly:popular")
                        .stream().map(AttractionRankingResponse::from).toList();

        return ResponseEntity.ok(attractionRankingResponses);
    }
}
