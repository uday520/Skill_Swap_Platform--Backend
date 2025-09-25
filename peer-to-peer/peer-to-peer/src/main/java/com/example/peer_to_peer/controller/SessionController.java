package com.example.peer_to_peer.controller;

import com.example.peer_to_peer.dto.SessionDto;
import com.example.peer_to_peer.entity.Session;
import com.example.peer_to_peer.service.SessionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/request")
    public Session requestSession(@RequestBody SessionDto sessionDto) {
        return sessionService.requestSession(sessionDto);
    }

    @PostMapping("/updateStatus/{id}")
    public Session updateStatus(
            @PathVariable Long id,
            @RequestBody StatusRequest request) {
        return sessionService.updateSessionStatus(id, request.getStatus());
    }



    @GetMapping("/teacher/{id}")
    public List<Session> getSessionsByTeacher(@PathVariable Long id) {
        return sessionService.getSessionsByTeacher(id);
    }

    @GetMapping("/learner/{id}")
    public List<Session> getSessionsByLearner(@PathVariable Long id) {
        return sessionService.getSessionsByLearner(id);
    }
    @Data
    static class StatusRequest {
        private String status;
    }
}
