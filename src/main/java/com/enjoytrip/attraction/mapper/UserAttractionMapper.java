package com.enjoytrip.attraction.mapper;

import com.enjoytrip.attraction.domain.Attraction;
import com.enjoytrip.attraction.domain.TopNthAttraction;
import com.enjoytrip.attraction.domain.UserAttraction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserAttractionMapper {

    int createUserAttraction(@Param("userId") Long userId, @Param("attraction") Attraction attraction);

    // 이미 관심 표시를 한 관광지 인지 확인
    Optional<UserAttraction> findByUserIdAndAttractionId(@Param("userId")Long userId, @Param("attractionId")Long attractionId);

    // 유저가 관심 표시를 한 관광지 정보를 모두 가져오기
    List<Attraction> findByUserId(Long userId);


    int deleteByUserIdAndAttractionId(Long userId, Long attractionId);

    // Top10 지역 가져오기
    List<TopNthAttraction> findTopNth(int topNth);
}
