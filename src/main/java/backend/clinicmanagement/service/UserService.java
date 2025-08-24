package backend.clinicmanagement.service;

import backend.clinicmanagement.dto.request.CreateUserRequest;
import backend.clinicmanagement.dto.response.UserResponse;
import backend.clinicmanagement.entity.Role;
import backend.clinicmanagement.entity.User;
import backend.clinicmanagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public UserResponse create(CreateUserRequest r) {
        User u = repo.save(User.builder()
                .email(r.email())
                .passwordHash(encoder.encode(r.password()))
                .role(r.role() == null ? Role.DOCTOR : r.role())
                .build());
        return new UserResponse(u.getId(), u.getEmail(), u.getRole());
    }

    public List<UserResponse> all() {
        return repo.findAll().stream().map(u -> new UserResponse(u.getId(),
                u.getEmail(), u.getRole())).toList();
    }

}
