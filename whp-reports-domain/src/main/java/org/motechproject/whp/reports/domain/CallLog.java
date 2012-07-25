package org.motechproject.whp.reports.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//Encapsulates call details
@Data
@Entity
@Table(name = "call_log", uniqueConstraints = {@UniqueConstraint(columnNames = {"call_log_id"})})
public class CallLog {

    @Id
    @Column(name = "call_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "called_by")
    private String calledBy;

    @Column(name = "duration")
    private long durationInSeconds;
}
