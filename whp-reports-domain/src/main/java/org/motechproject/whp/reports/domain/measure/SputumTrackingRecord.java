package org.motechproject.whp.reports.domain.measure;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sputum_tracking", uniqueConstraints = {@UniqueConstraint(columnNames = {"sputum_tracking_id"})})
public class SputumTrackingRecord {

    @Id
    @Column(name = "sputum_tracking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "date_issued_on")
    private Date dateIssuedOn;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "submitted_by")
    private String submittedBy;

    @Column(name = "submitter_id")
    private String submitterId;

    @Column(name = "location_id")
    private String locationId;

    @Column(name = "instance")
    private String instance;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "container_status")
    private String containerStatus;

    @Column(name = "reason_for_closure")
    private String reasonForClosure;

    @Column(name = "alternate_diagnosis_code")
    private String alternateDiagnosisCode;
}
