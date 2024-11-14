package com.enjoytrip.attraction.service.dto;

import com.enjoytrip.attraction.domain.Attraction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AttractionDto {
    private Long attractionId;
    private String address;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String tel;
    private String title;
    private String sigunguName;

    public static AttractionDto from(Attraction attraction) {
        return new AttractionDto(
                attraction.getAttractionId(),
                attraction.getAddress(),
                attraction.getFirstImage(),
                attraction.getMapX(),
                attraction.getMapY(),
                attraction.getTel(),
                attraction.getTitle(),
                attraction.getSigunguName()
        );
    }
}
