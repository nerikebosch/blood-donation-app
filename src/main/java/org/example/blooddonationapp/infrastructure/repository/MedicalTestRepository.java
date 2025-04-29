package org.example.blooddonationapp.infrastructure.repository;

import org.example.blooddonationapp.infrastructure.entity.DonorProfileEntity;
import org.example.blooddonationapp.infrastructure.entity.MedicalTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalTestRepository extends JpaRepository<MedicalTestEntity, Long> {
    List<MedicalTestEntity> findByDonor(DonorProfileEntity donor);
}
