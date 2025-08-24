package backend.clinicmanagement.dto.response;


import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime startAt,
        LocalDateTime endAt,
        String status) {
}