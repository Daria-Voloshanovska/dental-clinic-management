package backend.clinicmanagement.service;

import backend.clinicmanagement.dto.request.MedicalRecordRequest;
import backend.clinicmanagement.dto.response.MedicalRecordResponse;
import backend.clinicmanagement.entity.Doctor;
import backend.clinicmanagement.entity.MedicalRecord;
import backend.clinicmanagement.entity.Patient;
import backend.clinicmanagement.repository.DoctorRepository;
import backend.clinicmanagement.repository.MedicalRecordRepository;
import backend.clinicmanagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository repo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public MedicalRecordService(MedicalRecordRepository repo,
                                DoctorRepository doctorRepo, PatientRepository patientRepo) {
        this.repo = repo;
        this.doctorRepo = doctorRepo;
        this.patientRepo =
                patientRepo;
    }

    @Transactional
    public MedicalRecordResponse create(MedicalRecordRequest r) {
        Patient p = patientRepo.findById(r.patientId()).orElseThrow();
        Doctor d = doctorRepo.findById(r.doctorId()).orElseThrow();
        MedicalRecord mr = repo.save(MedicalRecord.builder()
                .patient(p)
                .doctor(d)
                .visitDate(r.visitDate())
                .diagnosis(r.diagnosis())
                .notes(r.notes())
                .build());
        return new MedicalRecordResponse(mr.getId(), p.getId(), d.getId(),
                mr.getVisitDate(), mr.getDiagnosis(), mr.getNotes());
    }

    public List<MedicalRecordResponse> findByPatient(Long patientId) {
        return repo.findByPatientId(patientId).stream()
                .map(mr -> new MedicalRecordResponse(mr.getId(),
                        mr.getPatient().getId(), mr.getDoctor().getId(), mr.getVisitDate(),
                        mr.getDiagnosis(), mr.getNotes()))
                .toList();
    }

}
