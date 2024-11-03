package com.enjoytrip.social.domain;

import com.enjoytrip.common.exception.BadRequestException;
import com.enjoytrip.social.service.GoogleOAuthService;
import com.enjoytrip.social.service.OAuthService;
import lombok.Getter;

public enum SocialType {
    GOOGLE(GoogleOAuthService.class),
    NONE;

    @Getter
    private final Class<? extends OAuthService> clazz;

    SocialType(Class<? extends OAuthService> clazz) {
        this.clazz = clazz;
    }

    SocialType() {
        this.clazz = null; // NONE일 때는 clazz를 null로 설정
    }

    public static SocialType fromString(String value) {
        for (SocialType type : SocialType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new BadRequestException("URL 반환 실패", "지원하지 않는 소셜 로그인 입니다");
    }
}
