package com.enjoytrip.attraction.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocalSearchRequest {
    Integer areaCode;
    Integer sigunguCode;
    Integer contentTypeId;
    String keyword;
}
