package com.enjoytrip.social.domain;

import com.enjoytrip.social.service.GoogleOAuthService;
import com.enjoytrip.social.service.OAuthService;
import lombok.Getter;

@Getter
public enum SocialType {
    GOOGLE(GoogleOAuthService.class),
    NONE;

    private final Class<? extends OAuthService> clazz;

    SocialType(Class<? extends OAuthService> clazz) {
        this.clazz = clazz;
    }

    SocialType() {
        this.clazz = null;
    }
}
