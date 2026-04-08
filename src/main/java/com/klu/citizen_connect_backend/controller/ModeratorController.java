package com.klu.citizen_connect_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.citizen_connect_backend.dto.ComplaintResponse;
import com.klu.citizen_connect_backend.dto.StatusUpdateRequest;
import com.klu.citizen_connect_backend.service.ComplaintService;
import com.klu.citizen_connect_backend.service.ModeratorService;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private ModeratorService moderatorService;

    @GetMapping("/complaints")
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PutMapping("/complaints/{id}/status")
    public ResponseEntity<?> updateComplaintStatus(@PathVariable Long id,
                                                   @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(moderatorService.updateStatus(id, request.getStatus()));
    }
}