package com.enjoytrip.group.controller.response;

import com.enjoytrip.user.service.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreatorResponse {
    private Long creatorId;
    private String nickname;
    private String avatarUrl;

    public static CreatorResponse from(UserDTO dto) {
        return CreatorResponse.builder()
                .creatorId(dto.getUserId())
                .nickname(dto.getNickname())
                .avatarUrl(dto.getAvatarUrl())
                .build();
    }
}
