package org.example.blooddonationapp.controller.appointment.dto;

import org.example.blooddonationapp.commontypes.AppointmentStatus;

public class UpdateAppointmentStatusDto {
    private AppointmentStatus appointmentStatus;

    public UpdateAppointmentStatusDto() {}

    public UpdateAppointmentStatusDto(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}
