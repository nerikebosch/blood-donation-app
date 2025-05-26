package org.example.blooddonationapp.service.medicaltests;


import org.example.blooddonationapp.controller.medicaltests.dto.CreateMedicalTestDto;
import org.example.blooddonationapp.controller.medicaltests.dto.CreateMedicalTestResponseDto;
import org.example.blooddonationapp.controller.medicaltests.dto.GetMedicalTestDto;
import org.example.blooddonationapp.controller.medicaltests.dto.UpdateMedicalTestDto;
import org.example.blooddonationapp.infrastructure.entity.DonorProfileEntity;
import org.example.blooddonationapp.infrastructure.entity.MedicalTestEntity;
import org.example.blooddonationapp.infrastructure.repository.DonorProfileRepository;
import org.example.blooddonationapp.infrastructure.repository.MedicalTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalTestService {

    private final MedicalTestRepository medicalTestRepository;
    private final DonorProfileRepository donorProfileRepository;

    @Autowired
    public MedicalTestService(MedicalTestRepository medicalTestRepository,
                              DonorProfileRepository donorProfileRepository) {
        this.medicalTestRepository = medicalTestRepository;
        this.donorProfileRepository = donorProfileRepository;
    }

    public CreateMedicalTestResponseDto createMedicalTest(CreateMedicalTestDto dto) {
        DonorProfileEntity donor = donorProfileRepository.findById(dto.getDonorId())
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        MedicalTestEntity test = new MedicalTestEntity();
        test.setDonor(donor);
        test.setTestType(dto.getTestType());
        test.setResult(dto.getResult());
        test.setTestDate(dto.getTestDate());
        test.setNotes(dto.getNotes());

        MedicalTestEntity saved = medicalTestRepository.save(test);

        return new CreateMedicalTestResponseDto(
                saved.getId(),
                donor.getId(),
                saved.getTestType(),
                saved.getResult(),
                saved.getTestDate(),
                saved.getNotes()
        );
    }

    public List<GetMedicalTestDto> getTestsByDonorId(Long donorId) {
        DonorProfileEntity donor = donorProfileRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        return medicalTestRepository.findByDonor(donor).stream()
                .map(test -> new GetMedicalTestDto(
                        test.getId(),
                        donorId,
                        test.getTestType(),
                        test.getResult(),
                        test.getTestDate(),
                        test.getNotes()
                        )
                )
                .collect(Collectors.toList());
    }

    public GetMedicalTestDto getMedicalTest(Long medicalTestId) {
        MedicalTestEntity test = medicalTestRepository.findById(medicalTestId)
                .orElseThrow(() -> new RuntimeException("Medical test not found"));

        return new GetMedicalTestDto(
                test.getId(),
                test.getDonor().getId(),
                test.getTestType(),
                test.getResult(),
                test.getTestDate(),
                test.getNotes()
        );
    }

    public UpdateMedicalTestDto updateMedicalTest(Long medicalTestId, UpdateMedicalTestDto dto){
        MedicalTestEntity test = medicalTestRepository.findById(medicalTestId)
                .orElseThrow(() -> new IllegalArgumentException("Medical test not found"));

        test.setTestType(dto.getTestType());
        test.setResult(dto.getResult());
        test.setTestDate(dto.getTestDate());
        test.setNotes(dto.getNotes());

        MedicalTestEntity updated = medicalTestRepository.save(test);

        return new UpdateMedicalTestDto(
                updated.getId(),
                updated.getTestType(),
                updated.getResult(),
                updated.getTestDate(),
                updated.getNotes()
        );
    }

    public void deleteTest(Long medicalTestId){
        if (!medicalTestRepository.existsById(medicalTestId)) {
            throw new IllegalArgumentException("Medical test not found");
        }
        medicalTestRepository.deleteById(medicalTestId);
    }
}
