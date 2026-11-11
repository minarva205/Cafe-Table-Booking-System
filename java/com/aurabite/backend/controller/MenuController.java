package com.aurabite.backend.controller;

import com.aurabite.backend.model.MenuItem;
import com.aurabite.backend.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuItemRepository menuItemRepository;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems(@RequestParam(required = false) String category) {
        if (category != null && !category.trim().isEmpty() && !"ALL".equalsIgnoreCase(category)) {
            return ResponseEntity.ok(menuItemRepository.findByCategory(category));
        }
        return ResponseEntity.ok(menuItemRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem item) {
        MenuItem saved = menuItemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem updated) {
        return menuItemRepository.findById(id)
                .map(item -> {
                    item.setName(updated.getName());
                    item.setDescription(updated.getDescription());
                    item.setPrice(updated.getPrice());
                    item.setCategory(updated.getCategory());
                    item.setImageUrl(updated.getImageUrl());
                    item.setIsAvailable(updated.getIsAvailable());
                    item.setIsChefSpecial(updated.getIsChefSpecial());
                    item.setDietaryTag(updated.getDietaryTag());
                    item.setPrepTimeMinutes(updated.getPrepTimeMinutes());
                    return ResponseEntity.ok(menuItemRepository.save(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
