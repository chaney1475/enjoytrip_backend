package com.enjoytrip.chatting.domain;

import com.enjoytrip.chatting.dto.ChatMessageDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(name = "chatRoomId_timestamp_idx", def = "{'chatRoomId' : 1, 'timestamp' : -1}")
@Document(collection = "chat_message")
public class ChatMessage {
    // MongoDB에서 자동으로 생성되는 고유 ID
    @Id
    private String id;

    // 채팅방 아이디
    private Long chatRoomId;

    // 보낸 User id
    private Long senderId;

    // 내용
    private String content;

    // 전송 시간
    @Indexed(direction = IndexDirection.DESCENDING)
    private Date timestamp;

    // 닉네임
    private String nickname;

    // 프로필 사진 URL
    private String profileUrl;

    public static ChatMessage from(ChatMessageDTO dto){
        return ChatMessage.builder()
                .chatRoomId(Long.valueOf(dto.getChatRoomId()))
                .senderId(Long.valueOf(dto.getSenderId()))
                .content(dto.getContent())
                .timestamp(dto.getTimestamp())
                .nickname(dto.getNickname())
                .profileUrl(dto.getProfileUrl())
                .build();
    }


}
