package org.motechproject.whp.reports.domain.measure;

import lombok.Data;
import org.motechproject.whp.reports.domain.dimension.DateDimension;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "start_time")
    private DateDimension startTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "end_time")
    private DateDimension endTime;

    @Column(name = "called_by")
    private String calledBy;

    @Column(name = "call_status")
    private String callStatus;

    @Column(name = "adherence_status")
    private String adherenceStatus;

}
