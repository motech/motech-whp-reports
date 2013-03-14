package org.motechproject.whp.reports.domain.measure.calllog;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "flashing_log", uniqueConstraints = {@UniqueConstraint(columnNames = {"flashing_log_id"})})
public class FlashingLog implements Serializable {

    @Id
    @Column(name = "flashing_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "call_time")
    private Timestamp callTime;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "flashing_call_id")
    private String flashingCallId;
}
