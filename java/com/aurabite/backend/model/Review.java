package com.aurabite.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    private String avatarUrl;

    @Column(nullable = false)
    private Integer rating; // 1 to 5

    @Column(length = 1000, nullable = false)
    private String comment;

    private String tag; // "Verified Guest", "VIP Member", "Food Critic"

    private LocalDateTime createdAt = LocalDateTime.now();
}
