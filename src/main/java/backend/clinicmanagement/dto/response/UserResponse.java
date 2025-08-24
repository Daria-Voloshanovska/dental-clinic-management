package backend.clinicmanagement.dto.response;

import backend.clinicmanagement.entity.Role;

public record UserResponse(
        Long id,
        String email,
        Role role) {
}
