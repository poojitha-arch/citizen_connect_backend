package com.klu.citizen_connect_backend.dto;



public class ComplaintResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String location;
    private String status;
    private String createdAt;
    private String citizenName;
    private Long userId;

    public ComplaintResponse() {
    }

    public ComplaintResponse(Long id, String title, String description, String category,
                             String location, String status, String createdAt,
                             String citizenName, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
        this.citizenName = citizenName;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}