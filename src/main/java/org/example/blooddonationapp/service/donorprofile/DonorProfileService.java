package org.example.blooddonationapp.service.donorprofile;


import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileDto;
import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileResponseDto;
import org.example.blooddonationapp.controller.donorprofile.dto.GetDonorProfileDto;
import org.example.blooddonationapp.infrastructure.entity.DonorProfileEntity;
import org.example.blooddonationapp.infrastructure.repository.DonorProfileRepository;
import org.example.blooddonationapp.infrastructure.repository.UserRepository;
import org.example.blooddonationapp.service.donorprofile.error.DonorNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorProfileService {
    private final DonorProfileRepository donorProfileRepository;
    private final UserRepository userRepository;

    @Autowired
    public DonorProfileService(DonorProfileRepository donorProfileRepository, UserRepository userRepository) {
        this.donorProfileRepository = donorProfileRepository;
        this.userRepository = userRepository;
    }

    public GetDonorProfileDto getProfileByUserId(Long userId) {
        var profile = donorProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Donor profile not found"));

        return new GetDonorProfileDto(
                profile.getBloodType(),
                profile.getDateOfBirth(),
                profile.getGender(),
                profile.getAddress(),
                profile.getLastDonationDate()
        );
    }

    public CreateDonorProfileResponseDto create(CreateDonorProfileDto dto) {
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new DonorNotFoundError(dto.getUserId()));

        DonorProfileEntity profile = new DonorProfileEntity();
        profile.setUser(user);
        profile.setBloodType(dto.getBloodType());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setGender(dto.getGender());
        profile.setAddress(dto.getAddress());
        profile.setLastDonationDate(dto.getLastDonationDate());

        var saved = donorProfileRepository.save(profile);

        return new CreateDonorProfileResponseDto(
                saved.getId(),
                saved.getBloodType(),
                saved.getDateOfBirth(),
                saved.getGender(),
                saved.getAddress(),
                saved.getLastDonationDate()
        );
    }

    public CreateDonorProfileResponseDto getByUserId(Long userId) {
        var profile = donorProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Donor profile not found"));

        return new CreateDonorProfileResponseDto(
                profile.getId(),
                profile.getBloodType(),
                profile.getDateOfBirth(),
                profile.getGender(),
                profile.getAddress(),
                profile.getLastDonationDate()
        );
    }
}