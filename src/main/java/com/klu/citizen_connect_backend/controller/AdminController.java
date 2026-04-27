package com.klu.citizen_connect_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.citizen_connect_backend.dto.ComplaintResponse;
import com.klu.citizen_connect_backend.dto.DashboardStatsResponse;
import com.klu.citizen_connect_backend.dto.StatusUpdateRequest;
import com.klu.citizen_connect_backend.entity.Complaint;
import com.klu.citizen_connect_backend.service.AdminService;
import com.klu.citizen_connect_backend.service.ComplaintService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponse> getDashboardStats() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PutMapping("/complaints/{id}/status")
    public ResponseEntity<Complaint> updateComplaintStatus(@PathVariable Long id,
                                                           @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(adminService.updateComplaintStatus(id, request.getStatus()));
    }
}