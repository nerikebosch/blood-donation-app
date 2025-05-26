package org.example.blooddonationapp.controller.appointment.dto;

public class CreateAppointmentDto {
    private Long slotId;

    public CreateAppointmentDto() {}

    public CreateAppointmentDto(Long slotId) {
        this.slotId = slotId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }
}
