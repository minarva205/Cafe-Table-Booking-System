package com.aurabite.backend.controller;

import com.aurabite.backend.model.CafeTable;
import com.aurabite.backend.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableRepository tableRepository;

    @GetMapping
    public ResponseEntity<List<CafeTable>> getAllTables(@RequestParam(required = false) String zone) {
        if (zone != null && !zone.trim().isEmpty() && !"ALL".equalsIgnoreCase(zone)) {
            return ResponseEntity.ok(tableRepository.findByZone(zone));
        }
        return ResponseEntity.ok(tableRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CafeTable> getTableById(@PathVariable Long id) {
        return tableRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CafeTable> updateTableStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        return tableRepository.findById(id)
                .map(table -> {
                    table.setStatus(newStatus);
                    return ResponseEntity.ok(tableRepository.save(table));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
