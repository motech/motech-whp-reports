package org.motechproject.whp.reports.calllog.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class OutboundDetails implements Serializable {

    private String callType;

    private String requestId;

    private String attemptTime;

    private String attempt;
}
