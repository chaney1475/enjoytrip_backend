package com.enjoytrip.group.service.command;

import com.enjoytrip.group.controller.request.GroupCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GroupCreateCommand {
    private String title;
    private String description;
    private Long attractionId;
    private Long creator;
    private Integer maxParticipant;

    static public GroupCreateCommand from(Long userId, GroupCreateRequest request) {
        return new GroupCreateCommand(
                request.getTitle(),
                request.getDescription(),
                request.getAttractionId(),
                userId,
                request.getMaxParticipant()
        );
    }
}
