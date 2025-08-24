package backend.clinicmanagement.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TreatmentResponse(Long id,
                                Long patientId,
                                Long doctorId,
                                String name,
                                String notes,
                                LocalDate date,
                                BigDecimal price) {
}
