package org.example.blooddonationapp.infrastructure.repository;

import org.example.blooddonationapp.infrastructure.entity.DonationSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationSlotRepository extends JpaRepository<DonationSlotEntity, Long> {
}
