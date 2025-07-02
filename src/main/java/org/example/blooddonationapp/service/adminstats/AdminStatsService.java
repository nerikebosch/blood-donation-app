package org.example.blooddonationapp.service.adminstats;

import org.example.blooddonationapp.commontypes.AppointmentStatus;
import org.example.blooddonationapp.controller.adminstats.dto.AdminStatsDto;
import org.example.blooddonationapp.controller.adminstats.dto.BloodDonationStatDto;
import org.example.blooddonationapp.controller.donorprofile.dto.GetDonorProfileDto;
import org.example.blooddonationapp.infrastructure.entity.AppointmentEntity;
import org.example.blooddonationapp.infrastructure.entity.DonorProfileEntity;
import org.example.blooddonationapp.infrastructure.repository.AppointmentRepository;
import org.example.blooddonationapp.infrastructure.repository.DonorProfileRepository;
import org.example.blooddonationapp.infrastructure.repository.UserRepository;
import org.example.blooddonationapp.service.donorprofile.DonorProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminStatsService {

    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final DonorProfileService donorProfileService;

    public AdminStatsService(UserRepository userRepository,
                             AppointmentRepository appointmentRepository,
                             DonorProfileRepository donorProfileRepository, DonorProfileService donorProfileService) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.donorProfileService = donorProfileService;
    }

    public AdminStatsDto getStats() {
        long totalPatients = donorProfileService.getAllDonors().size();
        long totalAppointments = appointmentRepository.count();
        long completedAppointments = appointmentRepository.countByAppointmentStatus(AppointmentStatus.COMPLETED);

        // Appointments today
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);
        long appointmentsToday = appointmentRepository
                .findByBookedAtBetween(startOfDay, endOfDay)
                .size();

        // Get completed appointments
        List<AppointmentEntity> completed = appointmentRepository.findByAppointmentStatus(AppointmentStatus.COMPLETED);

        // Get all donor profiles
        List<GetDonorProfileDto> donorProfiles = donorProfileService.getAllDonors();

        // Map: userId -> bloodType
        Map<Long, String> userBloodMap = donorProfiles.stream()
                .filter(p -> p.getBloodType() != null)
                .collect(Collectors.toMap(
                        GetDonorProfileDto::getUserId,
                        GetDonorProfileDto::getBloodType
                ));

        List<String> allBloodTypes = List.of("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");

        // Count blood type donations (number of completed appointments per blood type)
        Map<String, Long> bloodCounts = completed.stream()
                .map(a -> userBloodMap.get(a.getUser().getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        bt -> bt,
                        Collectors.counting()
                ));

// Create BloodDonationStatDto list with all blood types, default count 0, multiply count by 500ml
        List<BloodDonationStatDto> bloodStats = allBloodTypes.stream()
                .map(bt -> {
                    long count = bloodCounts.getOrDefault(bt, 0L);
                    long totalVolumeMl = count * 500; // 500ml per appointment
                    return new BloodDonationStatDto(bt, totalVolumeMl);
                })
                .sorted(Comparator.comparing(BloodDonationStatDto::getBloodType))
                .toList();

        return new AdminStatsDto(
                totalPatients,
                appointmentsToday,
                totalAppointments,
                completedAppointments,
                bloodStats
        );
    }
}
