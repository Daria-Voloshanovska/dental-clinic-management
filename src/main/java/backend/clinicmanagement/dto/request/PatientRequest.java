package backend.clinicmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;

    public record PatientRequest(
            @NotBlank String firstName,
            @NotBlank String lastName,
            String phone,
            String email) {}



