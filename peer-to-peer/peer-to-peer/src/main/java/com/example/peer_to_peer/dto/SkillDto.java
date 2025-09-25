package com.example.peer_to_peer.dto;


import lombok.Data;
import java.util.List;

@Data
public class SkillDto {
    private List<String> names; // Multiple skill names
    private Long userId;        // User to associate skills with
}
