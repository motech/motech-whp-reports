package org.motechproject.whp.reports.domain.measure;


import lombok.Data;
import org.motechproject.whp.reports.contract.enums.YesNo;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "provider_reminder_call_log", uniqueConstraints = {@UniqueConstraint(columnNames = {"call_log_id"})})
public class ProviderReminderCallLog {

    @Id
    @Column(name = "call_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "call_id")
    private  String callId;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "provider_id")
    private String providerId;

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

    @Column(name = "adherence_reported")
    private String adherenceReported = YesNo.No.code();
}