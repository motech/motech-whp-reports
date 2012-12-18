package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.motechproject.validation.constraints.DateTimeFormat;
import org.motechproject.validation.constraints.Enumeration;
import org.motechproject.validation.constraints.NotNullOrEmpty;
import org.motechproject.whp.reports.contract.enums.ReminderDisconnectionType;
import org.motechproject.whp.reports.contract.enums.ReminderType;
import org.motechproject.whp.reports.contract.enums.Status;

import javax.validation.constraints.NotNull;

@Data
public class ProviderReminderCallLogRequest {
    private static final String DATE_TIME_FORMAT = "dd/MM/YYYY HH:mm:ss";

    @NotNullOrEmpty
    private String requestId;

    @NotNullOrEmpty
    private String callId;

    @NotEmpty
    @Enumeration(type = ReminderType.class)
    private String reminderType;

    @NotNullOrEmpty
    private String msisdn;

    @NotNullOrEmpty
    private String providerId;

    @NotNullOrEmpty
    @Enumeration(type = Status.class)
    private String callStatus;

    @NotNullOrEmpty
    @Enumeration(type = ReminderDisconnectionType.class)
    private String disconnectionType;

    @NotNullOrEmpty
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private String startTime;

    @NotNullOrEmpty
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private String endTime;

    @NotNull
    private Integer attempt;

    @NotNullOrEmpty
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private String attemptTime;

}
