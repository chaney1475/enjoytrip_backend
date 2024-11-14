package com.enjoytrip.attraction.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAttraction {
    private Long attractionUserId;
    private Long userId;
    private Long attractionId;
    private String sigunguName;
    private LocalDateTime createdAt;
}
