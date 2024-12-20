package com.enjoytrip.group.controller.response;

import com.enjoytrip.attraction.controller.response.AttractionResponse;
import com.enjoytrip.group.service.dto.GroupDTO;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {
    private Long groupId;
    private String title;
    private String description;
    private AttractionResponse attraction;
    private CreatorResponse creator;
    private LocalDateTime createdAt;
    private Integer maxParticipant;

    public static GroupResponse from(GroupDTO dto){
        return GroupResponse.builder()
                .groupId(dto.getGroupId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .attraction(AttractionResponse.from(dto.getAttraction()))
                .creator(CreatorResponse.from(dto.getCreator()))
                .createdAt(dto.getCreatedAt())
                .maxParticipant(dto.getMaxParticipant())
                .build();
    }
}
