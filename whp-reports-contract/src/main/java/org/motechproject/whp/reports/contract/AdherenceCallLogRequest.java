package org.motechproject.whp.reports.contract;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdherenceCallLogRequest implements Serializable {
    private String providerId;
    private String callId;
    private String callStatus;
    private Date startTime;
    private Date endTime;
    private String calledBy;
    private Integer totalPatients;
    private Integer adherenceCaptured;
    private Integer adherenceNotCaptured;
}
