package org.example.blooddonationapp.controller.medicaltests.dto;

import java.time.LocalDate;

public class UpdateMedicalTestDto {
    private Long id;
    private String testType;
    private String result;
    private LocalDate testDate;
    private String notes;

    public UpdateMedicalTestDto() {}

    public UpdateMedicalTestDto(Long id, String testType, String result, LocalDate testDate, String notes) {
        this.id = id;
        this.testType = testType;
        this.result = result;
        this.testDate = testDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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
