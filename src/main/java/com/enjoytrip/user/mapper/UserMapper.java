package com.enjoytrip.user.mapper;

import com.enjoytrip.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {

    // Social ID로 사용자 조회
    Optional<User> findBySocialId(@Param("socialId") String socialId);

    Optional<User> findByEmail(@Param("email") String email);

    // User ID로 사용자 조회
    Optional<User> findById(@Param("userId") Long userId);

    // 사용자 삽입
    void insertUser(User user);

    // 사용자 업데이트
    void updateUser(User user);
}
