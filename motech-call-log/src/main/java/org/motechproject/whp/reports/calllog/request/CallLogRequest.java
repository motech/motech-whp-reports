package org.motechproject.whp.reports.calllog.request;

import lombok.Data;
import org.motechproject.validation.constraints.DateTimeFormat;
import org.motechproject.validation.constraints.Enumeration;
import org.motechproject.validation.constraints.NotNullOrEmpty;
import org.motechproject.whp.reports.calllog.domain.DispositionType;

import java.io.Serializable;
import java.util.Map;

@Data
public class CallLogRequest implements Serializable {

    @NotNullOrEmpty
    private String callId;

    @NotNullOrEmpty
    private String phoneNumber;

    @DateTimeFormat(validateEmptyString = false)
    private String startTime;

    @DateTimeFormat(validateEmptyString = false)
    private String endTime;

    @NotNullOrEmpty
    @Enumeration(type = DispositionType.class)
    private String disposition;

    private String errorMessage;

    private OutboundDetails outboundDetails;

    private Map<String, String> callEvents;

    private Map<String, String> customData;
}
