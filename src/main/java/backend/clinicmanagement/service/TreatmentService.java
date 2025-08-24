package backend.clinicmanagement.service;

import backend.clinicmanagement.dto.request.TreatmentRequest;
import backend.clinicmanagement.dto.response.TreatmentResponse;
import backend.clinicmanagement.entity.Doctor;
import backend.clinicmanagement.entity.Patient;
import backend.clinicmanagement.entity.Treatment;
import backend.clinicmanagement.repository.DoctorRepository;
import backend.clinicmanagement.repository.PatientRepository;
import backend.clinicmanagement.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {
    private final TreatmentRepository repo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;

    public TreatmentService(TreatmentRepository repo, PatientRepository
            patientRepo, DoctorRepository doctorRepo) {
        this.repo = repo;
        this.patientRepo = patientRepo;
        this.doctorRepo =
                doctorRepo;
    }

    public TreatmentResponse create(TreatmentRequest r) {
        Patient p = patientRepo.findById(r.patientId()).orElseThrow();
        Doctor d = doctorRepo.findById(r.doctorId()).orElseThrow();
        Treatment t = repo.save(Treatment.builder()
                .patient(p)
                .doctor(d)
                .name(r.name())
                .notes(r.notes())
                .date(r.date())
                .price(r.price())
                .build()
        );

        return new TreatmentResponse(
                t.getId(),
                p.getId(),
                d.getId(),
                t.getName(),
                t.getNotes(),
                t.getDate(),
                t.getPrice()
        );
    }

    public List<TreatmentResponse> byPatient(Long patientId) {
        return repo.findByPatientId(patientId).stream()
                .map(t -> new TreatmentResponse(t.getId(),
                        t.getPatient().getId(), t.getDoctor().getId(), t.getName(), t.getNotes(),
                        t.getDate(), t.getPrice()))
                .toList();
    }

}
