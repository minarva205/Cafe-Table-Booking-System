package com.aurabite.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String category; // "Coffee & Brews", "Bakery & Pastries", "Artisanal Mains", "Beverages & Mocktails", "Desserts"

    private String imageUrl;

    private Boolean isAvailable = true;

    private Boolean isChefSpecial = false;

    private String dietaryTag; // "Vegetarian", "Vegan", "Gluten-Free", "Chef's Pick"

    private Double rating = 4.8;

    private Integer prepTimeMinutes = 15;
}
