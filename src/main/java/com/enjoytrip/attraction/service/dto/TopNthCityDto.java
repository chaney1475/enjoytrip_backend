package com.enjoytrip.attraction.service.dto;

import com.enjoytrip.attraction.domain.TopNthCity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthCityDto {
    private Integer ranking;
    private String sigunguName;
    private Long count;

    public static TopNthCityDto from(TopNthCity topNthCity) {
        return new TopNthCityDto(
                topNthCity.getRanking(),
                topNthCity.getSigunguName(),
                topNthCity.getCount()
        );
    }
}
