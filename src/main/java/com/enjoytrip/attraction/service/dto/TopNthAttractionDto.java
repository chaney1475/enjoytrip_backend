package com.enjoytrip.attraction.service.dto;

import com.enjoytrip.attraction.domain.TopNthAttraction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthAttractionDto {
    private Long attractionId;
    private Integer rank;
    private Long count;
    private String title;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String address;

    public static TopNthAttractionDto from(TopNthAttraction topNthAttraction) {
        return new TopNthAttractionDto(
                topNthAttraction.getAttractionId(),
                topNthAttraction.getRank(),
                topNthAttraction.getCount(),
                topNthAttraction.getTitle(),
                topNthAttraction.getFirstImage(),
                topNthAttraction.getMapX(),
                topNthAttraction.getMapY(),
                topNthAttraction.getAddress()
        );
    }
}
