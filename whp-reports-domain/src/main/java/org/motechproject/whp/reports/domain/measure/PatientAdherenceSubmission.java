package org.motechproject.whp.reports.domain.measure;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patient_adherence_submission", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class PatientAdherenceSubmission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_taken")
    private Long timeTaken;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "status")
    private String status;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "submitted_value")
    private String submittedValue;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "submitted_by")
    private String submittedBy;

    @Column(name = "valid")
    private boolean valid;

    @Column(name = "call_id")
    private String callId;

    @Column(name = "ivr_file_length")
    private Long ivrFileLength;

}
