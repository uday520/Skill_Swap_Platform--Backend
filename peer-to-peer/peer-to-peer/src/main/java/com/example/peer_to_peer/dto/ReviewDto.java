package com.example.peer_to_peer.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long userId;
    private Long sessionId;
    private int rating;
    private String comment;
}
