package com.enjoytrip.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDTO {
    private String token;
    private Long userId;
    private String nickname;
    private String profileUrl;

    static public LoginDTO from(String token, UserDTO userDto) {
        return new LoginDTO(
                token,
                userDto.getUserId(),
                userDto.getNickname(),
                userDto.getAvatarUrl()
        );
    }
}
