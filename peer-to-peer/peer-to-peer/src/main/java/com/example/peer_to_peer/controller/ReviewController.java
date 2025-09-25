package com.example.peer_to_peer.controller;

import com.example.peer_to_peer.dto.ReviewDto;
import com.example.peer_to_peer.dto.ReviewResponseDto;
import com.example.peer_to_peer.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    public ReviewResponseDto addReview(@RequestBody ReviewDto dto) {
        return reviewService.addReview(dto);
    }

    // Reviews given by user
    @GetMapping("/user/{userId}")
    public List<ReviewResponseDto> getReviewsByUser(@PathVariable Long userId) {
        return reviewService.getReviewsByUser(userId);
    }

    // Reviews received by teacher
    @GetMapping("/teacher/{teacherId}")
    public List<ReviewResponseDto> getReviewsForTeacher(@PathVariable Long teacherId) {
        return reviewService.getReviewsForTeacher(teacherId);
    }
}
