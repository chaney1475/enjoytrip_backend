package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.domain.AttractionRanking;
import com.enjoytrip.attraction.service.dto.AttractionRankingDto;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RankingService {

    private final RedisTemplate<String, List<AttractionRanking>> attractionRankingTemplate;

    // 데이터 저장
    public void save(String key, List<AttractionRanking> attractionRanking, long duration, TimeUnit timeUnit) {
        attractionRankingTemplate.opsForValue().set(key, attractionRanking, duration, timeUnit);
    }

    // 데이터 가져오기
    public List<AttractionRankingDto> get(String key) {
        List<AttractionRanking> attractionRankings = attractionRankingTemplate.opsForValue().get(key);

        return attractionRankings == null ?
                null : attractionRankings.stream().map(AttractionRankingDto::from).toList();
    }

    // 데이터 삭제
    public void delete(String key) {
        attractionRankingTemplate.delete(key);
    }
}