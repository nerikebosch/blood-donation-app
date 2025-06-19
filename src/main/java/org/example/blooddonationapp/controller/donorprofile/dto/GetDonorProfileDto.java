package org.example.blooddonationapp.controller.donorprofile.dto;

import java.time.LocalDate;

public class GetDonorProfileDto {
    private Long userId;
    private String ethnicity;
    private String bloodType;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private LocalDate lastDonationDate;

    public GetDonorProfileDto() {}

    public GetDonorProfileDto(Long userId, String ethnicity, String bloodType, LocalDate dateOfBirth, String gender, String address, LocalDate lastDonationDate) {
        this.userId = userId;
        this.ethnicity = ethnicity;
        this.bloodType = bloodType;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.lastDonationDate = lastDonationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }
}
