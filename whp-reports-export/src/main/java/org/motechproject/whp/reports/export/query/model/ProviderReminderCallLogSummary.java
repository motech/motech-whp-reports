package org.motechproject.whp.reports.export.query.model;

import lombok.Data;
import org.motechproject.whp.reports.contract.enums.YesNo;
import org.motechproject.whp.reports.date.WHPDateTime;

import java.sql.Timestamp;

@Data
public class ProviderReminderCallLogSummary {
    private String callId;
    private String providerId;
    private String reminderType;
    private String callAnswered;
    private Timestamp attemptDateTime;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private String adherenceReported;
    private String disconnectionType;
    private Integer attempt;
    private String district;

    public String getReminderDay() {
        return WHPDateTime.dayOfWeek(attemptDateTime);
    }

    public Integer getDuration() {
        return WHPDateTime.getDuration(startDateTime, endDateTime);
    }

    public String getAdherenceReportedDisplayText() {
        return YesNo.valueFromCode(adherenceReported).name();
    }
}