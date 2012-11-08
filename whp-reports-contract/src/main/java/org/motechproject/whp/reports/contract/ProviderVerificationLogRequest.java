package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;

@Data
public class ProviderVerificationLogRequest implements Serializable {
    private String mobileNumber;
    private String providerId;
    private String callId;
    private DateTime time;
}
