package backend.clinicmanagement.service;

import backend.clinicmanagement.dto.request.PatientRequest;
import backend.clinicmanagement.dto.response.PatientResponse;
import backend.clinicmanagement.entity.Patient;
import backend.clinicmanagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PatientServiceTest {
    @Test
    void createsPatient() {
        PatientRepository repo = Mockito.mock(PatientRepository.class);
        Mockito.when(repo.save(Mockito.any())).thenAnswer(inv -> {
            Patient p = inv.getArgument(0);
            p.setId(1L);
            return p;
        });
        PatientService service = new PatientService(repo);
        PatientResponse resp = service.create(new
                PatientRequest("John", "Doe", "123", "j@d.com"));
        assertNotNull(resp);
        assertEquals(1L, resp.id());
        assertEquals("John", resp.firstName());
        ArgumentCaptor<Patient> captor = ArgumentCaptor.forClass(Patient.class);
        Mockito.verify(repo).save(captor.capture());
        assertEquals("Doe", captor.getValue().getLastName());
    }

}
