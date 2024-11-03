package com.enjoytrip.attraction.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttractionCreateRequest {
    private String title;
    private String contentId;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String address;
}
