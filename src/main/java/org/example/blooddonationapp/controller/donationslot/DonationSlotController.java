package org.example.blooddonationapp.controller.donationslot;

import jakarta.validation.Valid;
import org.example.blooddonationapp.controller.donationslot.dto.CreateSlotDto;
import org.example.blooddonationapp.controller.donationslot.dto.CreateSlotResponseDto;
import org.example.blooddonationapp.service.donationslot.DonationSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class DonationSlotController {

    @Autowired
    private DonationSlotService slotService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateSlotResponseDto> create(@RequestBody @Valid CreateSlotDto dto) {
        var response = slotService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('DONOR')")
    public List<CreateSlotResponseDto> getAll() {
        return slotService.getAll();
    }
}

