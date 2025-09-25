package com.example.peer_to_peer.dto;

import lombok.Data;
import java.util.List;

@Data
public class  UserResponseDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String bio;
    private String location;
    private List<String> skillsCanTeach;
    private List<String> skillsWantLearn;
}
