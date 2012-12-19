package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;

@Data
public class ProviderVerificationLogRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mobileNumber;
    private String providerId;
    private String callId;
    private DateTime time;
}
