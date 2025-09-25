package com.example.peer_to_peer.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
