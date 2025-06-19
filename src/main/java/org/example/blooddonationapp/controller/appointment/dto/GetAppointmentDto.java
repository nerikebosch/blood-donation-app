package org.example.blooddonationapp.controller.appointment.dto;

import org.example.blooddonationapp.commontypes.AppointmentStatus;
import org.example.blooddonationapp.controller.donationslot.dto.CreateSlotDto;

import java.time.LocalDateTime;

public class GetAppointmentDto {
    private Long id;
    private Long userId;
    private LocalDateTime bookedAt;
    private AppointmentStatus status;
    private CreateSlotDto slot;

    public GetAppointmentDto() {}

    public GetAppointmentDto(Long id, Long userId, LocalDateTime bookedAt, AppointmentStatus status, CreateSlotDto slot) {
        this.id = id;
        this.userId = userId;
        this.bookedAt = bookedAt;
        this.status = status;
        this.slot = slot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public CreateSlotDto getSlot() {
        return slot;
    }

    public void setSlot(CreateSlotDto slot) {
        this.slot = slot;
    }
}
