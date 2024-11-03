package com.enjoytrip.user.controller.response;

import com.enjoytrip.user.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {
    private Long userId;
    private String nickname;
    private String email;
    private String avatarUrl;

    public static UserResponse fromDto(UserDTO user) {
        return new UserResponse(
                user.getUserId(),
                user.getNickname(),
                user.getEmail(),
                user.getAvatarUrl()
        );
    }
}
