package org.example.blooddonationapp.controller.medicaltests;

import jakarta.validation.Valid;
import org.example.blooddonationapp.controller.medicaltests.dto.CreateMedicalTestDto;
import org.example.blooddonationapp.controller.medicaltests.dto.CreateMedicalTestResponseDto;
import org.example.blooddonationapp.controller.medicaltests.dto.GetMedicalTestDto;
import org.example.blooddonationapp.controller.medicaltests.dto.UpdateMedicalTestDto;
import org.example.blooddonationapp.service.medicaltests.MedicalTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public ResponseEntity<CreateMedicalTestResponseDto> create(@Valid @RequestBody CreateMedicalTestDto dto) {
        CreateMedicalTestResponseDto response = medicalTestService.createMedicalTest(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/donor/{donorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DONOR')")
    public ResponseEntity<List<GetMedicalTestDto>> getByDonor(@PathVariable Long donorId) {
        return ResponseEntity.ok(medicalTestService.getTestsByDonorId(donorId));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GetMedicalTestDto>> getAll() {
        return ResponseEntity.ok(medicalTestService.getAllTests());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DONOR')")
    public ResponseEntity<GetMedicalTestDto> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(medicalTestService.getMedicalTest(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateMedicalTestDto> update(@PathVariable Long id, @RequestBody UpdateMedicalTestDto dto) {
        return ResponseEntity.ok(medicalTestService.updateMedicalTest(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicalTestService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public List<GetMedicalTestDto> getMyMedicalReports(Principal principal) {
        return medicalTestService.getTestsByUsername(principal.getName());
    }

}

