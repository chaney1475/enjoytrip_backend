package com.enjoytrip.attraction.service.command;

import com.enjoytrip.attraction.controller.request.AttractionCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AttractionCreateCommand {
    private String address;
    private String contentId;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String tel;
    private String title;
    private String areaCode;
    private String sigunguCode;
    private Long userId;

    static public AttractionCreateCommand from(Long userId, AttractionCreateRequest request) {
        return new AttractionCreateCommand(
                request.getAddress(),
                request.getContentId(),
                request.getFirstImage(),
                request.getMapX(),
                request.getMapY(),
                request.getTel(),
                request.getTitle(),
                request.getAreaCode(),
                request.getSigunguCode(),
                userId
        );
    }
}
