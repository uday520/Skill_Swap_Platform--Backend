package com.example.peer_to_peer.service;

import com.example.peer_to_peer.entity.Skill;
import com.example.peer_to_peer.entity.User;
import com.example.peer_to_peer.repository.SkillRepository;
import com.example.peer_to_peer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    // Add multiple skills to a user
    public List<Skill> addSkillsToUser(Long userId, List<String> skillNames) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Skill> skillsToAdd = new ArrayList<>();
        for (String skillName : skillNames) {
            Skill skill = skillRepository.findByName(skillName)
                    .orElseGet(() -> skillRepository.save(Skill.builder().name(skillName).build()));
            skillsToAdd.add(skill);
        }

        // For example, adding to skillsCanTeach. You can change to skillsWantLearn if needed
        if (user.getSkillsCanTeach() == null) {
            user.setSkillsCanTeach(new ArrayList<>());
        }
        user.getSkillsCanTeach().addAll(skillsToAdd);
        userRepository.save(user);

        return skillsToAdd;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    public List<Skill> getAllSkillsExcludingUser(Long userId) {
        // Fetch all skills
        List<Skill> allSkills = skillRepository.findAll();

        // Fetch the user's skills
        User user = userRepository.findById(userId).orElseThrow();
        List<Skill> userSkills = user.getSkillsCanTeach();

        // Remove user's skills from all skills
        return allSkills.stream()
                .filter(skill -> !userSkills.contains(skill))
                .toList();
    }

    public void deleteSkillCompleted(long userId,String skillLearned) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // 2️⃣ Fetch the skill by name
        Skill skill = skillRepository.findByName(skillLearned)
                .orElseThrow(() -> new RuntimeException("Skill not found with name: " + skillLearned));

        // 3️⃣ Remove the skill from user's learned skills list
        boolean removed = user.getSkillsWantLearn().remove(skill);

        if (!removed) {
            throw new RuntimeException("User does not have this skill in learned skills");
        }

        // 4️⃣ Save the updated user
        userRepository.save(user);

    }
}
