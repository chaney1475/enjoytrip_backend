package com.enjoytrip.group.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserJoinGroupCommand {
    private Long userId;
    private Long groupId;

    public static UserJoinGroupCommand from(Long userId, Long groupId) {
        return new UserJoinGroupCommand(userId, groupId);
    }

}
