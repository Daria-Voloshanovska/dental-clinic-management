package backend.clinicmanagement.service;

import backend.clinicmanagement.dto.request.PatientRequest;
import backend.clinicmanagement.dto.response.PatientResponse;
import backend.clinicmanagement.entity.Patient;
import backend.clinicmanagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public PatientResponse create(PatientRequest r) {
        Patient p = repo.save(Patient.builder()
                .firstName(r.firstName())
                .lastName(r.lastName())
                .phone(r.phone())
                .email(r.email())
                .build());
        return new PatientResponse(p.getId(), p.getFirstName(),
                p.getLastName(), p.getPhone(), p.getEmail());
    }

    public List<PatientResponse> findAll() {
        return repo.findAll().stream()
                .map(p -> new PatientResponse(p.getId(), p.getFirstName(),
                        p.getLastName(), p.getPhone(), p.getEmail()))
                .toList();
    }
}
