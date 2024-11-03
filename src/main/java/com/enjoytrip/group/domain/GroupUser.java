package com.enjoytrip.group.domain;

import com.enjoytrip.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupUser {
    private Long groupUserId;          // GroupMember의 고유 ID
    private User user;        // 참여한 유저 정보
    private Group group;      // 참여한 그룹 정보
    private LocalDateTime joinedAt;    // 그룹에 참여한 시각
}
