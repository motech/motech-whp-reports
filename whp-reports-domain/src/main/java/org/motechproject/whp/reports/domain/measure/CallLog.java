package org.motechproject.whp.reports.domain.measure;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "call_log", uniqueConstraints = {@UniqueConstraint(columnNames = {"call_log_id"})})
public class CallLog {

    @Id
    @Column(name = "call_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "call_id")
    private  String callId;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "start_date_time")
    private Timestamp startDateTime;

    @Column(name = "end_date_time")
    private Timestamp endDateTime;

    @Column(name = "duration")
    private long duration;

    @Column(name = "called_by")
    private String calledBy;

    @Column(name = "call_status")
    private String callStatus;

    @Column(name = "patient_count")
    private Integer totalPatients;

    @Column(name = "adherence_captured_count")
    private Integer adherenceCaptured;

    @Column(name = "adherence_not_captured_count")
    private Integer adherenceNotCaptured;

}
