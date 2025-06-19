package org.example.blooddonationapp.controller.donorprofile;

import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileDto;
import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileResponseDto;
import org.example.blooddonationapp.controller.donorprofile.dto.GetDonorProfileDto;
import org.example.blooddonationapp.controller.donorprofile.dto.UpdateDonorProfileDto;
import org.example.blooddonationapp.service.donorprofile.DonorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.init.RepositoriesPopulatedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donor-profiles")
public class DonorProfileController {

    private final DonorProfileService donorProfileService;

    @Autowired
    public DonorProfileController(DonorProfileService donorProfileService) {
        this.donorProfileService = donorProfileService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateDonorProfileResponseDto> create(@RequestBody CreateDonorProfileDto dto) {
        CreateDonorProfileResponseDto profile = donorProfileService.createDonorProfile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DONOR')")
    public ResponseEntity<GetDonorProfileDto> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(donorProfileService.getDonorById(userId));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DONOR')")
    public ResponseEntity<UpdateDonorProfileDto> update(@PathVariable Long userId, @RequestBody UpdateDonorProfileDto dto) {
        return ResponseEntity.ok(donorProfileService.updateDonorProfile(userId, dto));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DONOR')")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        donorProfileService.deleteDonorProfile(userId);
        return ResponseEntity.noContent().build();
    }
}
