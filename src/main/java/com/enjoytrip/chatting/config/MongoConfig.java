package com.enjoytrip.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.enjoytrip.chatting.repository")
public class MongoConfig {
    // 추가적인 MongoDB 설정이 있다면 여기에 작성
}

