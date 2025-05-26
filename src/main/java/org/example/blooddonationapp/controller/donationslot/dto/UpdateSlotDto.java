package org.example.blooddonationapp.controller.donationslot.dto;

import java.time.LocalDateTime;

public class UpdateSlotDto {
    private LocalDateTime dateTime;
    private String location;
    private Integer capacity;

    public UpdateSlotDto(){}

    public UpdateSlotDto(LocalDateTime dateTime, String location, Integer capacity) {
        this.dateTime = dateTime;
        this.location = location;
        this.capacity = capacity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
