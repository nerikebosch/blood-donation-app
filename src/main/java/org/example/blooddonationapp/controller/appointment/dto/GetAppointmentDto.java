package org.example.blooddonationapp.controller.appointment.dto;

import org.example.blooddonationapp.commontypes.AppointmentStatus;

import java.time.LocalDateTime;

public class GetAppointmentDto {
    private Long id;
    private Long userId;
    private Long slotId;
    private LocalDateTime bookedAt;
    private AppointmentStatus status;

    public GetAppointmentDto() {}

    public GetAppointmentDto(Long id, Long userId, Long slotId, LocalDateTime bookedAt, AppointmentStatus status) {
        this.id = id;
        this.userId = userId;
        this.slotId = slotId;
        this.bookedAt = bookedAt;
        this.status = status;
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

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
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
}
