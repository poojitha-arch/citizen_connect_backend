package com.klu.citizen_connect_backend.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String username, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Citizen Connect - OTP Verification");
        message.setText(
                "Hello " + username + ",\n\n" +
                "Your OTP for Citizen Connect registration is: " + otp + "\n\n" +
                "This OTP is valid for 5 minutes.\n\n" +
                "Regards,\nCitizen Connect Team"
        );
        mailSender.send(message);
    }

    public void sendLoginEmail(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Citizen Connect - Login Alert");
        message.setText(
                "Hello " + username + ",\n\n" +
                "Your account was logged in successfully.\n\n" +
                "If this was not you, please reset your password immediately.\n\n" +
                "Regards,\nCitizen Connect Team"
        );
        mailSender.send(message);
    }
}