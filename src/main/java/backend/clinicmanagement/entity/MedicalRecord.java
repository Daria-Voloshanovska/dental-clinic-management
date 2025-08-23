package backend.clinicmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Patient patient;
    @ManyToOne(optional = false)
    private Doctor doctor;
    @Column(nullable = false)
    private LocalDate visitDate;
    private String diagnosis;
    private String notes; // clinical notes
}
