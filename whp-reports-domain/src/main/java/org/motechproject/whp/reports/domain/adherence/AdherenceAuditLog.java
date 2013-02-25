package org.motechproject.whp.reports.domain.adherence;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "adherence_audit_log", uniqueConstraints = {@UniqueConstraint(columnNames = {"audit_log_id"})})
public class AdherenceAuditLog {

    @Id
    @Column(name = "audit_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "tb_id")
    private String tbId;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "dose_date")
    private Date doseDate;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "doses_taken")
    private Integer numberOfDosesTaken;

    @Column(name = "pill_status")
    private String pillStatus;

    @Column(name = "channel")
    private String channel;

    @Column(name="is_given_by_provider")
    private String isGivenByProvider;

}
