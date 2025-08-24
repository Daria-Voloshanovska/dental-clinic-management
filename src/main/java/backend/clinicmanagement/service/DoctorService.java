package backend.clinicmanagement.service;

import backend.clinicmanagement.dto.request.DoctorRequest;
import backend.clinicmanagement.dto.response.DoctorResponse;
import backend.clinicmanagement.entity.Doctor;
import backend.clinicmanagement.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public DoctorResponse create(DoctorRequest r) {
        Doctor d =
                repo.save(Doctor.builder().fullName(r.fullName()).specialization(r.specialization()).build());
        return new DoctorResponse(d.getId(), d.getFullName(),
                d.getSpecialization(), d.getUser() != null ? d.getUser().getEmail() : null);
    }

    public List<DoctorResponse> findAll() {
        return repo.findAll().stream()
                .map(d -> new DoctorResponse(d.getId(), d.getFullName(),
                        d.getSpecialization(), d.getUser() != null ? d.getUser().getEmail() : null))
                .toList();
    }
}
