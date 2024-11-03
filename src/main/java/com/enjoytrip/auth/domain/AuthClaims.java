package com.enjoytrip.auth.domain;

import com.enjoytrip.user.domain.User;
import com.enjoytrip.user.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AuthClaims {

    private Long userId;

    public static AuthClaims fromUser(UserDTO user) {
        return AuthClaims.builder()
                .userId(user.getUserId())
                .build();
    }
}

