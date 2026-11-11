package com.aurabite.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact_inquiries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String subject;

    @Column(length = 1000, nullable = false)
    private String message;

    private String status = "UNREAD"; // "UNREAD", "RESPONDED", "ARCHIVED"

    private LocalDateTime createdAt = LocalDateTime.now();
}
