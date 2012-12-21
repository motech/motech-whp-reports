package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdherenceCallLogRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String providerId;
    private String callId;
    private String flashingCallId;
    private String callStatus;
    private Date startTime;
    private Date endTime;
    private String calledBy;
    private Date attemptTime;
    private Integer totalPatients;
    private Integer adherenceCaptured;
    private Integer adherenceNotCaptured;
}
