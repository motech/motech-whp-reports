package org.motechproject.whp.reports.calllog.handler;

import org.motechproject.event.MotechEvent;
import org.motechproject.event.annotations.MotechListener;
import org.motechproject.whp.reports.calllog.request.CallLogRequest;
import org.motechproject.whp.reports.calllog.service.CallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallLogHandler {

    private CallLogService callLogService;

    @Autowired
    public CallLogHandler(CallLogService callLogService) {
        this.callLogService = callLogService;
    }

    @MotechListener(subjects = EventKeys.CALL_LOG_RECEIVED)
    public void handleCallLogReceived(MotechEvent motechEvent) {
        CallLogRequest callLogRequest = (CallLogRequest) motechEvent.getParameters().get("0");

        callLogService.add(callLogRequest);
    }
}
