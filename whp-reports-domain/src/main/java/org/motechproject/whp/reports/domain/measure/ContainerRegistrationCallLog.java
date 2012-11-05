package org.motechproject.whp.reports.domain.measure;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "container_call_log", schema = "whp_reports", uniqueConstraints = {@UniqueConstraint(columnNames = {"call_log_id"})})
public class ContainerRegistrationCallLog {

    @Id
    @Column(name = "call_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "call_id")
    private  String callId;


    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "start_date_time")
    private Timestamp startDateTime;

    @Column(name = "end_date_time")
    private Timestamp endDateTime;

    @Column(name = "duration")
    private long duration;

    @Column(name = "disconnection_type")
    private String disconnectionType;

    @Column(name = "mobile_number")
    private String mobileNumber;


}