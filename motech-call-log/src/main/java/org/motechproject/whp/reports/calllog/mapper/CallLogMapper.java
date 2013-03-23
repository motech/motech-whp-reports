package org.motechproject.whp.reports.calllog.mapper;

import org.motechproject.whp.reports.calllog.domain.CallLog;
import org.motechproject.whp.reports.calllog.request.CallLogRequest;
import org.motechproject.whp.reports.calllog.request.OutboundDetails;
import org.springframework.stereotype.Component;

@Component
public class CallLogMapper {
    public CallLog map(CallLogRequest callLogRequest) {

        CallLog callLog = new CallLog();
        callLog.setCallId(callLogRequest.getCallId());
        callLog.setDisposition(callLogRequest.getDisposition());
        callLog.setEndDateTime(callLogRequest.getEndTime());
        callLog.setStartDateTime(callLogRequest.getStartTime());
        callLog.setPhoneNumber(callLogRequest.getPhoneNumber());
        callLog.setErrorMessage(callLogRequest.getErrorMessage());

        OutboundDetails outboundDetails = callLogRequest.getOutboundDetails();
        if(outboundDetails != null) {
            callLog.setAttemptTime(outboundDetails.getAttemptTime());
            callLog.setAttempt(outboundDetails.getAttempt());
            callLog.setCallType(outboundDetails.getCallType());
            callLog.setRequestId(outboundDetails.getRequestId());
        }
        callLog.setCustomData(callLogRequest.getCustomData());
        callLog.setCallEvents(callLogRequest.getCallEvents());

        return callLog;
    }
}
