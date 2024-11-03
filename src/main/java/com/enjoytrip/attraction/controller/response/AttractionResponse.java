package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.AttractionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttractionResponse {
    private long attractionId;
    private String title;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String address;

    public static AttractionResponse from(AttractionDto attractionDto) {
        return new AttractionResponse(
                attractionDto.getAttractionId(),
                attractionDto.getTitle(),
                attractionDto.getFirstImage(),
                attractionDto.getMapX(),
                attractionDto.getMapY(),
                attractionDto.getAddress()
        );
    }
}
