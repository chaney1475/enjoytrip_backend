package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.AttractionRankingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AttractionRankingResponse {
    private String address;
    private String contentId;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String sigunguName;
    private String tel;
    private String title;

    public static AttractionRankingResponse from(AttractionRankingDto attractionRankingDto) {
        return new AttractionRankingResponse(
                attractionRankingDto.getAddress(),
                attractionRankingDto.getContentId(),
                attractionRankingDto.getFirstImage(),
                attractionRankingDto.getMapX(),
                attractionRankingDto.getMapY(),
                attractionRankingDto.getSigunguName(),
                attractionRankingDto.getTel(),
                attractionRankingDto.getTitle()
        );
    }
}
