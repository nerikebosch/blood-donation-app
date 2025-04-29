package org.example.blooddonationapp.service.donationslot;

import org.example.blooddonationapp.infrastructure.repository.DonationSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.blooddonationapp.infrastructure.entity.DonationSlotEntity;
import org.example.blooddonationapp.controller.donationslot.dto.CreateSlotDto;
import org.example.blooddonationapp.controller.donationslot.dto.CreateSlotResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationSlotService {
    @Autowired
    private DonationSlotRepository slotRepo;

    public CreateSlotResponseDto create(CreateSlotDto dto) {
        var slot = new DonationSlotEntity();
        slot.setDateTime(dto.getDateTime());
        slot.setLocation(dto.getLocation());
        slot.setCapacity(dto.getCapacity());

        var saved = slotRepo.save(slot);
        return new CreateSlotResponseDto(saved.getId(), saved.getLocation(), saved.getDateTime(), saved.getCapacity());
    }

    public List<CreateSlotResponseDto> getAll() {
        return slotRepo.findAll().stream()
                .map(s -> new CreateSlotResponseDto(s.getId(), s.getLocation(), s.getDateTime(), s.getCapacity()))
                .collect(Collectors.toList());
    }
}

