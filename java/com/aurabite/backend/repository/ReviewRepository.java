package com.aurabite.backend.repository;

import com.aurabite.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByOrderByCreatedAtDesc();

    @Query("SELECT COALESCE(AVG(r.rating), 5.0) FROM Review r")
    double findAverageRating();
}
