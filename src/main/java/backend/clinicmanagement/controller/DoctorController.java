package backend.clinicmanagement.controller;

import backend.clinicmanagement.dto.request.DoctorRequest;
import backend.clinicmanagement.dto.response.DoctorResponse;
import backend.clinicmanagement.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service =
                service;
    }

    @GetMapping
    public List<DoctorResponse> all() {
        return service.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorResponse> create(@Valid @RequestBody DoctorRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

}
