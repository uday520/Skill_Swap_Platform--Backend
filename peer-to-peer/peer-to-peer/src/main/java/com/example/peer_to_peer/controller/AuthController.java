package com.example.peer_to_peer.controller;

import com.example.peer_to_peer.dto.LoginRequest;
import com.example.peer_to_peer.dto.LoginResponse;
import com.example.peer_to_peer.dto.UserDto;
import com.example.peer_to_peer.entity.User;
import com.example.peer_to_peer.service.AuthService;
import com.example.peer_to_peer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto request) {
        User savedUser = userService.createUser(request);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.authenticateUser(loginRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}
