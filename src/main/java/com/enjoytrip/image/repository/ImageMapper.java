package com.enjoytrip.image.repository;

import com.enjoytrip.image.domain.Image;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO image (url) VALUES (#{url})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long save(Image image);

    @Select("SELECT * FROM image WHERE id = #{id} AND is_deleted = false")
    Image findById(Long id);

    @Select("SELECT * FROM image WHERE is_deleted = false")
    List<Image> findAll();

    @Update("UPDATE image SET is_deleted = true WHERE id = #{id}")
    void deleteById(Long id);
}

