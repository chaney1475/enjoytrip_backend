package com.enjoytrip.attraction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopNthAttraction {
    private Integer ranking;
    private String sigunguName;
    private Long count;
}
