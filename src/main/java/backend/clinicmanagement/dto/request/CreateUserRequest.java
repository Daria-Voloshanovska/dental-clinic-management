package backend.clinicmanagement.dto.request;

import backend.clinicmanagement.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @Email @NotBlank String email,
        @NotBlank String password,
        Role role) {}



