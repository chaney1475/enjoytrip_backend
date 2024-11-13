package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.TopNthAttractionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthAttractionResponse {
    private Integer ranking;
    private String sigunguName;
    private Long count;

    public static TopNthAttractionResponse from(TopNthAttractionDto topNthAttractionDto) {
        return new TopNthAttractionResponse(
                topNthAttractionDto.getRanking(),
                topNthAttractionDto.getSigunguName(),
                topNthAttractionDto.getCount()
        );
    }
}
