package backend.clinicmanagement.repository;

import backend.clinicmanagement.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndStartAtBetween(Long doctorId,
                                                      LocalDateTime from, LocalDateTime to);

}
