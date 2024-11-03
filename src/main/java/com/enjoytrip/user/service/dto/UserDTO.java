package com.enjoytrip.user.service.dto;

import com.enjoytrip.social.domain.SocialType;
import com.enjoytrip.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String password;
    private String nickname;
    private String email;
    private SocialType socialType;
    private String socialId;
    private String avatarUrl;

    public static UserDTO from(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getPassword(),
                user.getNickname(),
                user.getEmail(),
                user.getSocialType(),
                user.getSocialId(),
                user.getAvatarUrl()
        );
    }
}
