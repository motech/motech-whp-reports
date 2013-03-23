package org.motechproject.whp.reports.calllog.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutboundDetails {

    private String callType;

    private String requestId;

    private Timestamp attemptTime;

    private String attempt;
}
