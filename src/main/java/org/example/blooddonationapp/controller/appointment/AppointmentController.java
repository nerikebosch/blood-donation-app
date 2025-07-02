package org.example.blooddonationapp.controller.appointment;

import org.example.blooddonationapp.controller.appointment.dto.CreateAppointmentDto;
import org.example.blooddonationapp.controller.appointment.dto.CreateAppointmentResponseDto;
import org.example.blooddonationapp.controller.appointment.dto.GetAppointmentDto;
import org.example.blooddonationapp.controller.appointment.dto.UpdateAppointmentStatusDto;
import org.springframework.http.ResponseEntity;
import org.example.blooddonationapp.service.appointment.AppointmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DONOR')")
    public ResponseEntity<CreateAppointmentResponseDto> createAppointment(@RequestBody CreateAppointmentDto dto) {
        return ResponseEntity.ok(appointmentService.createAppointment(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DONOR')")
    public ResponseEntity<GetAppointmentDto> getAppointment(@PathVariable long id) {
        return ResponseEntity.ok(appointmentService.getAppointment(id));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DONOR')")
    public ResponseEntity<Void> updateAppointmentStatus(@PathVariable long id,
                                                        @RequestBody UpdateAppointmentStatusDto dto) {
        appointmentService.updateAppointmentStatus(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DONOR')")
    public ResponseEntity<List<GetAppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/mine")
    @PreAuthorize("hasRole('ROLE_DONOR')")
    public ResponseEntity<List<GetAppointmentDto>> getMyAppointments(Principal principal) {
        String username = principal.getName();
        System.out.println("Current principal: " + principal);
        System.out.println("Username from principal: " + username);
        List<GetAppointmentDto> appointments = appointmentService.getAppointmentsByUsername(username);
        return ResponseEntity.ok(appointments);
    }

}

