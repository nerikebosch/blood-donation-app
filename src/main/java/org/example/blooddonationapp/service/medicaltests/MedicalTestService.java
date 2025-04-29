package org.example.blooddonationapp.service.medicaltests;


import org.example.blooddonationapp.controller.medicaltests.dto.CreateMedicalTestDto;
import org.example.blooddonationapp.controller.medicaltests.dto.GetMedicalTestDto;
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

    public void create(CreateMedicalTestDto dto) {
        DonorProfileEntity donor = donorProfileRepository.findById(dto.getDonorId())
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        MedicalTestEntity test = new MedicalTestEntity();
        test.setDonor(donor);
        test.setTestType(dto.getTestType());
        test.setResult(dto.getResult());
        test.setTestDate(dto.getTestDate());
        test.setNotes(dto.getNotes());

        medicalTestRepository.save(test);
    }

    public List<GetMedicalTestDto> getByDonor(Long donorId) {
        DonorProfileEntity donor = donorProfileRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        return medicalTestRepository.findByDonor(donor).stream()
                .map(test -> new GetMedicalTestDto(
                        test.getTestType(),
                        test.getResult(),
                        test.getTestDate(),
                        test.getNotes()
                ))
                .collect(Collectors.toList());
    }
}
