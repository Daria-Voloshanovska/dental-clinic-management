package backend.clinicmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TreatmentDto {
    public record TreatmentRequest(@NotNull Long patientId, @NotNull Long
    doctorId, @NotBlank String name, String notes, @NotNull LocalDate date,
                                   BigDecimal price) {
    }

    public record TreatmentResponse(Long id, Long patientId, Long doctorId,
                                    String name, String notes, LocalDate date, BigDecimal price) {
    }
}
