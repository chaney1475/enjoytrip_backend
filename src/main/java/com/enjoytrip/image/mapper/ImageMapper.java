package com.enjoytrip.image.mapper;

import com.enjoytrip.image.domain.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO image (url) VALUES (#{url})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Image image);

    @Select("SELECT * FROM image WHERE id = #{id}")
    Image findById(Long id);

    @Update("DELETE FROM image WHERE id = #{id}")
    void deleteById(Long id);
}

