package backend.clinicmanagement.service;

import backend.clinicmanagement.config.JwtTokenProvider;
import backend.clinicmanagement.dto.AuthDto;
import backend.clinicmanagement.entity.Doctor;
import backend.clinicmanagement.entity.Role;
import backend.clinicmanagement.entity.User;
import backend.clinicmanagement.repository.DoctorRepository;
import backend.clinicmanagement.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final DoctorRepository doctorRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwt;

    public AuthService(UserRepository userRepo, DoctorRepository doctorRepo, PasswordEncoder encoder, AuthenticationManager authManager, JwtTokenProvider jwt) {
        this.userRepo = userRepo;
        this.doctorRepo = doctorRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwt = jwt;
    }
    public AuthDto.AuthResponse register(AuthDto.RegisterRequest req) {
        if (userRepo.existsByEmail(req.email())) throw new IllegalArgumentException("Email already registered");
        Role role = req.role() == null ? Role.DOCTOR : req.role();
        User user = User.builder()
                .email(req.email())
                .passwordHash(encoder.encode(req.password()))
                .role(role)
                .build();
        userRepo.save(user);
        if (role == Role.DOCTOR) {
            doctorRepo.save(Doctor.builder()
                    .fullName(req.fullName() == null ? req.email() :
                            req.fullName())
                    .specialization(req.specialization() == null ?
                            "General" : req.specialization())
                    .user(user)
                    .build());
        }
        String token = jwt.createToken(user.getEmail(),
                user.getRole().name());
        return new AuthDto.AuthResponse(token);
    }
    public AuthDto.AuthResponse login(AuthDto.LoginRequest req) {
        authManager.authenticate(new
                UsernamePasswordAuthenticationToken(req.email(), req.password()));
        User user = userRepo.findByEmail(req.email()).orElseThrow();
        String token = jwt.createToken(user.getEmail(),
                user.getRole().name());
        return new AuthDto.AuthResponse(token);
    }
}
