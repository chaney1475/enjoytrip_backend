package com.enjoytrip.image.controller.response;

import com.enjoytrip.image.service.dto.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

    private Long id;
    public static ImageResponse from(final ImageDto dto) {

        return new ImageResponse(dto.getId());
    }

}
