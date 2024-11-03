package com.enjoytrip.user.domain;

import com.enjoytrip.social.domain.SocialType;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long userId;
    private String nickname;
    private String email;
    private SocialType socialType;
    private String socialId;
    private String avatarUrl;
    private String password; // 비밀번호 필드 추가

    // user.update(command.getNickname(), image.getUrl());
    public void update(String name, String avatarUrl) {
        this.nickname = name;
        this.avatarUrl = avatarUrl;
    }
}
