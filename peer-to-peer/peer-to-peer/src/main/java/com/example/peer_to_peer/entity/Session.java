package com.example.peer_to_peer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private User learner;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private LocalDateTime sessionTime;

    private String status; // REQUESTED, ACCEPTED, COMPLETED, CANCELLED
}
