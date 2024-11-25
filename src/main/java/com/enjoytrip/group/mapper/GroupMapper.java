package com.enjoytrip.group.mapper;

import com.enjoytrip.group.domain.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GroupMapper {
    void insertGroup(Group group);

    Optional<Group> findByGroupId(Long groupId);

    // 전체 그룹 목록 조회
    List<Group> selectAllGroups();

    List<Group> selectAllGroupsWithPagination(@Param("limit") int limit, @Param("offset") int offset);

    int getTotalGroupsCount();
}
