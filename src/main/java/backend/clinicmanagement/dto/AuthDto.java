package backend.clinicmanagement.dto;

import backend.clinicmanagement.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class AuthDto {
    public record RegisterRequest(
            @Email @NotBlank String email,
            @NotBlank String password,
            Role role, // ADMIN/DOCTOR
            String fullName, // for DOCTOR
            String specialization // for DOCTOR
    ) {
    }

    public record LoginRequest(@Email @NotBlank String email, @NotBlank
    String password) {
    }

    public record AuthResponse(String token) {
    }
}
