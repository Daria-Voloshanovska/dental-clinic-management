package backend.clinicmanagement.controller;

import backend.clinicmanagement.dto.request.PatientRequest;
import backend.clinicmanagement.dto.response.PatientResponse;
import backend.clinicmanagement.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service =
                service;
    }

    @GetMapping
    public List<PatientResponse> all() {
        return service.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody PatientRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

}
