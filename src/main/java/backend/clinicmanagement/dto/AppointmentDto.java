package backend.clinicmanagement.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentDto {
    public record AppointmentRequest(@NotNull Long doctorId, @NotNull Long
    patientId, @NotNull LocalDateTime startAt, @NotNull LocalDateTime endAt) {
    }

    public record AppointmentResponse(Long id, Long doctorId, Long
    patientId, LocalDateTime startAt, LocalDateTime endAt, String status) {
    }
}
