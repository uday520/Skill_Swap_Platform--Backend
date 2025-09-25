package com.example.peer_to_peer.controller;

import com.example.peer_to_peer.dto.UserResponseDto;
import com.example.peer_to_peer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/by-id/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search/{Keyword}")
    public List<UserResponseDto> searchBySkill(@PathVariable String Keyword) {
      return userService.searchUsersBySkill(Keyword);
    }

}
