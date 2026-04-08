package com.klu.citizen_connect_backend.dto;



public class ComplaintRequest {

    private String title;
    private String description;
    private String category;
    private String location;
    private Long userId;

    public ComplaintRequest() {
    }

    public ComplaintRequest(String title, String description, String category, String location, Long userId) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}