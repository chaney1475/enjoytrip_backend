package com.enjoytrip.chatting.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    // 메시지 아이디
    private Long messageId;
    // 채팅방 아이디
    private Long chatRoomId;
    // 보낸 User id
    private Long senderId;
    // 내용
    private String content;

    private LocalDateTime timestamp;
    // 닉네임
    private String nickname;
    // 프로필 사진
    private String profileUrl;
}
