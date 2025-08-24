package backend.clinicmanagement.dto.response;

public record PatientResponse(
        Long id,
        String firstName,
        String lastName,
        String phone,
        String email) {
}
