package com.enjoytrip.chatting.repository;

import com.enjoytrip.chatting.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    @Query(value = "{ 'chatRoomId': ?0, 'timestamp': { $lt: ?1 } }", sort = "{ 'timestamp': -1 }")
    List<ChatMessage> findTop10ByChatRoomIdAndTimestampBefore(Long chatRoomId, Date timestamp);

}
