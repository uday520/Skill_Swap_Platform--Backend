package com.example.peer_to_peer.controller;

import com.example.peer_to_peer.entity.Skill;
import com.example.peer_to_peer.service.SkillService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    // Add multiple skills to a user these added skills will be on SKills to Teach
    @PutMapping("/add-to-user/{userId}")
    public List<Skill> addSkillsToUser(
            @PathVariable Long userId,
            @RequestBody SkillRequest skillRequest) {
        return skillService.addSkillsToUser(userId, skillRequest.getSkills());
    }

    @GetMapping("/All/{userId}")
    public List<Skill> getAllSkillsExcludingUser(@PathVariable Long userId) {
        return skillService.getAllSkillsExcludingUser(userId);
    }

    @DeleteMapping("/user/{userId}/delete/{skill_learned}")
    public String deleteSkillLearned(@PathVariable long userId, @PathVariable String skill_learned) {
        skillService.deleteSkillCompleted(userId, skill_learned);

        return "DELETED SUCCESSFULLY";
    }


    // DTO for receiving multiple skills
    @Data
    static class SkillRequest {
        private List<String> skills;
    }
}
