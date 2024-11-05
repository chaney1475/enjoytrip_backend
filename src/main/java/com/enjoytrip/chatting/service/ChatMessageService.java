package com.enjoytrip.chatting.service;

import com.enjoytrip.chatting.domain.ChatMessage;
import com.enjoytrip.chatting.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatMessageRepository repository;

    public ChatMessage save(ChatMessage chatMessage) {
        return repository.save(chatMessage);
    }

    public List<ChatMessage> getRecentMessages(Long chatRoomId, Date beforeTimestamp) {
        // 기준 timestamp 이전의 최신 10개의 메시지를 반환
        return repository.findTop10ByChatRoomIdAndTimestampBefore(chatRoomId, beforeTimestamp);
    }

}
