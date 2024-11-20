package com.enjoytrip.chatting.config;

import com.enjoytrip.attraction.domain.AttractionRanking;
import com.enjoytrip.chatting.dto.ChatMessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, ChatMessageDTO> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ChatMessageDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, List<AttractionRanking>> attractionRankingTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, List<AttractionRanking>> attractionRankingTemplate = new RedisTemplate<>();
        attractionRankingTemplate.setConnectionFactory(connectionFactory);
        attractionRankingTemplate.setKeySerializer(new StringRedisSerializer());
        attractionRankingTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return attractionRankingTemplate;
    }
}

