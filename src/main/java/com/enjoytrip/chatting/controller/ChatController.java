package com.enjoytrip.chatting.controller;

import com.enjoytrip.chatting.config.ChatUserPrincipal;
import com.enjoytrip.chatting.domain.ChatMessage;
import com.enjoytrip.chatting.dto.ChatMessageDTO;
import com.enjoytrip.chatting.producer.ChatMessageProducer;
import com.enjoytrip.chatting.service.ChatMessageService;
import com.enjoytrip.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController {
    private final JwtUtil jwtUtil;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatMessageProducer chatMessageProducer;

    // 채팅을 웹소켓으로 받아서 Kafka에 발행
    @MessageMapping("/chat/{roomNumber}")
    public void handleChatMessage(@DestinationVariable String roomNumber, @Payload ChatMessageDTO message, Principal principal) {
        if (principal instanceof ChatUserPrincipal chatUserPrincipal) {

            // Principal에서 userId를 가져와서 설정
            message.setSenderId(chatUserPrincipal.getName());
        }

        message.setChatRoomId(roomNumber);
        // 로그 추가
        System.out.println("Received WebSocket message: " + message);

        // 데이터베이스 저장
        chatMessageService.saveAsync(ChatMessage.from(message));

        // 메시지 발행
        chatMessageProducer.sendMessage(message);
    }

    @GetMapping("/messages")
    public List<ChatMessage> getRecentMessages(
            @RequestParam Long chatRoomId,
            @RequestParam(required = false) Date beforeTimestamp) {

        // beforeTimestamp가 없는 경우 현재 시간을 기준으로 설정
        if (beforeTimestamp == null) {
            beforeTimestamp = new Date();
        }

        return chatMessageService.getRecentMessages(chatRoomId, beforeTimestamp);
    }
}

