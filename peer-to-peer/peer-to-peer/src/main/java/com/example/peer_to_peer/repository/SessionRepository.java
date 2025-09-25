package com.example.peer_to_peer.repository;

import com.example.peer_to_peer.entity.User;
import com.example.peer_to_peer.entity.Skill;
import com.example.peer_to_peer.entity.Session;
import com.example.peer_to_peer.entity.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Session Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByTeacherId(Long teacherId);
    List<Session> findByLearnerId(Long learnerId);
}