package org.example.blooddonationapp.controller.medicaltests.dto;

import org.example.blooddonationapp.commontypes.MedicalTestResult;
import org.example.blooddonationapp.commontypes.MedicalTestType;

import java.time.LocalDate;

public class CreateMedicalTestDto {
    private Long donorId;
    private MedicalTestType testType;
    private MedicalTestResult result;
    private LocalDate testDate;
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
