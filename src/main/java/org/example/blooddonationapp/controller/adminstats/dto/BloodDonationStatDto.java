package org.example.blooddonationapp.controller.adminstats.dto;

public class BloodDonationStatDto {
    private String bloodType;
    private long volume;

    public BloodDonationStatDto() {}

    public BloodDonationStatDto(String bloodType, long volume) {
        this.bloodType = bloodType;
        this.volume = volume;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long count) {
        this.volume = count;
    }
}
