package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.motechproject.validation.constraints.DateTimeFormat;
import org.motechproject.validation.constraints.Enumeration;
import org.motechproject.validation.constraints.NotNullOrEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class ContainerRegistrationCallLogRequest implements Serializable {
    private static final String DATE_TIME_FORMAT = "dd/MM/YYYY HH:mm:ss";

    @NotBlank
    private String callId;
    @NotBlank
    private String providerId;
    @NotNullOrEmpty
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private String startDateTime;
    @NotNullOrEmpty
    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    private String endDateTime;
    @NotNullOrEmpty
    @Enumeration(type = DisconnectionType.class)
    private String disconnectionType;
    @NotBlank
    @Digits(integer = 10, fraction = 0)
    private String mobileNumber;
}
