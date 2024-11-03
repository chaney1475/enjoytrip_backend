package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.TopNthAttractionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthAttractionResponse {
    private Long attractionId;
    private Integer rank;
    private Long count;
    private String title;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String address;

    public static TopNthAttractionResponse from(TopNthAttractionDto topNthAttractionDto) {
        return new TopNthAttractionResponse(
                topNthAttractionDto.getAttractionId(),
                topNthAttractionDto.getRank(),
                topNthAttractionDto.getCount(),
                topNthAttractionDto.getTitle(),
                topNthAttractionDto.getFirstImage(),
                topNthAttractionDto.getMapX(),
                topNthAttractionDto.getMapY(),
                topNthAttractionDto.getAddress()
        );
    }
}
