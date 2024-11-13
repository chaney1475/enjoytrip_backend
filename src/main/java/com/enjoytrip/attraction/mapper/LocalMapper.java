package com.enjoytrip.attraction.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocalMapper {
    String findSigunguNameByAreaCodeAndSigunguCode(String areaCode, String sigunguCode);
}
