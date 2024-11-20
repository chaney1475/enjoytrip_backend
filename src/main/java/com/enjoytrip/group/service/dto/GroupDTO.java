package com.enjoytrip.group.service.dto;

import com.enjoytrip.attraction.service.dto.AttractionDto;
import com.enjoytrip.group.domain.Group;
import com.enjoytrip.user.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class GroupDTO {
    private Long groupId;
    private String title;
    private String description;
    private AttractionDto attraction;
    private UserDTO creator;
    private LocalDateTime createdAt;
    private Integer maxParticipant;

    public static GroupDTO from(Group group){
        return new GroupDTO(
                group.getGroupId(),
                group.getTitle(),
                group.getDescription(),
                AttractionDto.from(group.getAttraction()),
                UserDTO.from(group.getCreator()),
                group.getCreatedAt(),
                group.getMaxParticipant()
        );
    }
}
