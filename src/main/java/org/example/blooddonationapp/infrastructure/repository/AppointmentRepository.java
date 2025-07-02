package org.example.blooddonationapp.infrastructure.repository;

import org.example.blooddonationapp.commontypes.AppointmentStatus;
import org.example.blooddonationapp.infrastructure.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    Optional<AppointmentEntity> findById(Long Id);
    List<AppointmentEntity> findAllByUserId(Long userId);

    long countByAppointmentStatus(AppointmentStatus appointmentStatus);

    List<AppointmentEntity> findByAppointmentStatus(AppointmentStatus appointmentStatus);

    List<AppointmentEntity> findByBookedAtBetween(LocalDateTime start, LocalDateTime end);
}
