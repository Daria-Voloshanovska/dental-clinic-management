package backend.clinicmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class DoctorDto {
    public record DoctorRequest(@NotBlank String fullName, @NotBlank String
    specialization) {
    }

    public record DoctorResponse(Long id, String fullName, String
    specialization, String email) {
    }

}
