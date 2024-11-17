package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.AreaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AreaResponse {
    private String code;
    private String name;

    public static AreaResponse from(AreaDto areaDto) {
        return new AreaResponse(
                areaDto.getCode(),
                areaDto.getName()
        );
    }
}
