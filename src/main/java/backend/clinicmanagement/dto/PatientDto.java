package backend.clinicmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class PatientDto {
    public record PatientRequest(@NotBlank String firstName, @NotBlank
    String lastName, String phone, String email) {
    }

    public record PatientResponse(Long id, String firstName, String
    lastName, String phone, String email) {
    }
}
