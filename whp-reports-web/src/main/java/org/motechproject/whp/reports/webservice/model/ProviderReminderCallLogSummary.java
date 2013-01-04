package org.motechproject.whp.reports.webservice.model;

import lombok.Setter;
import org.motechproject.export.annotation.ExportValue;
import org.motechproject.whp.reports.date.WHPDateTime;

import java.util.Date;

@Setter
public class ProviderReminderCallLogSummary {

    private String callId;
    private String providerId;
    private String reminderType;
    private String reminderDay;
    private String callAnswered;
    private Date attemptTime;
    private Date startTime;
    private Integer duration;
    private String adherenceReported;
    private String disconnectionType;
    private Integer attempt;

    @ExportValue(index = 1)
    public String getCallId() {
        return callId;
    }

    @ExportValue(index = 2)
    public String getProviderId() {
        return providerId;
    }

    @ExportValue(index = 3)
    public String getReminderType() {
        return reminderType;
    }

    @ExportValue(index = 4)
    public String getReminderDay() {
        return reminderDay;
    }

    @ExportValue(index = 5, format = WHPDateTime.DATE_TIME_FORMAT, column = "Call Attempt Time")
    public Date getAttemptTime() {
        return attemptTime;
    }

    @ExportValue(index = 6, format = WHPDateTime.DATE_TIME_FORMAT, column = "Call Start Time")
    public Date getStartTime() {
        return startTime;
    }

    @ExportValue(index = 7)
    public String getCallAnswered() {
        return callAnswered;
    }

    @ExportValue(index = 8, column = "Duration Of Call")
    public Integer getDuration() {
        return duration;
    }

    @ExportValue(index = 9, column = "Adherence Reported After Call")
    public String getAdherenceReported() {
        return adherenceReported;
    }

    @ExportValue(index = 10)
    public String getDisconnectionType() {
        return disconnectionType;
    }

    @ExportValue(index = 11, column = "Call Attempt Number")
    public Integer getAttempt() {
        return attempt;
    }
}
