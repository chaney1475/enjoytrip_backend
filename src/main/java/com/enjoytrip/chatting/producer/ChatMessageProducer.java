package com.enjoytrip.chatting.producer;

import com.enjoytrip.chatting.dto.ChatMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ChatMessageProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(ChatMessageDTO message) {
        try {
            // ChatMessageDTO를 JSON 문자열로 변환
            String messageJson = objectMapper.writeValueAsString(message);

            // 로그 추가
            System.out.println("Publishing to Kafka: " + messageJson);
            String key = "chatroom:" + message.getChatRoomId();

            kafkaTemplate.send("chatting-topic", key, messageJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

