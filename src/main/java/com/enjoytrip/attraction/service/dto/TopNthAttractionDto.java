package com.enjoytrip.attraction.service.dto;

import com.enjoytrip.attraction.domain.TopNthAttraction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthAttractionDto {
    private Integer ranking;
    private String sigunguName;
    private Long count;

    public static TopNthAttractionDto from(TopNthAttraction topNthAttraction) {
        return new TopNthAttractionDto(
                topNthAttraction.getRanking(),
                topNthAttraction.getSigunguName(),
                topNthAttraction.getCount()
        );
    }
}
