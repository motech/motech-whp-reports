package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ContainerRegistrationCallLogRequest implements Serializable {

    private String callId;
    private String providerId;
    private Date startDateTime;
    private Date endDateTime;
    private String disconnectionType;
    private String mobileNumber;

}
