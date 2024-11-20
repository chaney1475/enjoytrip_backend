package com.enjoytrip.attraction.service.dto;

import com.enjoytrip.attraction.domain.AttractionRanking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AttractionRankingDto {
    private String address;
    private String contentId;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String sigunguName;
    private String tel;
    private String title;

    public static AttractionRankingDto from(AttractionRanking attractionRanking) {
        return new AttractionRankingDto(
                attractionRanking.getAddress(),
                attractionRanking.getContentId(),
                attractionRanking.getFirstImage(),
                attractionRanking.getMapX(),
                attractionRanking.getMapY(),
                attractionRanking.getSigunguName(),
                attractionRanking.getTel(),
                attractionRanking.getTitle()
        );
    }
}
