package com.enjoytrip.chatting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatJoin {
    private Long joinId;
    private Long chatRoomId;
    private Long userId;
    private LocalDateTime joinedAt;
}
