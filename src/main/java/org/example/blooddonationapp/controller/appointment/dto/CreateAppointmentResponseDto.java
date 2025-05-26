package org.example.blooddonationapp.controller.appointment.dto;

import org.example.blooddonationapp.commontypes.AppointmentStatus;

import java.time.LocalDateTime;

public class CreateAppointmentResponseDto {
    private Long appointmentId;
    private Long userId;
    private Long slotId;
    private LocalDateTime bookedAt;
    private AppointmentStatus status;

    public CreateAppointmentResponseDto() {}

    public CreateAppointmentResponseDto(Long appointmentId, Long userId, Long slotId, LocalDateTime bookedAt, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.userId = userId;
        this.slotId = slotId;
        this.bookedAt = bookedAt;
        this.status = status;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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
