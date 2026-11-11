package com.aurabite.backend.controller;

import com.aurabite.backend.model.ContactInquiry;
import com.aurabite.backend.repository.ContactInquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactInquiryRepository contactInquiryRepository;

    @PostMapping
    public ResponseEntity<ContactInquiry> submitInquiry(@RequestBody ContactInquiry inquiry) {
        inquiry.setStatus("UNREAD");
        ContactInquiry saved = contactInquiryRepository.save(inquiry);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ContactInquiry>> getInquiries() {
        return ResponseEntity.ok(contactInquiryRepository.findAllByOrderByCreatedAtDesc());
    }
}
