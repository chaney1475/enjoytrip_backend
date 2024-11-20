package com.enjoytrip.attraction.config;

import com.enjoytrip.attraction.domain.AttractionRanking;
import com.enjoytrip.attraction.mapper.AttractionMapper;
import com.enjoytrip.attraction.mapper.UserAttractionMapper;
import com.enjoytrip.attraction.service.RankingService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class AttractionRankingScheduler {

    private final RankingService rankingService;
    private final AttractionMapper attractionMapper;
    private final UserAttractionMapper userAttractionMapper;

    @Scheduled(cron = "0 */5 * * * *")
    public void updateWeeklyPopular() {
        System.out.println("Scheduled task started...");

        // DB에서 주간 인기 데이터를 가져옵니다.
        List<AttractionRanking> attractionRanking = getAttractionRanking();

        System.out.println(attractionRanking.toString());

        // Redis에 데이터 저장
        rankingService.save("weekly:popular", attractionRanking, 5, TimeUnit.MINUTES);

        System.out.println("Data saved to Redis: attractionRanking");
    }

    private List<AttractionRanking> getAttractionRanking() {
        List<Integer> topAttractionIds = userAttractionMapper.findTop5Attractions();

        return attractionMapper.getAttractionsByIds(topAttractionIds);
    }
}