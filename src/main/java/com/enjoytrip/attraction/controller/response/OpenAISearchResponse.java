package com.enjoytrip.attraction.controller.response;

import com.enjoytrip.attraction.service.dto.OpenAISearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OpenAISearchResponse {
    private String address;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String tel;
    private String title;
    private String sigunguName;
    private String reason;

    public static OpenAISearchResponse from(OpenAISearchDto openAISearchDto) {
        return new OpenAISearchResponse(
                openAISearchDto.getAddress(),
                openAISearchDto.getFirstImage(),
                openAISearchDto.getMapX(),
                openAISearchDto.getMapY(),
                openAISearchDto.getTel(),
                openAISearchDto.getTitle(),
                openAISearchDto.getSigunguName(),
                openAISearchDto.getReason()
        );
    }
}
