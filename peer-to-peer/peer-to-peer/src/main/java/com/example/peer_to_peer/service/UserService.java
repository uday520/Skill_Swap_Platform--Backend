package com.example.peer_to_peer.service;

import com.example.peer_to_peer.dto.UserDto;
import com.example.peer_to_peer.dto.UserResponseDto;
import com.example.peer_to_peer.entity.Skill;
import com.example.peer_to_peer.entity.User;
import com.example.peer_to_peer.repository.SkillRepository;
import com.example.peer_to_peer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Register User with Skill Names
    public User createUser(UserDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword())) // Now properly encrypted
                .bio(dto.getBio())
                .location(dto.getLocation())
                .build();

        // Convert skill names → Skill entities
        List<Skill> teachSkills = dto.getSkillsCanTeach().stream()
                .map(name -> skillRepository.findByName(name)
                        .orElseGet(() -> skillRepository.save(Skill.builder().name(name).build())))
                .collect(Collectors.toList());

        List<Skill> learnSkills = dto.getSkillsWantLearn().stream()
                .map(name -> skillRepository.findByName(name)
                        .orElseGet(() -> skillRepository.save(Skill.builder().name(name).build())))
                .collect(Collectors.toList());

        user.setSkillsCanTeach(teachSkills);
        user.setSkillsWantLearn(learnSkills);

        return userRepository.save(user);
    }

    // ✅ Get clean response DTO
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return mapToResponseDto(user);
    }

    // ✅ Search by skill name
    public List<UserResponseDto> searchUsersBySkill(String skillName) {
        List<User> users = userRepository.findBySkillsCanTeach_NameContaining(skillName);
        return users.stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    // 🔹 Helper mapper
    private UserResponseDto mapToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setBio(user.getBio());
        dto.setLocation(user.getLocation());
        dto.setSkillsCanTeach(
                user.getSkillsCanTeach().stream()
                        .map(Skill::getName)
                        .collect(Collectors.toList())
        );
        dto.setSkillsWantLearn(
                user.getSkillsWantLearn().stream()
                        .map(Skill::getName)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    // New method to find a user by username (needed for login)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));


    }

}
