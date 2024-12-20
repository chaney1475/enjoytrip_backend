package com.enjoytrip.attraction.mapper;

import com.enjoytrip.attraction.domain.Attraction;
import com.enjoytrip.attraction.domain.AttractionRanking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {
    int insertAttraction(Attraction attraction);

    // 이미 존재하는 관광지인지 확인
    Long findAttractionIdByContentId(String contentId);

    List<AttractionRanking> getAttractionsByIds(List<Integer> topAttractionIds);
}
