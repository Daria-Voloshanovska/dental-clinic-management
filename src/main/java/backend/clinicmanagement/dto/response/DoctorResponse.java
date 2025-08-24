package backend.clinicmanagement.dto.response;

public record DoctorResponse(
        Long id,
        String fullName,
        String specialization,
        String email) {
}
