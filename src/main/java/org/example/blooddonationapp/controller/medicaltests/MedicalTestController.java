package org.example.blooddonationapp.controller.medicaltests;

import org.example.blooddonationapp.controller.medicaltests.dto.CreateMedicalTestDto;
import org.example.blooddonationapp.controller.medicaltests.dto.GetMedicalTestDto;
import org.example.blooddonationapp.service.medicaltests.MedicalTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-tests")
public class MedicalTestController {

    private final MedicalTestService medicalTestService ;

    public MedicalTestController(MedicalTestService medicalTestService) {
        this.medicalTestService = medicalTestService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody CreateMedicalTestDto dto) {
        medicalTestService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/donor/{donorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DONOR')")
    public List<GetMedicalTestDto> getByDonor(@PathVariable Long donorId) {
        return medicalTestService.getByDonor(donorId);
    }
}

