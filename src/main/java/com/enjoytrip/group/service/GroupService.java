package com.enjoytrip.group.service;

import com.enjoytrip.common.schema.PagedResponse;
import com.enjoytrip.group.exception.GroupException;
import com.enjoytrip.group.domain.Group;
import com.enjoytrip.group.mapper.GroupMapper;
import com.enjoytrip.group.mapper.GroupUserMapper;
import com.enjoytrip.group.service.command.GroupCreateCommand;
import com.enjoytrip.group.service.command.UserJoinGroupCommand;
import com.enjoytrip.group.service.command.UserLeaveGroupCommand;
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
            // 그룹 정보 조회
            Group group = groupMapper.findByGroupId(command.getGroupId())
                .orElseThrow(() -> new GroupException("GROUP_NOT_FOUND", "그룹 없음", "해당 그룹을 찾을 수 없습니다."));

        // 현재 그룹의 참여자 수 조회
        int currentParticipants = groupUserMapper.countUsersInGroup(command.getGroupId());
        if (currentParticipants >= group.getMaxParticipant()) {
            throw new GroupException(
                    "GROUP_FULL",
                    "그룹 참여 불가",
                    String.format("해당 그룹은 최대 인원(%d명)을 초과하였습니다.", group.getMaxParticipant())
            );
        }

        int result = groupUserMapper.insertGroupUser(command.getUserId(), command.getGroupId());

        if (result == 0){
            throw new GroupException("GROUP_JOIN_FAILED", "그룹 가입 실패", "그룹 가입에 실패하였습니다.");
        }
    }

    // 유저가 참여한 그룹 목록을 조회하는 메서드
    @Transactional
    public List<GroupDTO> getUserGroups(Long userId) {
        List<Group> groups = groupUserMapper.findGroupsByUserId(userId);
        return groups.stream()
                .map(GroupDTO::from)
                .toList(); // Group -> GroupDTO 변환
    }

    // 페이징 처리된 그룹 목록 조회
//    public List<GroupDTO> getGroupsWithPagination(int page, int size) {
//        int offset = page * size; // 시작 위치 계산
//        List<Group> groups = groupMapper.selectAllGroupsWithPagination(size, offset);
//        return groups.stream()
//                .map(GroupDTO::from)
//                .toList(); // Group -> GroupDTO 변환
//    }

    public PagedResponse<GroupDTO> getGroupsWithPagination(int page, int size) {
        int offset = page * size; // OFFSET 계산
        List<Group> groups = groupMapper.selectAllGroupsWithPagination(size, offset); // 데이터 조회
        int totalElements = groupMapper.getTotalGroupsCount(); // 전체 데이터 개수 조회
        int totalPages = (int) Math.ceil((double) totalElements / size); // 총 페이지 수 계산

        return new PagedResponse<>(
                groups.stream()
                .map(GroupDTO::from)
                .toList(),
                totalPages,
                totalElements,
                page,
                size
        );
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

    @Transactional
    public void leaveGroup(UserLeaveGroupCommand command) {
        int result = groupUserMapper.deleteGroupUser(command.getUserId(), command.getGroupId());
        if (result == 0) {
            throw new GroupException(
                    "GROUP_LEAVE_FAILED",
                    "그룹 탈퇴 실패",
                    "그룹 탈퇴에 실패하였습니다. 그룹에 가입하지 않았거나 잘못된 요청입니다."
            );
        }
    }

}
