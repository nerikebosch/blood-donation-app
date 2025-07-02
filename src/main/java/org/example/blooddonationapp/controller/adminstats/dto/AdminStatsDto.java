package org.example.blooddonationapp.controller.adminstats.dto;


import java.util.List;

    public class AdminStatsDto {
        private long totalPatients;
        private long appointmentsToday;
        private long totalAppointments;
        private long completedAppointments;
        private List<BloodDonationStatDto> bloodDonations;

        public AdminStatsDto() {}

        public AdminStatsDto(long totalPatients, long appointmentsToday, long totalAppointments, long completedAppointments, List<BloodDonationStatDto> bloodDonations) {
            this.totalPatients = totalPatients;
            this.appointmentsToday = appointmentsToday;
            this.totalAppointments = totalAppointments;
            this.completedAppointments = completedAppointments;
            this.bloodDonations = bloodDonations;
        }

        public long getTotalPatients() {
            return totalPatients;
        }

        public void setTotalPatients(long totalPatients) {
            this.totalPatients = totalPatients;
        }

        public long getAppointmentsToday() {
            return appointmentsToday;
        }

        public void setAppointmentsToday(long appointmentsToday) {
            this.appointmentsToday = appointmentsToday;
        }

        public long getTotalAppointments() {
            return totalAppointments;
        }

        public void setTotalAppointments(long totalAppointments) {
            this.totalAppointments = totalAppointments;
        }

        public long getCompletedAppointments() {
            return completedAppointments;
        }

        public void setCompletedAppointments(long completedAppointments) {
            this.completedAppointments = completedAppointments;
        }

        public List<BloodDonationStatDto> getBloodDonations() {
            return bloodDonations;
        }

        public void setBloodDonations(List<BloodDonationStatDto> bloodDonations) {
            this.bloodDonations = bloodDonations;
        }
    }
