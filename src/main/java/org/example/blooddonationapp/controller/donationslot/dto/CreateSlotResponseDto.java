package org.example.blooddonationapp.controller.donationslot.dto;

import java.time.LocalDateTime;

public class CreateSlotResponseDto {
    private Long id;
    private String location;
    private LocalDateTime dateTime;
    private int capacity;

    public CreateSlotResponseDto(Long id, String location, LocalDateTime dateTime, int capacity) {
        this.id = id;
        this.location = location;
        this.dateTime = dateTime;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
