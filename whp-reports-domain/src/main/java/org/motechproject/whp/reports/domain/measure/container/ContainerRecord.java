package org.motechproject.whp.reports.domain.measure.container;

import lombok.Data;
import org.motechproject.whp.reports.domain.dimension.AlternateDiagnosis;
import org.motechproject.whp.reports.domain.dimension.ReasonForClosure;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "sputum_tracking", uniqueConstraints = {@UniqueConstraint(columnNames = {"sputum_tracking_id"})})
public class ContainerRecord {

    @Id
    @Column(name = "sputum_tracking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "date_issued_on")
    private Date issuedOn;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "submitter_role")
    private String submitterRole;

    @Column(name = "submitter_id")
    private String submitterId;

    @Column(name = "provider_district")
    private String providerDistrict;

    @Column(name = "registration_instance")
    private String registrationInstance;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "call_id")
    private String callId;

    @Column(name = "smear_test_date1")
    private Date smearTestDate1;

    @Column(name = "smear_test_result1")
    private String smearTestResult1;

    @Column(name = "smear_test_date2")
    private Date smearTestDate2;

    @Column(name = "smear_test_result2")
    private String smearTestResult2;

    @Column(name = "cumulative_result")
    private String cumulativeResult;

    @Column(name = "lab_name")
    private String labName;

    @Column(name = "lab_number")
    private String labNumber;

    @Column(name = "lab_results_captured_on")
    private Timestamp labResultsCapturedOn;

    @Column(name = "container_status")
    private String status;

    @Column(name = "reason_for_closure")
    private String reasonForClosureCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reason_for_closure",nullable=false, insertable=false, updatable=false)
    private ReasonForClosure reasonForClosure;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "alternate_diagnosis_code")
    private String alternateDiagnosisCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="alternate_diagnosis_code",nullable=false, insertable=false, updatable=false)
    private AlternateDiagnosis alternateDiagnosis;

    @Column(name = "closure_date")
    private Timestamp closureDate;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "tb_id")
    private String tbId;

    @Column(name = "consultation_date")
    private Date consultationDate;

    @Column(name = "mapping_instance")
    private String mappingInstance;

    @Embedded
    private UserGivenPatientDetails userGivenPatientDetails = new UserGivenPatientDetails();
}
