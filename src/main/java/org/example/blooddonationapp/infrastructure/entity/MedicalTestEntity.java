package org.example.blooddonationapp.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "medical_test", schema = "blood_donation")
public class MedicalTestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private DonorProfileEntity donor;

    @Column(name = "test_type", nullable = false)
    private String testType;        // e.g. Hemoglobin, HIV, Blood Pressure

    @Column(name = "result", nullable = false)
    private String result;          // e.g. Normal, Low, Negative

    @Column(name = "test_date", nullable = false)
    private LocalDate testDate;

    @Column(name = "notes")
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DonorProfileEntity getDonor() {
        return donor;
    }

    public void setDonor(DonorProfileEntity donor) {
        this.donor = donor;
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
