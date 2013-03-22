package org.motechproject.whp.reports.calllog.request;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Data
public class CallLogRequest {

    private String callId;

    private String phoneNumber;

    private Timestamp startDateTime;

    private Timestamp endDateTime;

    private String disposition;

    private String errorMessage;

    private OutboundDetails outboundDetails;

    private Map<String, String> callEvents;

    private Map<String, String> customData;
}
