package com.klu.citizen_connect_backend.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.citizen_connect_backend.dto.ComplaintRequest;
import com.klu.citizen_connect_backend.dto.ComplaintResponse;
import com.klu.citizen_connect_backend.entity.Complaint;
import com.klu.citizen_connect_backend.entity.User;
import com.klu.citizen_connect_backend.exception.ResourceNotFountException;
import com.klu.citizen_connect_backend.exception.ResourceNotFountException;
import com.klu.citizen_connect_backend.repository.ComplaintRepository;
import com.klu.citizen_connect_backend.repository.UserRepository;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private UserRepository userRepository;

    public ComplaintResponse createComplaint(ComplaintRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFountException("User not found"));

        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setLocation(request.getLocation());
        complaint.setUser(user);

        Complaint savedComplaint = complaintRepository.save(complaint);
        return mapToResponse(savedComplaint);
    }

    public List<ComplaintResponse> getAllComplaints() {
        List<Complaint> complaints = complaintRepository.findAll();
        List<ComplaintResponse> responseList = new ArrayList<>();

        for (Complaint complaint : complaints) {
            responseList.add(mapToResponse(complaint));
        }

        return responseList;
    }

    public List<ComplaintResponse> getComplaintsByUserId(Long userId) {
        List<Complaint> complaints = complaintRepository.findByUserId(userId);
        List<ComplaintResponse> responseList = new ArrayList<>();

        for (Complaint complaint : complaints) {
            responseList.add(mapToResponse(complaint));
        }

        return responseList;
    }

    public ComplaintResponse getComplaintById(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFountException("Complaint not found"));

        return mapToResponse(complaint);
    }

    private ComplaintResponse mapToResponse(Complaint complaint) {
        String citizenName = complaint.getUser() != null ? complaint.getUser().getUsername() : null;
        Long userId = complaint.getUser() != null ? complaint.getUser().getId() : null;

        return new ComplaintResponse(
                complaint.getId(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getCategory(),
                complaint.getLocation(),
                complaint.getStatus().name(),
                complaint.getCreatedAt() != null ? complaint.getCreatedAt().toString() : null,
                citizenName,
                userId
        );
    }
}