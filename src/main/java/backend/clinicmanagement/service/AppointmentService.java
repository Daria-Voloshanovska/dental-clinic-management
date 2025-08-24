package backend.clinicmanagement.service;

import backend.clinicmanagement.dto.request.AppointmentRequest;
import backend.clinicmanagement.dto.response.AppointmentResponse;
import backend.clinicmanagement.entity.Appointment;
import backend.clinicmanagement.entity.Doctor;
import backend.clinicmanagement.entity.Patient;
import backend.clinicmanagement.repository.AppointmentRepository;
import backend.clinicmanagement.repository.DoctorRepository;
import backend.clinicmanagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository repo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;
    public AppointmentService(AppointmentRepository repo, DoctorRepository
            doctorRepo, PatientRepository patientRepo) {
        this.repo = repo; this.doctorRepo = doctorRepo; this.patientRepo =
                patientRepo;
    }
    @Transactional
    public AppointmentResponse create(AppointmentRequest r) {
        Doctor d = doctorRepo.findById(r.doctorId()).orElseThrow();
        Patient p = patientRepo.findById(r.patientId()).orElseThrow();
        if (!r.endAt().isAfter(r.startAt())) throw new
                IllegalArgumentException("endAt must be after startAt");
        Appointment a = repo.save(Appointment.builder()
                .doctor(d)
                .patient(p)
                .startAt(r.startAt())
                .endAt(r.endAt())
                .status("SCHEDULED")
                .build()
        );
        return new AppointmentResponse(a.getId(), d.getId(), p.getId(),
                a.getStartAt(), a.getEndAt(), a.getStatus());
    }
    public List<AppointmentResponse> findAll() {
        return repo.findAll().stream().map(a -> new AppointmentResponse(a.getId(), a.getDoctor().getId(),
                        a.getPatient().getId(), a.getStartAt(), a.getEndAt(), a.getStatus())
        ).toList();
    }
}
