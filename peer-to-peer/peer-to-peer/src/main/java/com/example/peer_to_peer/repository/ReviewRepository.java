package com.example.peer_to_peer.repository;

import com.example.peer_to_peer.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Reviews given by user
    List<Review> findByUserId(Long userId);

    // Reviews for a teacher
    List<Review> findBySession_Teacher_Id(Long teacherId);

    // Check if review already exists (prevent duplicate)
    boolean existsByUserIdAndSessionId(Long userId, Long sessionId);
}
