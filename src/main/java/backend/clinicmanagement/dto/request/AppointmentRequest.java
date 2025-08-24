package backend.clinicmanagement.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

    public record AppointmentRequest(
            @NotNull Long doctorId,
            @NotNull Long patientId,
            @NotNull LocalDateTime startAt,
            @NotNull LocalDateTime endAt) {}


