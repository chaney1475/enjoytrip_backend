package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.command.LocalSearchCommand;
import com.enjoytrip.attraction.service.dto.LocalSearchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class LocalSearchResponse {
    private String address;
    private String contentId;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String tel;
    private String title;
    private String areaCode;
    private String sigunguCode;

    public static LocalSearchResponse from(LocalSearchDto localSearchDto) {
        return new LocalSearchResponse(
                localSearchDto.getAddress(),
                localSearchDto.getContentId(),
                localSearchDto.getFirstImage(),
                localSearchDto.getMapX(),
                localSearchDto.getMapY(),
                localSearchDto.getTel(),
                localSearchDto.getTitle(),
                localSearchDto.getAreaCode(),
                localSearchDto.getSigunguCode()
        );
    }
}
