package com.example.peer_to_peer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private int rating;
    private String comment;
    private Long userId;    // Only when teacher fetches reviews
    private Long teacherId; // Only when user fetches reviews
}
