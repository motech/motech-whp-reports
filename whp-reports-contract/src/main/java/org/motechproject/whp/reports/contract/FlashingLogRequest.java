package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FlashingLogRequest implements Serializable {
    private Date creationTime;
    private String mobileNumber;
    private Date callTime;
    private String providerId;
    private String flashingCallId;
}
