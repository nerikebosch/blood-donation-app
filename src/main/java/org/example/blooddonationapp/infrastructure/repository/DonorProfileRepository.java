package org.example.blooddonationapp.infrastructure.repository;

import org.example.blooddonationapp.infrastructure.entity.DonorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonorProfileRepository extends JpaRepository<DonorProfileEntity, Long> {

    Optional<DonorProfileEntity> findById(Long userId);
    Optional <DonorProfileEntity> deleteDonorProfileById(Long userId);
}
