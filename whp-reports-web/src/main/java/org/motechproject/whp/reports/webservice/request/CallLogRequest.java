package org.motechproject.whp.reports.webservice.request;

import lombok.Data;
import org.motechproject.whp.reports.domain.dimension.DateDimension;
import org.motechproject.whp.reports.domain.measure.CallLog;

import java.util.Date;

@Data
public class CallLogRequest {
    private String providerId;
    private Date startTime;
    private Date endTime;
    private String calledBy;

    public CallLog createCallLog() {
        CallLog callLog = new CallLog();
        callLog.setCalledBy(calledBy);
        callLog.setStartTime(new DateDimension(startTime));
        callLog.setEndTime(new DateDimension(endTime));
        callLog.setProviderId(providerId);
        return callLog;
    }
}
