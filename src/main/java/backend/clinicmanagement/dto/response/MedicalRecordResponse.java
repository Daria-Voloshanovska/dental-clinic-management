package backend.clinicmanagement.dto.response;

import java.time.LocalDate;

public record MedicalRecordResponse(
        Long id,
        Long patientId,
        Long doctorId,
        LocalDate visitDate,
        String diagnosis,
        String notes) {
}
