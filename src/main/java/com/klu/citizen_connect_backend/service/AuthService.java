package com.klu.citizen_connect_backend.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.citizen_connect_backend.dto.AuthResponse;
import com.klu.citizen_connect_backend.dto.LoginRequest;
import com.klu.citizen_connect_backend.dto.SignRequest;
import com.klu.citizen_connect_backend.entity.Role;
import com.klu.citizen_connect_backend.entity.User;
import com.klu.citizen_connect_backend.exception.BadRequestException;
import com.klu.citizen_connect_backend.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public AuthResponse register(SignRequest request) {
        if (request.getPassword() == null || request.getConfirmPassword() == null) {
            throw new BadRequestException("Password fields are required");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());

        try {
            user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } catch (Exception e) {
            throw new BadRequestException("Invalid role value");
        }

        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        return new AuthResponse(
                "Registration successful",
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole().name()
        );
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            throw new BadRequestException("User not found");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        try {
            emailService.sendLoginEmail(user.getEmail(), user.getUsername());
        } catch (Exception e) {
            System.out.println("Login email sending failed: " + e.getMessage());
        }

        return new AuthResponse(
                "Login successful",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}