package com.enjoytrip.chatting.service;

import com.enjoytrip.chatting.domain.ChatMessage;
import com.enjoytrip.chatting.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatMessageRepository repository;

    public ChatMessage save(ChatMessage chatMessage) {
        return repository.save(chatMessage);
    }

}
