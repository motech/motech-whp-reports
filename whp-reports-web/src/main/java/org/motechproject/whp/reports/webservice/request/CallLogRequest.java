package org.motechproject.whp.reports.webservice.request;

import lombok.Data;
import org.motechproject.whp.reports.domain.CallLog;

import java.util.Date;

@Data
public class CallLogRequest {
    private String providerId;
    private Date startTime;
    private Date endTime;
    private String calledBy;

    public CallLog createCallLog(){
        CallLog callLog = new CallLog();
        callLog.setCalledBy(calledBy);
        callLog.setStartTime(startTime);
        callLog.setEndTime(endTime);
        callLog.setProviderId(providerId);

        return callLog;
    }
}
