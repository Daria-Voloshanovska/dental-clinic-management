package backend.clinicmanagement.controller;

import backend.clinicmanagement.dto.request.AppointmentRequest;
import backend.clinicmanagement.dto.response.AppointmentResponse;
import backend.clinicmanagement.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppointmentResponse> all() {
        return service.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public ResponseEntity<AppointmentResponse> create(@Valid @RequestBody AppointmentRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

}
