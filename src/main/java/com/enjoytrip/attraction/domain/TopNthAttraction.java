package com.enjoytrip.attraction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthAttraction {
    private Long attractionId;
    private Integer rank;
    private Long count;
    private String title;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String address;
}
