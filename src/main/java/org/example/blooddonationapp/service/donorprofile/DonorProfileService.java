package org.example.blooddonationapp.service.donorprofile;


import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileDto;
import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileResponseDto;
import org.example.blooddonationapp.controller.donorprofile.dto.GetDonorProfileDto;
import org.example.blooddonationapp.controller.donorprofile.dto.UpdateDonorProfileDto;
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

    public CreateDonorProfileResponseDto createDonorProfile(CreateDonorProfileDto dto) {
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new DonorNotFoundError(dto.getUserId()));

        DonorProfileEntity profile = new DonorProfileEntity();
        profile.setUser(user);
        profile.setBloodType(dto.getBloodType());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setGender(dto.getGender());
        profile.setAddress(dto.getAddress());
        profile.setLastDonationDate(dto.getLastDonationDate());

        DonorProfileEntity saved = donorProfileRepository.save(profile);

        return new CreateDonorProfileResponseDto(
                saved.getId(),
                saved.getBloodType(),
                saved.getDateOfBirth(),
                saved.getGender(),
                saved.getAddress(),
                saved.getLastDonationDate()
        );
    }

    public GetDonorProfileDto getDonorById(Long userId) {
        DonorProfileEntity test = donorProfileRepository.findByDonorId(userId)
                .orElseThrow(() -> new DonorNotFoundError(userId));

        return new GetDonorProfileDto(
                test.getId(),
                test.getBloodType(),
                test.getDateOfBirth(),
                test.getGender(),
                test.getAddress(),
                test.getLastDonationDate()
        );
    }

    public UpdateDonorProfileDto updateDonorProfile(Long userId, UpdateDonorProfileDto dto) {
        DonorProfileEntity test = donorProfileRepository.findByDonorId(userId)
                .orElseThrow(() -> new DonorNotFoundError(userId));

        test.setAddress(dto.getAddress());
        test.setBloodType(dto.getBloodType());
        test.setGender(dto.getGender());
        test.setDateOfBirth(dto.getDateOfBirth());
        test.setLastDonationDate(dto.getLastDonationDate());

        DonorProfileEntity updated = donorProfileRepository.save(test);;

        return new UpdateDonorProfileDto(
                updated.getId(),
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
}