package com.enjoytrip.group.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLeaveGroupCommand {
    private final Long userId;
    private final Long groupId;

    public static UserLeaveGroupCommand from(Long userId, Long groupId) {
        return new UserLeaveGroupCommand(userId, groupId);
    }
}
