package com.enjoytrip.attraction.repository;

import com.enjoytrip.attraction.domain.Attraction;
import com.enjoytrip.attraction.domain.AttractionUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AttractionUserMapper {

    int createAttractionUser(Attraction attraction, Long userId);

    // 이미 관심 표시를 한 관광지 인지 확인
    Optional<AttractionUser> findByAttractionIdAndUserId(Long attractionId, Long userId);

    // 유저가 관심 표시를 한 관광지 정보를 모두 가져오기
    List<Attraction> findByUserId(Long userId);

    int deleteByAttractionIdAndUserId(Long attractionId, Long userId);
}
