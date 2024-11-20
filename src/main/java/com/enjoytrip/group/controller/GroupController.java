package com.enjoytrip.group.controller;

import com.enjoytrip.auth.annotation.Authenticated;
import com.enjoytrip.auth.annotation.LoginRequired;
import com.enjoytrip.auth.domain.AuthClaims;
import com.enjoytrip.group.controller.request.GroupCreateRequest;
import com.enjoytrip.group.controller.response.GroupResponse;
import com.enjoytrip.group.domain.Group;
import com.enjoytrip.group.service.GroupService;
import com.enjoytrip.group.service.command.GroupCreateCommand;
import com.enjoytrip.group.service.command.UserJoinGroupCommand;
import com.enjoytrip.group.service.dto.GroupDTO;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    //유저가 그룹 만들기
    @LoginRequired
    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@Authenticated AuthClaims claims, @RequestBody GroupCreateRequest request) {
        GroupResponse group = GroupResponse.from(
                groupService.createGroup(GroupCreateCommand.from(claims.getUserId(), request))
        );
        return ResponseEntity.created(URI.create("api/groups/" + group.getGroupId())).body(group);
    }

    // 그룹 아이디로 그룹 단건 조회
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponse> getGroup(
            @Parameter(description = "groupId 로 그룹 조회하기", example = "123")
            @PathVariable Long groupId) {
        GroupResponse group = GroupResponse.from(groupService.getGroup(groupId));
        return ResponseEntity.ok(group);
    }

    // 그룹 전체 목록 조회
    @GetMapping
    public ResponseEntity<List<GroupResponse>> getGroups() {
        List<GroupDTO> groups = groupService.getGroups();

        List<GroupResponse> list = groups.stream()
                .map(GroupResponse::from)
                .toList();

        return ResponseEntity.ok(list);
    }

    @LoginRequired
    // 유저가 그룹에 참여
    @PostMapping("/{groupId}/join")
    public ResponseEntity<String> joinGroup(@Authenticated AuthClaims claims, @PathVariable Long groupId) {

        groupService.joinGroup(
                UserJoinGroupCommand.from(
                        claims.getUserId(),
                        groupId));

        return ResponseEntity.ok("그룹 유저 조인 성공");
    }

    // 유저가 참여한 그룹 목록을 조회하는 엔드포인트
    @LoginRequired
    @GetMapping("/user")
    public ResponseEntity<List<Group>> getGroupsByUserId(@Parameter(hidden = true) @Authenticated AuthClaims claims) {
        List<Group> groups = groupService.getUserGroups(claims.getUserId());
        return ResponseEntity.ok(groups);
    }
}
