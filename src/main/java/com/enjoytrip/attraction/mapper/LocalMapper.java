package com.enjoytrip.attraction.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LocalMapper {
    String findSigunguNameByAreaCodeAndSigunguCode(@Param("areaCode") String areaCode, @Param("sigunguCode") String sigunguCode);
}
