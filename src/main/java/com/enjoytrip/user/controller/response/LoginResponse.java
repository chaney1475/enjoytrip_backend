package com.enjoytrip.user.controller.response;

import com.enjoytrip.user.service.dto.LoginDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long userId;
    private String nickname;
    private String avatarUrl;

    static public LoginResponse from(LoginDTO dto) {
        return new LoginResponse(
                dto.getToken(),
                dto.getUserId(),
                dto.getNickname(),
                dto.getAvatarUrl()
        );
    }
}
