package com.enjoytrip.attraction.domain;

import com.enjoytrip.attraction.service.command.AttractionCreateCommand;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {
    private Long attractionId;
    private String title;
    private String contentId;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String address;

    static public Attraction from(AttractionCreateCommand command) {
        return new Attraction(
                null,
                command.getTitle(),
                command.getContentId(),
                command.getFirstImage(),
                command.getMapX(),
                command.getMapY(),
                command.getAddress()
        );
    }
}
