package com.example.peer_to_peer.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SessionDto {
    private Long teacherId;
    private Long learnerId;
    private Long skillId;
    private LocalDateTime sessionTime;
}
