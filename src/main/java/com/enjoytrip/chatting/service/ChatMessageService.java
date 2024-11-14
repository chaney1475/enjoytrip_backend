package com.enjoytrip.chatting.service;

import com.enjoytrip.chatting.domain.ChatMessage;
import com.enjoytrip.chatting.dto.ChatMessageDTO;
import com.enjoytrip.chatting.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatMessageRepository repository;

    private final RedisTemplate<String, ChatMessageDTO> redisTemplate;
    private static final String MESSAGE_BUFFER_KEY = "chat_message_buffer";

    @Async
    public void saveAsync(ChatMessage chatMessage) {
        repository.save(chatMessage);
    }

    public List<ChatMessage> getRecentMessages(Long chatRoomId, Date beforeTimestamp) {
        // 기준 timestamp 이전의 최신 10개의 메시지를 반환
        return repository.findTop10ByChatRoomIdAndTimestampBefore(chatRoomId, beforeTimestamp);
    }

    // Redis에서 버퍼링된 메시지 가져오기
    public List<ChatMessageDTO> getBufferedMessages(String roomNumber) {
        String redisKey = MESSAGE_BUFFER_KEY + ":" + roomNumber;
        return redisTemplate.opsForList().range(redisKey, 0, -1);
    }

    public void saveMessageToBuffer(String roomNumber, ChatMessageDTO message) {
        String redisKey = MESSAGE_BUFFER_KEY + ":" + roomNumber;
        redisTemplate.opsForList().rightPush(redisKey, message);
        redisTemplate.expire(redisKey, 2, TimeUnit.MINUTES);
    }

}
