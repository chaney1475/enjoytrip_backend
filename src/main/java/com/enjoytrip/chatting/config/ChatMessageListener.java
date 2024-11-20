package com.enjoytrip.chatting.config;

import com.enjoytrip.chatting.dto.ChatMessageDTO;
import com.enjoytrip.chatting.service.ChatMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatMessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "chatting-topic", groupId = "chat-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        try {
            // Kafka에서 수신한 메시지 로그 추가
            System.out.println("Received from Kafka: " + message);

            // 메시지를 ChatMessageDTO 객체로 변환
            ChatMessageDTO chatMessage = objectMapper.readValue(message, ChatMessageDTO.class);
            String roomId = chatMessage.getChatRoomId();

            // 변환된 메시지 로그 추가
            System.out.println("Parsed ChatMessageDTO: " + chatMessage);

            // WebSocket 구독 경로로 전송
            messagingTemplate.convertAndSend("/api/sub/chat/" + roomId, chatMessage);

            // 전송 후 확인 로그 추가
            System.out.println("Sent to WebSocket /api/sub/chat/" + roomId + ": " + chatMessage);
        } catch (Exception e) {
            System.err.println("Error processing message: " + message);
            e.printStackTrace(); // 파싱 오류 시 에러 처리
        }
    }

}
