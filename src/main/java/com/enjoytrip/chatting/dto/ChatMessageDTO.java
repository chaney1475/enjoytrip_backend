package com.enjoytrip.chatting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ChatMessageDTO {
    // 메시지 아이디
    private String messageId;
    // 채팅방 아이디
    private String chatRoomId;
    // 보낸 User id
    private String senderId;
    // 내용
    private String content;
    // 전송 시간
    private LocalDateTime timestamp;
    // 닉네임
    private String nickname;
    // 프로필 사진 URL
    private String profileUrl;
    // 필요시 생성자, getter, setter 추가
    // GroupChat 엔티티를 GroupChatDTO로 변환하는 메서드
}

