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
    private String contentId;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String tel;
    private String title;
    private String areaCode;
    private String sigunguCode;
    private String reason;

    public static OpenAISearchResponse from(OpenAISearchDto openAISearchDto) {
        return new OpenAISearchResponse(
                openAISearchDto.getAddress(),
                openAISearchDto.getContentId(),
                openAISearchDto.getFirstImage(),
                openAISearchDto.getMapX(),
                openAISearchDto.getMapY(),
                openAISearchDto.getTel(),
                openAISearchDto.getTitle(),
                openAISearchDto.getAreaCode(),
                openAISearchDto.getSigunguCode(),
                openAISearchDto.getReason()
        );
    }
}
