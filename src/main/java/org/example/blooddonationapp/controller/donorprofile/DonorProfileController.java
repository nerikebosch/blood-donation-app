package org.example.blooddonationapp.controller.donorprofile;

import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileDto;
import org.example.blooddonationapp.controller.donorprofile.dto.CreateDonorProfileResponseDto;
import org.example.blooddonationapp.controller.donorprofile.dto.GetDonorProfileDto;
import org.example.blooddonationapp.service.donorprofile.DonorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CreateDonorProfileResponseDto> create(@RequestBody CreateDonorProfileDto dto) {
        var profile = donorProfileService.create(dto);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CreateDonorProfileResponseDto> getByUserId(@PathVariable Long userId) {
        var profile = donorProfileService.getByUserId(userId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<GetDonorProfileDto> getProfile(@PathVariable Long userId) {
        var profile = donorProfileService.getProfileByUserId(userId);
        return ResponseEntity.ok(profile);
    }
}
