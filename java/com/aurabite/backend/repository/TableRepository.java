package com.aurabite.backend.repository;

import com.aurabite.backend.model.CafeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<CafeTable, Long> {
    List<CafeTable> findByZone(String zone);
    List<CafeTable> findByStatus(String status);
    Optional<CafeTable> findByTableNumber(String tableNumber);
}
