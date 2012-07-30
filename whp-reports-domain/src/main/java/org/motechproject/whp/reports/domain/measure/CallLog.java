package org.motechproject.whp.reports.domain.measure;

import lombok.Data;
import org.motechproject.whp.reports.domain.dimension.DateTimeDimension;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "start_time")
    private DateTimeDimension startTime;

    @ManyToOne
    @JoinColumn(name = "end_time")
    private DateTimeDimension endTime;

    @Column(name = "called_by")
    private String calledBy;

    @Column(name = "duration")
    private long durationInSeconds;

    @Column(name = "call_status")
    private String callStatus;

    @Column(name = "adherence_status")
    private String adherenceStatus;

    public void setEndTime(DateTimeDimension endTime) {
        if (null != startTime) {
            durationInSeconds = endTime.differenceInSeconds(startTime);
        }
        this.endTime = endTime;
    }
}
