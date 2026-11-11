package com.aurabite.backend.repository;

import com.aurabite.backend.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategory(String category);
    List<MenuItem> findByIsAvailable(Boolean isAvailable);
    List<MenuItem> findByIsChefSpecial(Boolean isChefSpecial);
}
