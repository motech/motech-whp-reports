package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContainerVerificationLogRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mobileNumber;
    private String callId;
    private boolean isValidContainer;
}
