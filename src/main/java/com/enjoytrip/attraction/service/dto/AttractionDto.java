package com.enjoytrip.attraction.service.dto;

import com.enjoytrip.attraction.domain.Attraction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttractionDto {
    private Long attractionId;
    private String title;
//    private String contentId;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String address;

    public static AttractionDto from(Attraction attraction) {
        return new AttractionDto(
                attraction.getAttractionId(),
                attraction.getTitle(),
                attraction.getFirstImage(),
                attraction.getMapX(),
                attraction.getMapY(),
                attraction.getAddress()
        );
    }
}
