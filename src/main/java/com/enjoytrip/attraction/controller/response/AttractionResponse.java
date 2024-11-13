package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.AttractionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AttractionResponse {
    private Long attractionId;
    private String address;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String tel;
    private String title;
    private String sigunguName;

    public static AttractionResponse from(AttractionDto attractionDto) {
        return new AttractionResponse(
                attractionDto.getAttractionId(),
                attractionDto.getAddress(),
                attractionDto.getFirstImage(),
                attractionDto.getMapX(),
                attractionDto.getMapY(),
                attractionDto.getTel(),
                attractionDto.getTitle(),
                attractionDto.getSigunguName()
        );
    }
}
