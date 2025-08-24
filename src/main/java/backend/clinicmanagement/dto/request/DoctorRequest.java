package backend.clinicmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DoctorRequest(
        @NotBlank String fullName,
        @NotBlank String specialization) {
}



