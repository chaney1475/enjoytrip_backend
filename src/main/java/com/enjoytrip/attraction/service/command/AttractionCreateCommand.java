package com.enjoytrip.attraction.service.command;

import com.enjoytrip.attraction.controller.request.AttractionCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttractionCreateCommand {
    private String title;
    private String contentId;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String address;
    private Long userId;

    static public AttractionCreateCommand from(Long userId, AttractionCreateRequest request) {
        return new AttractionCreateCommand(
                request.getTitle(),
                request.getContentId(),
                request.getFirstImage(),
                request.getMapX(),
                request.getMapY(),
                request.getAddress(),
                userId
        );
    }
}
