package com.example.peer_to_peer.service;

import com.example.peer_to_peer.dto.ReviewDto;
import com.example.peer_to_peer.dto.ReviewResponseDto;
import com.example.peer_to_peer.entity.Review;
import com.example.peer_to_peer.entity.Session;
import com.example.peer_to_peer.entity.User;
import com.example.peer_to_peer.repository.ReviewRepository;
import com.example.peer_to_peer.repository.SessionRepository;
import com.example.peer_to_peer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    // ⭐ New method to add a review
    public ReviewResponseDto addReview(ReviewDto reviewDto) {
        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Session session = sessionRepository.findById(reviewDto.getSessionId())
                .orElseThrow(() -> new RuntimeException("Session not found"));

        Review review = Review.builder()
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment())
                .user(user)
                .session(session)
                .build();

        Review saved = reviewRepository.save(review);

        return new ReviewResponseDto(
                saved.getId(),
                saved.getRating(),
                saved.getComment(),
                saved.getUser() != null ? saved.getUser().getId() : null,
                saved.getSession() != null && saved.getSession().getTeacher() != null
                        ? saved.getSession().getTeacher().getId()
                        : null
        );
    }

    // Reviews given by user (fetch teacherId, hide userId)
    public List<ReviewResponseDto> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(r -> new ReviewResponseDto(
                        r.getId(),
                        r.getRating(),
                        r.getComment(),
                        null, // hide userId
                        r.getSession() != null && r.getSession().getTeacher() != null
                                ? r.getSession().getTeacher().getId()
                                : null
                ))
                .collect(Collectors.toList());
    }

    // Reviews received by teacher (fetch userId, hide teacherId)
    public List<ReviewResponseDto> getReviewsForTeacher(Long teacherId) {
        return reviewRepository.findBySession_Teacher_Id(teacherId).stream()
                .map(r -> new ReviewResponseDto(
                        r.getId(),
                        r.getRating(),
                        r.getComment(),
                        r.getUser() != null ? r.getUser().getId() : null,
                        null // hide teacherId
                ))
                .collect(Collectors.toList());
    }

    // All reviews
    public List<ReviewResponseDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(r -> new ReviewResponseDto(
                        r.getId(),
                        r.getRating(),
                        r.getComment(),
                        r.getUser() != null ? r.getUser().getId() : null,
                        r.getSession() != null && r.getSession().getTeacher() != null
                                ? r.getSession().getTeacher().getId()
                                : null
                ))
                .collect(Collectors.toList());
    }
}
