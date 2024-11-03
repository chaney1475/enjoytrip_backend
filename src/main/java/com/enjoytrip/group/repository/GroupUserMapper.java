package com.enjoytrip.group.repository;

import com.enjoytrip.group.domain.Group;
import com.enjoytrip.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupUserMapper {

    // 유저가 참여한 그룹 조회
    List<Group> findGroupsByUserId(Long userId);

    // 유저가 그룹에 가입
    int insertGroupUser(@Param("userId") Long userId, @Param("groupId") Long groupId);

    // 특정 그룹에 참여한 모든 사용자 정보 조회
    List<User> selectUsersInGroup(@Param("groupId") Long groupId);

    // 특정 그룹에 참여한 사용자 수 조회
    int countUsersInGroup(@Param("groupId") Long groupId);
}
