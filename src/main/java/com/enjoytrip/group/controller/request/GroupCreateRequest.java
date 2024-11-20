package com.enjoytrip.group.controller.request;

import com.enjoytrip.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class GroupCreateRequest {
    private String title;
    private String description;
    private Long attractionId;
    private Integer maxParticipant;
}
