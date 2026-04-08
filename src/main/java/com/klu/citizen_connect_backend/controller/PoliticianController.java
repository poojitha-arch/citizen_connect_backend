package com.klu.citizen_connect_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.citizen_connect_backend.dto.ComplaintResponse;
import com.klu.citizen_connect_backend.service.ComplaintService;
import com.klu.citizen_connect_backend.service.PoliticianService;

@RestController
@RequestMapping("/api/politician")
public class PoliticianController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private PoliticianService politicianService;

    @GetMapping("/complaints")
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PutMapping("/complaints/{id}/in-progress")
    public ResponseEntity<?> markInProgress(@PathVariable Long id) {
        return ResponseEntity.ok(politicianService.markInProgress(id));
    }

    @PutMapping("/complaints/{id}/resolve")
    public ResponseEntity<?> markResolved(@PathVariable Long id) {
        return ResponseEntity.ok(politicianService.markResolved(id));
    }
}