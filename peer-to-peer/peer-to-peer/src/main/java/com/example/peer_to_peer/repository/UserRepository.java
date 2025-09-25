package com.example.peer_to_peer.repository;

import com.example.peer_to_peer.dto.UserResponseDto;
import com.example.peer_to_peer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBySkillsCanTeach_NameContaining(String skillName);

    // Added method to find user by username
    Optional<User> findByUsername(String username);


}
