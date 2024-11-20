package com.enjoytrip.attraction.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AttractionCreateRequest {
    private String address;
    private String contentId;
    private String firstImage;
    private BigDecimal mapX;
    private BigDecimal mapY;
    private String tel;
    private String title;
    @NotNull
    private String areaCode;
    private String sigunguCode;
}
