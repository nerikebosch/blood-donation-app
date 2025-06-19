package org.example.blooddonationapp.service.appointment;

import jakarta.transaction.Transactional;
import org.example.blooddonationapp.controller.appointment.dto.CreateAppointmentDto;
import org.example.blooddonationapp.controller.appointment.dto.CreateAppointmentResponseDto;
import org.example.blooddonationapp.controller.appointment.dto.GetAppointmentDto;
import org.example.blooddonationapp.controller.appointment.dto.UpdateAppointmentStatusDto;
import org.example.blooddonationapp.controller.donationslot.dto.CreateSlotDto;
import org.example.blooddonationapp.infrastructure.entity.AppointmentEntity;
import org.example.blooddonationapp.infrastructure.entity.DonationSlotEntity;
import org.example.blooddonationapp.infrastructure.entity.UserEntity;
import org.example.blooddonationapp.infrastructure.repository.AppointmentRepository;
import org.example.blooddonationapp.infrastructure.repository.DonationSlotRepository;
import org.example.blooddonationapp.infrastructure.repository.UserRepository;
import org.example.blooddonationapp.commontypes.AppointmentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DonationSlotRepository slotRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              UserRepository userRepository,
                              DonationSlotRepository slotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.slotRepository = slotRepository;
    }

    @Transactional
    public CreateAppointmentResponseDto createAppointment(CreateAppointmentDto dto) {
        System.out.println("Start creating");
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        DonationSlotEntity slot = slotRepository.findById(dto.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.getCapacity() <= 0) {
            throw new RuntimeException("This slot is fully booked");
        }

        slot.setCapacity(slot.getCapacity() - 1);
        slotRepository.save(slot);

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setUser(user);
        appointment.setSlot(slot);
        appointment.setBookedAt(LocalDateTime.now());
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);
        System.out.println(appointment.getAppointmentStatus());
        System.out.println(appointment);

        AppointmentEntity saved = appointmentRepository.save(appointment);
        System.out.println(saved);

        CreateAppointmentResponseDto response = new CreateAppointmentResponseDto();
        response.setUserId(user.getId());
        response.setSlotId(slot.getId());
        response.setBookedAt(saved.getBookedAt());
        response.setStatus(saved.getAppointmentStatus());

        return response;
    }

    public GetAppointmentDto getAppointment(long id) {
        AppointmentEntity appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        GetAppointmentDto dto = new GetAppointmentDto();
        dto.setId(appointment.getId());
        dto.setUserId(appointment.getUser().getId());
        dto.setBookedAt(appointment.getBookedAt());
        dto.setStatus(appointment.getAppointmentStatus());

        DonationSlotEntity slot = appointment.getSlot();
        CreateSlotDto slotDto = new CreateSlotDto(
                slot.getDateTime(),
                slot.getLocation(),
                slot.getCapacity()
        );

        dto.setSlot(slotDto);

        return dto;
    }

    // Helper method for mapping
    private GetAppointmentDto mapToDto(AppointmentEntity appointment) {
        GetAppointmentDto dto = new GetAppointmentDto();
        dto.setId(appointment.getId());
        dto.setUserId(appointment.getUser().getId());
        dto.setBookedAt(appointment.getBookedAt());
        dto.setStatus(appointment.getAppointmentStatus());

        DonationSlotEntity slot = appointment.getSlot();
        if (slot != null) {
            CreateSlotDto slotDto = new CreateSlotDto(
                    slot.getDateTime(),
                    slot.getLocation(),
                    slot.getCapacity()
            );
            dto.setSlot(slotDto);
        }

        return dto;
    }


    public List<GetAppointmentDto> getAllAppointments() {
        var appointments = appointmentRepository.findAll(); // Adjust as needed
        return appointments.stream()
                .map(this::mapToDto)
                .toList();
    }

    public void updateAppointmentStatus(long id, UpdateAppointmentStatusDto dto) {
        AppointmentEntity appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setAppointmentStatus(dto.getAppointmentStatus());
        appointmentRepository.save(appointment);
    }
}
