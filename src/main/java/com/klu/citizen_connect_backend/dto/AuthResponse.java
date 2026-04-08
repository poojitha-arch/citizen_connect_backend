package com.klu.citizen_connect_backend.dto;


public class AuthResponse {

    private String message;
    private Long userId;
    private String username;
    private String email;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String message, Long userId, String username, String email, String role) {
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}