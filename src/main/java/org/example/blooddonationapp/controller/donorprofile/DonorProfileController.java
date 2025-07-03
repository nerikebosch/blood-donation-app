package org.example.blooddonationapp.controller.donorprofile;

import jakarta.validation.Valid;
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
import java.security.Principal;

import java.util.List;

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
    public ResponseEntity<CreateDonorProfileResponseDto> create(@Valid @RequestBody CreateDonorProfileDto dto) {
        CreateDonorProfileResponseDto profile = donorProfileService.createDonorProfile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profile);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DONOR')")
    public ResponseEntity<GetDonorProfileDto> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(donorProfileService.getDonorById(userId));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<GetDonorProfileDto>> getAllDonors() {
        return ResponseEntity.ok(donorProfileService.getAllDonors());
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DONOR')")
    public ResponseEntity<UpdateDonorProfileDto> update(@PathVariable Long userId, @RequestBody UpdateDonorProfileDto dto) {
        return ResponseEntity.ok(donorProfileService.updateDonorProfile(userId, dto));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DONOR')")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        donorProfileService.deleteDonorProfile(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public ResponseEntity<GetDonorProfileDto> getMyDonorProfile(Principal principal) {
        String username = principal.getName();
        GetDonorProfileDto profile = donorProfileService.getDonorProfileByUsername(username);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

}
