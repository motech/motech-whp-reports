package org.motechproject.whp.reports.calllog.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class CallLogRequest implements Serializable {

    private String callId;

    private String phoneNumber;

    private String startTime;

    private String endTime;

    private String disposition;

    private String errorMessage;

    private OutboundDetails outboundDetails;

    private Map<String, String> callEvents;

    private Map<String, String> customData;
}
