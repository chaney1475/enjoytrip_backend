package com.enjoytrip.attraction.domain;

import com.enjoytrip.attraction.service.command.AttractionCreateCommand;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {
    private Long attractionId;
    private String address;
    private String contentId;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String tel;
    private String title;
    private String sigunguName;

    static public Attraction from(AttractionCreateCommand command) {
        return new Attraction(
                null,
                command.getAddress(),
                command.getContentId(),
                command.getFirstImage(),
                command.getMapX(),
                command.getMapY(),
                command.getTel(),
                command.getTitle(),
                null
        );
    }
}
