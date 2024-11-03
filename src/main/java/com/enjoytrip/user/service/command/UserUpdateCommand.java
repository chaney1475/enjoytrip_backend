package com.enjoytrip.user.service.command;

import com.enjoytrip.user.controller.request.UserUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateCommand {

    private Long userId;
    private String nickname;
    private Long avatarImageId;

    public static UserUpdateCommand from(UserUpdateRequest request, Long userId) {
        return new UserUpdateCommand(
                userId,
                request.getNickname(),
                request.getAvatarImageId()
        );
    }
}
