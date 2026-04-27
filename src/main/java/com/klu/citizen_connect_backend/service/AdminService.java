package com.klu.citizen_connect_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.citizen_connect_backend.dto.DashboardStatsResponse;
import com.klu.citizen_connect_backend.entity.Complaint;
import com.klu.citizen_connect_backend.entity.ComplaintStatus;
import com.klu.citizen_connect_backend.exception.ResourceNotFountException;
import com.klu.citizen_connect_backend.repository.ComplaintRepository;

@Service
public class AdminService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public DashboardStatsResponse getDashboardStats() {
        long total = complaintRepository.count();
        long pending = complaintRepository.countByStatus(ComplaintStatus.PENDING);
        long inProgress = complaintRepository.countByStatus(ComplaintStatus.IN_PROGRESS);
        long resolved = complaintRepository.countByStatus(ComplaintStatus.RESOLVED);
        long rejected = complaintRepository.countByStatus(ComplaintStatus.REJECTED);

        return new DashboardStatsResponse(total, pending, inProgress, resolved, rejected);
    }

    public Complaint updateComplaintStatus(Long complaintId, String status) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFountException("Complaint not found"));

        complaint.setStatus(ComplaintStatus.valueOf(status.toUpperCase()));
        return complaintRepository.save(complaint);
    }
}