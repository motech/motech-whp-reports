package org.motechproject.whp.reports.export.query.model;

import java.sql.Timestamp;

import lombok.Data;

import org.motechproject.whp.reports.date.WHPDateTime;
@Data
public class PatientReminderCallLogSummary {
	private String callId;
    private String patientId;
    private String reminderType;
    private String callAnswered;
    private Timestamp attemptDateTime;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private String disconnectionType;
    private Integer attempt;
    private String district;

    public String getReminderDay() {
        return WHPDateTime.dayOfWeek(attemptDateTime);
    }


}
