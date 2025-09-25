package com.example.peer_to_peer.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String location;

    private List<String> skillsCanTeach;   // e.g. ["Java", "Spring Boot"]
    private List<String> skillsWantLearn;  // e.g. ["React", "Docker"]
}
