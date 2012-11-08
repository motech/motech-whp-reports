package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContainerVerificationLogRequest implements Serializable {
    private String mobileNumber;
    private String callId;
    private boolean isValidContainer;
}
