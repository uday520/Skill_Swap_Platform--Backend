package com.example.peer_to_peer.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SessionResponseDto {
    private Long id;
    private Long teacherId;
    private Long learnerId;
    private Long skillId;
    private LocalDateTime sessionTime;
    private String status;
}
