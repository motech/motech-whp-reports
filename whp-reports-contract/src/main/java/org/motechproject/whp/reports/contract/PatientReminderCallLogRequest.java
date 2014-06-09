package org.motechproject.whp.reports.contract;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.motechproject.validation.constraints.Enumeration;
import org.motechproject.whp.reports.contract.enums.ReminderDisconnectionType;
import org.motechproject.whp.reports.contract.enums.ReminderType;
import org.motechproject.whp.reports.contract.enums.YesNo;
@Data
public class PatientReminderCallLogRequest implements Serializable{
	private static final long serialVersionUID = 1L;

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
    private String patientId;

    @NotBlank
    @Enumeration(type = YesNo.class)
    private String callAnswered;

    @NotBlank
    @Enumeration(type = ReminderDisconnectionType.class)
    private String disconnectionType;

    private String startTime;

    private String endTime;

    @NotNull
    @Range(min=1)
    private String attempt;

    @NotBlank
    private String attemptTime;

}
