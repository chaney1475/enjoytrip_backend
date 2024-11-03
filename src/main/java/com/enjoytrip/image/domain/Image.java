package com.enjoytrip.image.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    private Long id;
    private String url;

    public Image(String url) {
        this.url = url;
    }
}
