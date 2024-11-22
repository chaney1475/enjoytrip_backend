package com.enjoytrip.user.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {

    @NotNull(message = "닉네임을 등록해주세요.")
    private String nickname;
    private Long avatarUrlId;

}
