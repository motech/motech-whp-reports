package org.motechproject.whp.reports.domain.patient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "patient_alert", uniqueConstraints = {@UniqueConstraint(columnNames = {"alert_pk"})})
public class PatientAlerts {
    @Id
    @Column(name = "alert_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "missed_doses")
    private int cumulativeMissedDoses;

    @Column(name = "missed_doses_severity")
    private int cumulativeMissedDosesAlertSeverity;

    @Column(name = "missed_doses_as_of")
    private Date cumulativeMissedDosesAlertDate;

    @Column(name = "adherence_missing")
    private int adherenceMissingWeeks;

    @Column(name = "adherence_missing_severity")
    private int adherenceMissingWeeksAlertSeverity;

    @Column(name = "adherence_missing_as_of")
    private Date adherenceMissingWeeksAlertDate;

    @Column(name = "not_started")
    private int treatmentNotStarted;

    @Column(name = "not_started_severity")
    private int treatmentNotStartedAlertSeverity;

    @Column(name = "not_started_as_of")
    private Date treatmentNotStartedAlertDate;

}
