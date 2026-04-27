package com.klu.citizen_connect_backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.citizen_connect_backend.entity.Complaint;
import com.klu.citizen_connect_backend.entity.ComplaintStatus;
import com.klu.citizen_connect_backend.exception.ResourceNotFountException;
import com.klu.citizen_connect_backend.repository.ComplaintRepository;

@Service
public class ModeratorService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint updateStatus(Long complaintId, String status) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFountException("Complaint not found"));

        complaint.setStatus(ComplaintStatus.valueOf(status.toUpperCase()));
        return complaintRepository.save(complaint);
    }
}