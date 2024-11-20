package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.domain.Attraction;
import com.enjoytrip.attraction.exception.AttractionException;
import com.enjoytrip.attraction.mapper.AttractionMapper;
import com.enjoytrip.attraction.mapper.UserAttractionMapper;
import com.enjoytrip.attraction.mapper.LocalMapper;
import com.enjoytrip.attraction.service.command.AttractionCreateCommand;
import com.enjoytrip.attraction.service.dto.AttractionDto;
import com.enjoytrip.attraction.service.dto.TopNthCityDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AttractionService {

    private final int TOP_N = 10;

    private final AttractionMapper attractionMapper;
    private final UserAttractionMapper userAttractionMapper;
    private final LocalMapper localMapper;

    @Transactional
    public AttractionDto insertAttraction(AttractionCreateCommand command) {

        Attraction attraction = Attraction.from(command);
        Long attractionId = null;
        int result = 0;

        /**
         * 1. areacode와 sigungucode를 이용해 시군구 이름을 가져온다.
          */
        String sigunguName = localMapper.findSigunguNameByAreaCodeAndSigunguCode(command.getAreaCode(), command.getSigunguCode());

        if(sigunguName == null) {
            throw new AttractionException("FIND_SIGUNGU_NAME_FAILED", "시군구 이름 찾기 실패", "시군구 이름 찾기에 실패하였습니다.");
        }

        attraction.setSigunguName(sigunguName);

        /**
         * DB에 데이터를 삽입하는 경우
         * 1. contentId가 null : openAI를 이용한 검색
         * 2. contentId를 가진 레코드가 존재하지 않는 경우
         */
        if(attraction.getContentId() == null || (attractionId = attractionMapper.findAttractionIdByContentId(attraction.getContentId())) == null) {
            result = attractionMapper.insertAttraction(attraction);
            if(result == 0) {
                throw new AttractionException("ATTRACTION_INSERT_FAILED", "관광지 정보 삽입 실패", "관광지 정보를 삽입에 실패하였습니다.");
            }
        } else {
            attraction.setAttractionId(attractionId);
        }

        if(userAttractionMapper.findByUserIdAndAttractionId(command.getUserId(), attraction.getAttractionId()).isPresent()) {
            throw new AttractionException("USER_ATTRACTION_ALREADY_EXIST", "관심목록에 관광지가 이미 존재", "관심목록에 관광지가 이미 존재합니다.");
        }

        result = userAttractionMapper.createUserAttraction(command.getUserId(), attraction);
        if(result == 0) {
            throw new AttractionException("USER_ATTRACTION_INSERT_FAILED", "관심목록에 관광지 삽입 실패", "관심목록에 관광지 추가를 실패하였습니다.");
        }

        return AttractionDto.from(attraction);
    }

    @Transactional
    public List<AttractionDto> getUserAttractions(Long userId) {

        return userAttractionMapper.findByUserId(userId)
                .stream().map(AttractionDto::from).toList();
    }

    @Transactional
    public void deleteUserAttraction(Long userId, Long attractionId) {

        int result = userAttractionMapper.deleteByUserIdAndAttractionId(userId, attractionId);

        if(result == 0) {
            throw new AttractionException("USER_ATTRACTION_DELETE_FAILED", "관심목록에서 관광지 삭제 실패", "관심목록에서 관광지 삭제를 실패하였습니다.");
        }
    }

    @Transactional
    public List<TopNthCityDto> getTopNthCities() {

        return userAttractionMapper.findTopNthCities(TOP_N)
                .stream().map(TopNthCityDto::from).toList();
    }
}
