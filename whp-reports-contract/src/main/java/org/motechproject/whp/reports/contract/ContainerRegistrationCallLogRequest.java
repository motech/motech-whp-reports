package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.util.Date;

@Data
public class ContainerRegistrationCallLogRequest {

    private String callId;
    private String providerId;
    private Date startDateTime;
    private Date endDateTime;
    private String disconnectionType;
    private String mobileNumber;

}
