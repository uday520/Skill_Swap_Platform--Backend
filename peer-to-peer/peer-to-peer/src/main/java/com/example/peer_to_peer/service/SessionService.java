package com.example.peer_to_peer.service;

import com.example.peer_to_peer.dto.SessionDto;
import com.example.peer_to_peer.entity.Session;
import com.example.peer_to_peer.entity.Skill;
import com.example.peer_to_peer.entity.User;
import com.example.peer_to_peer.repository.SessionRepository;
import com.example.peer_to_peer.repository.SkillRepository;
import com.example.peer_to_peer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public Session requestSession(SessionDto dto) {
        User teacher = userRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with id " + dto.getTeacherId()));

        User learner = userRepository.findById(dto.getLearnerId())
                .orElseThrow(() -> new RuntimeException("Learner not found with id " + dto.getLearnerId()));

        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found with id " + dto.getSkillId()));

        Session session = Session.builder()
                .teacher(teacher)
                .learner(learner)
                .skill(skill)
                .sessionTime(dto.getSessionTime())
                .status("REQUESTED")
                .build();
        return sessionRepository.save(session);
    }

    public Session updateSessionStatus(Long sessionId, String status) {
        Session session = sessionRepository.findById(sessionId).orElseThrow();
        session.setStatus(status);
        return sessionRepository.save(session);
    }

    public List<Session> getSessionsByTeacher(Long teacherId) {
        return sessionRepository.findByTeacherId(teacherId);
    }

    public List<Session> getSessionsByLearner(Long learnerId) {
        return sessionRepository.findByLearnerId(learnerId);
    }
}
