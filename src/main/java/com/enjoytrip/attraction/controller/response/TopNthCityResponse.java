package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.TopNthCityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthCityResponse {
    private Integer ranking;
    private String sigunguName;
    private Long count;

    public static TopNthCityResponse from(TopNthCityDto topNthCityDto) {
        return new TopNthCityResponse(
                topNthCityDto.getRanking(),
                topNthCityDto.getSigunguName(),
                topNthCityDto.getCount()
        );
    }
}
