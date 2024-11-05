package com.enjoytrip.common.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.enjoytrip.*.mapper")
public class MyBatisConfig {
}
