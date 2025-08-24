package backend.clinicmanagement.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MedicalRecordRequest(
        @NotNull Long patientId,
        @NotNull Long doctorId,
        @NotNull LocalDate visitDate,
        String diagnosis,
        String notes) {}


