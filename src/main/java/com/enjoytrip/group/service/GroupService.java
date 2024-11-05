package com.enjoytrip.group.service;

import com.enjoytrip.group.exception.GroupException;
import com.enjoytrip.group.domain.Group;
import com.enjoytrip.group.mapper.GroupMapper;
import com.enjoytrip.group.mapper.GroupUserMapper;
import com.enjoytrip.group.service.command.GroupCreateCommand;
import com.enjoytrip.group.service.command.UserJoinGroupCommand;
import com.enjoytrip.group.service.dto.GroupDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupMapper groupMapper;
    private final GroupUserMapper groupUserMapper;
    @Transactional
    public GroupDTO createGroup(GroupCreateCommand command){

        Group group = Group.from(command, LocalDateTime.now());

        groupMapper.insertGroup(group);
        groupUserMapper.insertGroupUser(command.getCreator(), group.getGroupId());

        Group newGroup = groupMapper.findByGroupId(group.getGroupId())
                .orElseThrow(() -> new GroupException("GROUP_CREATE_FAILED", "그룹 생성 실패", "그룹 생성에 실패하였습니다."));
        return GroupDTO.from(newGroup);
    }

    // 유저가 그룹에 참여하는 메서드
    @Transactional
    public void joinGroup(UserJoinGroupCommand command) {
        int result = groupUserMapper.insertGroupUser(command.getUserId(), command.getGroupId());
        if (result == 0){
            throw new GroupException("GROUP_JOIN_FAILED", "그룹 가입 실패", "그룹 가입에 실패하였습니다.");
        }
    }

    // 유저가 참여한 그룹 목록을 조회하는 메서드
    @Transactional
    public List<Group> getUserGroups(Long userId) {
        return groupUserMapper.findGroupsByUserId(userId);
    }

    // 아아디로 그룹 단건 조회
    @Transactional
    public GroupDTO getGroup(Long groupId) {
        Group group = groupMapper.findByGroupId(groupId)
                .orElseThrow(() -> new GroupException("GROUP_NOT_FOUND", "그룹 조회 실패", "해당 그룹을 찾을 수 없습니다."));
        return GroupDTO.from(group);
    }

    // 전체 그룹 목록을 조회하는 메서드
    @Transactional
    public List<GroupDTO> getGroups() {

        List<Group> groups = groupMapper.selectAllGroups();

        return groups.stream()
                .map(GroupDTO::from)
                .toList();
    }
}
