package org.motechproject.whp.reports.domain.measure;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.Period;

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

    @Column(name = "call_status")
    private String callStatus;

    @Column(name = "adherence_status")
    private String adherenceStatus;

    public void setEndTime(Date endDate) {
        if (null != startTime) {
            Period period = new Period(new DateTime(startTime), new DateTime(endDate));
            durationInSeconds = period.getSeconds();
        }
        this.endTime = endDate;
    }
}
