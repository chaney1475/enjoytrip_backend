package com.enjoytrip.attraction.domain;

import com.enjoytrip.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionUser {
    private Long attractionUserId;
    private Long attractionId;
    private Long userId;
    private LocalDateTime createdAt;
}
