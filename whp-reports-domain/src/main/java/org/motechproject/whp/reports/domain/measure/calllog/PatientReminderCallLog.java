package org.motechproject.whp.reports.domain.measure.calllog;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "patient_reminder_call_log", uniqueConstraints = {@UniqueConstraint(columnNames = {"call_log_id"})})
public class PatientReminderCallLog {

    @Id
    @Column(name = "call_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "call_id")
    private  String callId;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "reminder_type")
    private String reminderType;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "call_answered")
    private String callAnswered;

    @Column(name = "disconnection_type")
    private String disconnectionType;

    @Column(name = "start_date_time")
    private Timestamp startTime;

    @Column(name = "end_date_time")
    private Timestamp endTime;

    @Column(name = "attempt")
    private Integer attempt;

    @Column(name = "attempt_date_time")
    private Timestamp attemptTime;

}
