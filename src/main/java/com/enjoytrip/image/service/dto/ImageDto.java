package com.enjoytrip.image.service.dto;

import com.enjoytrip.image.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImageDto {
    private Long id;

    public static ImageDto from(Image image) {
        return new ImageDto(image.getId());
    }
}
