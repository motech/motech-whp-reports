package org.motechproject.whp.reports.domain.measure;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "container_call_log", uniqueConstraints = {@UniqueConstraint(columnNames = {"call_log_id"})})
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

    @Column(name = "provider_verification_time")
    private Timestamp providerVerificationTime;

    @Column(name = "valid_container_verification_attempts")
    private int validContainerVerificationAttempts;

    @Column(name = "invalid_container_verification_attempts")
    private int inValidContainerVerificationAttempts;
}