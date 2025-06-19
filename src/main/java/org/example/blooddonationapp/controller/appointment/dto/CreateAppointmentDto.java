package org.example.blooddonationapp.controller.appointment.dto;

public class CreateAppointmentDto {
    private Long slotId;
    private Long userId;

    public CreateAppointmentDto() {}

    public CreateAppointmentDto(Long slotId, Long userId) {
        this.userId = userId;
        this.slotId = slotId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
