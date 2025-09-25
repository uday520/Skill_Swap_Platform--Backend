package com.example.peer_to_peer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "reviews",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "session_id"}) // prevent duplicates
)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating; // 1-5

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Who gave the review

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session; // Which session this review belongs to
}
