package org.example.blooddonationapp.controller.medicaltests.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.example.blooddonationapp.commontypes.MedicalTestResult;
import org.example.blooddonationapp.commontypes.MedicalTestType;

import java.time.LocalDate;

public class CreateMedicalTestDto {

    @NotNull(message = "Donor ID is required.")
    private Long donorId;

    @NotNull(message = "Test type is required.")
    private MedicalTestType testType;

    @NotNull(message = "Test result is required.")
    private MedicalTestResult result;

    @NotNull(message = "Test date is required.")
    @PastOrPresent(message = "Test date cannot be in the future.")
    private LocalDate testDate;

    @Size(max = 500, message = "Notes cannot exceed 500 characters.")
    private String notes;


    public CreateMedicalTestDto(Long donorId, MedicalTestType testType, MedicalTestResult result, LocalDate testDate, String notes) {
        this.donorId = donorId;
        this.testType = testType;
        this.result = result;
        this.testDate = testDate;
        this.notes = notes;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public MedicalTestType getTestType() {
        return testType;
    }

    public void setTestType(MedicalTestType testType) {
        this.testType = testType;
    }

    public MedicalTestResult getResult() {
        return result;
    }

    public void setResult(MedicalTestResult result) {
        this.result = result;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
