package org.example.blooddonationapp.infrastructure.entity;

import jakarta.persistence.*;
import org.example.blooddonationapp.commontypes.AppointmentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment", schema = "blood_donation")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private DonationSlotEntity slot;

    @Column(name = "booked_at", nullable = false)
    private LocalDateTime bookedAt;

    @Column(name = "appointment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public DonationSlotEntity getSlot() {
        return slot;
    }

    public void setSlot(DonationSlotEntity slot) {
        this.slot = slot;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}