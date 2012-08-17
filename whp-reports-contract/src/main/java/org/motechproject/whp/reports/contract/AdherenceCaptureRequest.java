package org.motechproject.whp.reports.contract;


import lombok.Data;

import java.io.Serializable;

@Data
public class AdherenceCaptureRequest implements Serializable {
    private Long timeTaken;
    private String channelId;
    private String status;
    private String patientId;
    private String submittedValue;
    private String providerId;
    private String submittedBy;
    private boolean valid;
    private String callId;

}

