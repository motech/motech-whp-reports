package org.motechproject.whp.reports.domain.patient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "patient_treatment", uniqueConstraints = {@UniqueConstraint(columnNames = {"treatment_pk"})})
@EqualsAndHashCode(exclude = {"therapy"})
@ToString(exclude = {"therapy"})
public class Treatment {
    @Id
    @Column(name = "treatment_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="therapy_fk", insertable=false, updatable=false)
    private Therapy therapy;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "provider_district")
    private String providerDistrict;

    @Column(name = "tb_id")
    private String tbId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "treatment_outcome")
    private String treatmentOutcome;

    @Column(name = "patient_type")
    private String patientType;

    @Column(name = "tb_registration_no")
    private String tbRegistrationNumber;

    @Column(name = "pretreatment_result")
    private String preTreatmentSmearTestResult;

    @Column(name = "pretreatment_weight")
    private Double preTreatmentWeight;

    @Column(name = "is_current_treatment")
    private String currentTreatment;

    @Column(name = "is_paused")
    private String isPaused;

    @Column(name = "paused_date")
    private Date pausedDate;

    @Column(name = "reasons_for_pause")
    private String reasonsForPause;

    @Column(name = "paused_duration")
    private int totalPausedDuration;//days

}
