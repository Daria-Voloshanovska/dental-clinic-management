package backend.clinicmanagement.dto;

import backend.clinicmanagement.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    public record CreateUserRequest(@Email @NotBlank String email, @NotBlank
    String password, Role role) {
    }

    public record UserResponse(Long id, String email, Role role) {
    }
}
