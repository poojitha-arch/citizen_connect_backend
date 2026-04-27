package com.klu.citizen_connect_backend.dto;

public class PendingUserData {

    private OtpRequest signupData;
    private String otp;
    private long expiryTime;

    public PendingUserData() {
    }

    public PendingUserData(OtpRequest signupData, String otp, long expiryTime) {
        this.signupData = signupData;
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public OtpRequest getSignupData() {
        return signupData;
    }

    public void setSignupData(OtpRequest signupData) {
        this.signupData = signupData;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }
}