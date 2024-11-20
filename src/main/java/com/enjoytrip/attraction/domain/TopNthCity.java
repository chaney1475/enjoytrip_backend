package com.enjoytrip.attraction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthCity {
    private Integer ranking;
    private String sigunguName;
    private Long count;
}
