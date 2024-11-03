package com.enjoytrip.social.service;

import com.enjoytrip.user.service.dto.UserCommand;

public interface OAuthService {
    String getAuthorizationUrl(String redirectUri);

    String getAccessToken(String authorizationCode, String redirectUri);

    UserCommand getUserProfile(String accessToken);
}
