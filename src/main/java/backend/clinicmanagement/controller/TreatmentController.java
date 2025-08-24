package backend.clinicmanagement.controller;

import backend.clinicmanagement.dto.request.TreatmentRequest;
import backend.clinicmanagement.dto.response.TreatmentResponse;
import backend.clinicmanagement.service.TreatmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {
    private final TreatmentService service;

    public TreatmentController(TreatmentService service) {
        this.service = service;
    }

    @GetMapping("/patient/{patientId}")
    public List<TreatmentResponse> byPatient(@PathVariable Long patientId) {
        return service.byPatient(patientId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<TreatmentResponse> create(@Valid @RequestBody TreatmentRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

}
