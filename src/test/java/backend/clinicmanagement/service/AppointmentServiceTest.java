package backend.clinicmanagement.service;

import backend.clinicmanagement.dto.request.AppointmentRequest;
import backend.clinicmanagement.dto.response.AppointmentResponse;
import backend.clinicmanagement.entity.Appointment;
import backend.clinicmanagement.entity.Doctor;
import backend.clinicmanagement.entity.Patient;
import backend.clinicmanagement.repository.AppointmentRepository;
import backend.clinicmanagement.repository.DoctorRepository;
import backend.clinicmanagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentServiceTest {
    @Test
    void createsAppointment() {
        AppointmentRepository ar = Mockito.mock(AppointmentRepository.class);
        DoctorRepository dr = Mockito.mock(DoctorRepository.class);
        PatientRepository pr = Mockito.mock(PatientRepository.class);
        Mockito.when(dr.findById(1L)).thenReturn(Optional.of(Doctor.builder().id(1L).build()));
        Mockito.when(pr.findById(2L)).thenReturn(Optional.of(Patient.builder().id(2L).build()));
        Mockito.when(ar.save(Mockito.any())).thenAnswer(inv -> {
            Appointment a = inv.getArgument(0);
            a.setId(5L);
            return a;
        });
        AppointmentService service = new AppointmentService(ar, dr, pr);
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime end = start.plusHours(1);
        AppointmentResponse resp = service.create(new AppointmentRequest(1L,
                2L, start, end));
        assertEquals(5L, resp.id());
        assertEquals(1L, resp.doctorId());
        assertEquals(2L, resp.patientId());
    }

}
