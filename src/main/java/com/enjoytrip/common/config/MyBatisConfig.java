package com.enjoytrip.common.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.enjoytrip.*.repository")
public class MyBatisConfig {
    // MyBatis 관련 설정
}
