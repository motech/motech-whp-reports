package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.motechproject.validation.constraints.DateTimeFormat;
import org.motechproject.validation.constraints.Enumeration;
import org.motechproject.whp.reports.contract.enums.ReminderDisconnectionType;
import org.motechproject.whp.reports.contract.enums.ReminderType;
import org.motechproject.whp.reports.contract.enums.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ProviderReminderCallLogRequest implements Serializable {
    private static final String DATE_TIME_FORMAT = "dd/MM/YYYY HH:mm:ss";

    @NotBlank
    private String requestId;

    @NotBlank
    private String callId;

    @NotEmpty
    @Enumeration(type = ReminderType.class)
    private String reminderType;

    @NotBlank
    private String msisdn;

    @NotBlank
    private String providerId;

    @NotBlank
    @Enumeration(type = Status.class)
    private String callStatus;

    @NotBlank
    @Enumeration(type = ReminderDisconnectionType.class)
    private String disconnectionType;

    @NotBlank
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private String startTime;

    @NotBlank
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private String endTime;

    @NotNull
    private Integer attempt;

    @NotBlank
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private String attemptTime;

}
