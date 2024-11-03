package com.enjoytrip.group.domain;

import com.enjoytrip.group.service.command.GroupCreateCommand;
import com.enjoytrip.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private Long groupId;
    private String title;
    private String description;
//    private Attraction attraction;
    private User creator;
    private LocalDateTime createdAt;
    private Integer maxParticipant;

    public static Group from(GroupCreateCommand command, LocalDateTime now) {
        return Group.builder()
                .title(command.getTitle())
                .description(command.getDescription())
                .creator(
                        User.builder()
                                .userId(command.getCreator())
                                .build()
                )
                .createdAt(now)
                .maxParticipant(command.getMaxParticipant())
                .build();
    }
}
