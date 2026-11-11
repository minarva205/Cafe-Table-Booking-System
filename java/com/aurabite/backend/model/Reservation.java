package com.aurabite.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String bookingRef; // e.g. "AB-78291"

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String customerPhone;

    @Column(nullable = false)
    private LocalDate reservationDate;

    @Column(nullable = false)
    private String timeSlot; // e.g., "18:00 - 20:00"

    @Column(nullable = false)
    private Integer guestCount;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private CafeTable table;

    @Column(nullable = false)
    private String status; // "PENDING", "CONFIRMED", "CANCELLED", "COMPLETED"

    @Column(length = 500)
    private String specialRequests; // e.g., "Anniversary table setup with candles"

    private Double totalPreorderAmount = 0.0;

    private LocalDateTime createdAt = LocalDateTime.now();
}
