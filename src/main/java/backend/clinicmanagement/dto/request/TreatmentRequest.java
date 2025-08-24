package backend.clinicmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

    public record TreatmentRequest(
            @NotNull Long patientId,
            @NotNull Long doctorId,
            @NotBlank String name,
            String notes,
            @NotNull LocalDate date,
            BigDecimal price) {}


