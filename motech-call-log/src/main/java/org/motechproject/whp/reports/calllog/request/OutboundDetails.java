package org.motechproject.whp.reports.calllog.request;

import lombok.Data;
import org.motechproject.validation.constraints.DateTimeFormat;

import java.io.Serializable;

@Data
public class OutboundDetails implements Serializable {

    private String callType;

    private String requestId;

    @DateTimeFormat(validateEmptyString = false)
    private String attemptTime;

    private String attempt;
}
