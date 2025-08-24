package backend.clinicmanagement.controller;

import backend.clinicmanagement.dto.request.MedicalRecordRequest;
import backend.clinicmanagement.dto.response.MedicalRecordResponse;
import backend.clinicmanagement.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordController {
    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecordResponse> byPatient(@PathVariable Long patientId) {
        return service.findByPatient(patientId);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<MedicalRecordResponse> create(@Valid @RequestBody MedicalRecordRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

}
