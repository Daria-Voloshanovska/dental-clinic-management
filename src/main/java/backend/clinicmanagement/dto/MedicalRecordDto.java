package backend.clinicmanagement.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MedicalRecordDto {
    public record MedicalRecordRequest(@NotNull Long patientId, @NotNull
    Long doctorId, @NotNull LocalDate visitDate, String diagnosis, String notes) {
    }

    public record MedicalRecordResponse(Long id, Long patientId, Long
    doctorId, LocalDate visitDate, String diagnosis, String notes) {
    }

}
