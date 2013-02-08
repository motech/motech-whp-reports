package org.motechproject.whp.reports.domain.patient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "patient", uniqueConstraints = {@UniqueConstraint(columnNames = {"patient_pk"})})
public class Patient {

    @Id
    @Column(name = "patient_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_no")
    private String phoneNumber;

    @Column(name = "phi")
    private String phi;

    @Column(name = "status")
    private String patientStatus;

    @Column(name = "is_active")
    private String onActiveTreatment;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_fk")
    private List<Therapy> therapies = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_alert_fk")
    private PatientAlerts patientAlerts = new PatientAlerts();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_address_fk")
    private Address patientAddress = new Address();

    public void clearTherapies() {
        therapies.clear();
    }
}
