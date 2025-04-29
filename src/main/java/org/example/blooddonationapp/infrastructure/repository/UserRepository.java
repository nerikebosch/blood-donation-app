package org.example.blooddonationapp.infrastructure.repository;

import org.example.blooddonationapp.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
