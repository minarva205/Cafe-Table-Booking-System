package com.aurabite.backend.controller;

import com.aurabite.backend.model.Review;
import com.aurabite.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @GetMapping
    public ResponseEntity<List<Review>> getReviews() {
        return ResponseEntity.ok(reviewRepository.findAllByOrderByCreatedAtDesc());
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        if (review.getAvatarUrl() == null || review.getAvatarUrl().trim().isEmpty()) {
            review.setAvatarUrl("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=120&q=80");
        }
        if (review.getTag() == null || review.getTag().trim().isEmpty()) {
            review.setTag("Verified Guest");
        }
        Review saved = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
