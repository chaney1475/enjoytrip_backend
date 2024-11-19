package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.SigunguDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SigunguResponse {
    private String code;
    private String name;

    public static SigunguResponse from(SigunguDto sigunguDto) {
        return new SigunguResponse(
                sigunguDto.getCode(),
                sigunguDto.getName()
        );
    }
}
