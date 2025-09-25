package com.example.peer_to_peer.service;

import com.example.peer_to_peer.dto.LoginRequest;
import com.example.peer_to_peer.dto.LoginResponse;
import com.example.peer_to_peer.entity.User;
import com.example.peer_to_peer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        if (userOpt.isEmpty()) {
            return new LoginResponse(null, null, "User not found", false);
        }

        User user = userOpt.get();

        // Compare the provided password with the stored hashed password
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse(
                user.getId(),
                user.getUsername(),
                "Login successful",
                true
            );
        } else {
            return new LoginResponse(null, null, "Invalid password", false);
        }
    }
}
