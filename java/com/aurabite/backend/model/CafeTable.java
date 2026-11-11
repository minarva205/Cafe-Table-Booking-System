package com.aurabite.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cafe_tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CafeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tableNumber; // e.g., "T-01"

    @Column(nullable = false)
    private Integer capacity; // e.g., 2, 4, 6, 8

    @Column(nullable = false)
    private String zone; // "Window View", "Main Dining", "Patio Garden", "VIP Lounge"

    @Column(nullable = false)
    private String status; // "AVAILABLE", "RESERVED", "MAINTENANCE"

    private Double minSpend;

    private String features; // e.g., "Near Fountain, Power Outlet, Soft Lighting"
}
