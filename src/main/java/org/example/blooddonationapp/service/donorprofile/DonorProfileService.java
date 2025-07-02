package org.example.blooddonationapp.service.donorprofile;


import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileDto;
import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileResponseDto;
import org.example.blooddonationapp.controller.donorprofile.dto.GetDonorProfileDto;
import org.example.blooddonationapp.controller.donorprofile.dto.UpdateDonorProfileDto;
import org.example.blooddonationapp.infrastructure.entity.AuthEntity;
import org.example.blooddonationapp.infrastructure.entity.DonorProfileEntity;
import org.example.blooddonationapp.infrastructure.entity.UserEntity;
import org.example.blooddonationapp.infrastructure.repository.AuthRepository;
import org.example.blooddonationapp.infrastructure.repository.DonorProfileRepository;
import org.example.blooddonationapp.infrastructure.repository.UserRepository;
import org.example.blooddonationapp.service.donorprofile.error.DonorNotFoundError;
import org.example.blooddonationapp.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorProfileService {
    private final DonorProfileRepository donorProfileRepository;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    @Autowired
    public DonorProfileService(DonorProfileRepository donorProfileRepository, UserRepository userRepository, AuthRepository authRepository) {
        this.donorProfileRepository = donorProfileRepository;
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    public CreateDonorProfileResponseDto createDonorProfile(CreateDonorProfileDto dto) {
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new DonorNotFoundError(dto.getUserId()));

        DonorProfileEntity profile = new DonorProfileEntity();
        profile.setUser(user);
        profile.setEthnicity(dto.getEthnicity());
        profile.setBloodType(dto.getBloodType());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setGender(dto.getGender());
        profile.setAddress(dto.getAddress());
        profile.setLastDonationDate(dto.getLastDonationDate());

        DonorProfileEntity saved = donorProfileRepository.save(profile);

        return new CreateDonorProfileResponseDto(
                saved.getId(),
                saved.getEthnicity(),
                saved.getBloodType(),
                saved.getDateOfBirth(),
                saved.getGender(),
                saved.getAddress(),
                saved.getLastDonationDate()
        );
    }

    public GetDonorProfileDto getDonorById(Long userId) {
        DonorProfileEntity test = donorProfileRepository.findById(userId)
                .orElseThrow(() -> new DonorNotFoundError(userId));

        return new GetDonorProfileDto(
                test.getId(),
                test.getEthnicity(),
                test.getBloodType(),
                test.getDateOfBirth(),
                test.getGender(),
                test.getAddress(),
                test.getLastDonationDate()
        );
    }

    public List<GetDonorProfileDto> getAllDonors() {
        return donorProfileRepository.findAll().stream()
                .map(profile -> new GetDonorProfileDto(
                        profile.getId(),
                        profile.getEthnicity(),
                        profile.getBloodType(),
                        profile.getDateOfBirth(),
                        profile.getGender(),
                        profile.getAddress(),
                        profile.getLastDonationDate()
                ))
                .toList();
    }


    public UpdateDonorProfileDto updateDonorProfile(Long userId, UpdateDonorProfileDto dto) {
        DonorProfileEntity test = donorProfileRepository.findById(userId)
                .orElseThrow(() -> new DonorNotFoundError(userId));

        test.setAddress(dto.getAddress());
        test.setEthnicity(dto.getEthnicity());
        test.setBloodType(dto.getBloodType());
        test.setGender(dto.getGender());
        test.setDateOfBirth(dto.getDateOfBirth());
        test.setLastDonationDate(dto.getLastDonationDate());

        DonorProfileEntity updated = donorProfileRepository.save(test);;

        return new UpdateDonorProfileDto(
                updated.getId(),
                updated.getEthnicity(),
                updated.getBloodType(),
                updated.getDateOfBirth(),
                updated.getGender(),
                updated.getAddress(),
                updated.getLastDonationDate()
        );
    }

    public void deleteDonorProfile(Long userId) {
        if (!donorProfileRepository.existsById(userId)) {
            throw new DonorNotFoundError(userId);
        }

        donorProfileRepository.deleteById(userId);
    }

    public GetDonorProfileDto getDonorProfileByUsername(String username) {
        AuthEntity auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFound.createWithUsername(username));
        UserEntity user = auth.getUser();

        DonorProfileEntity profile = donorProfileRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Donor profile not found"));

        GetDonorProfileDto dto = new GetDonorProfileDto();
        dto.setUserId(user.getId());
        dto.setBloodType(profile.getBloodType());
        dto.setEthnicity(profile.getEthnicity());
        dto.setGender(profile.getGender());
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setAddress(profile.getAddress());
        dto.setLastDonationDate(profile.getLastDonationDate());

        return dto;
    }

}