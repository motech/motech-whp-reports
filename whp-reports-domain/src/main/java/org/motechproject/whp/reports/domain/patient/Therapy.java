package org.motechproject.whp.reports.domain.patient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "patient_therapy", uniqueConstraints = {@UniqueConstraint(columnNames = {"therapy_pk"})})
@EqualsAndHashCode(exclude = {"patient"})
@ToString(exclude = {"patient"})
public class Therapy {

    @Id
    @Column(name = "therapy_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="patient_fk", insertable=false, updatable=false)
    private Patient patient;

    @Column(name = "therapy_id")
    private String therapyId;

    @Column(name = "is_current_therapy")
    private String currentTherapy;

    @Column(name = "patient_age")
    private Integer patientAge;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "close_date")
    private Date closeDate;

    @Column(name = "status")
    private String status;

    @Column(name = "treatment_category")
    private String treatmentCategory;

    @Column(name = "disease_class")
    private String diseaseClass;

    @Column(name = "current_phase")
    private String currentPhase;

    @Column(name = "ip_start_date")
    private Date ipStartDate;

    @Column(name = "ip_end_date")
    private Date ipEndDate;

    @Column(name = "ip_pills_taken")
    private Integer ipPillsTaken;

    @Column(name = "ip_pills_remaining")
    private Integer ipPillsRemaining;

    @Column(name = "ip_total_doses")
    private Integer ipTotalDoses;

    @Column(name = "cp_start_date")
    private Date cpStartDate;

    @Column(name = "cp_end_date")
    private Date cpEndDate;

    @Column(name = "cp_pills_taken")
    private Integer cpPillsTaken;

    @Column(name = "cp_pills_remaining")
    private Integer cpPillsRemaining;

    @Column(name = "cp_total_doses")
    private Integer cpTotalDoses;

    @Column(name = "eip_start_date")
    private Date eipStartDate;

    @Column(name = "eip_end_date")
    private Date eipEndDate;

    @Column(name = "eip_pills_taken")
    private Integer eipPillsTaken;

    @Column(name = "eip_pills_remaining")
    private Integer eipPillsRemaining;

    @Column(name = "eip_total_doses")
    private Integer eipTotalDoses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="therapy_fk")
    private List<Treatment> treatments = new ArrayList<>();
}
