package com.klu.citizen_connect_backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.citizen_connect_backend.entity.Complaint;
import com.klu.citizen_connect_backend.entity.ComplaintStatus;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByUserId(Long userId);

    long countByStatus(ComplaintStatus status);
}