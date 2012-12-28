package org.motechproject.whp.reports.mapper;

import org.apache.commons.lang.StringUtils;
import org.motechproject.whp.reports.contract.ProviderReminderCallLogRequest;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.springframework.stereotype.Component;

import static org.motechproject.whp.reports.date.WHPDateTime.datetime;

@Component
public class ProviderReminderCallLogMapper {

    public ProviderReminderCallLog map(ProviderReminderCallLogRequest request) {
        ProviderReminderCallLog callLog = new ProviderReminderCallLog();
        callLog.setAttempt(Integer.parseInt(request.getAttempt()));
        callLog.setAttemptTime(datetime(request.getAttemptTime()).time());
        callLog.setStartTime(datetime(request.getStartTime()).time());
        callLog.setEndTime(datetime(request.getEndTime()).time());
        callLog.setCallId(request.getCallId());
        callLog.setCallStatus(request.getCallStatus());
        callLog.setDisconnectionType(request.getDisconnectionType());
        callLog.setReminderType(request.getReminderType());
        callLog.setMobileNumber(StringUtils.right(request.getMsisdn(), 10));
        callLog.setRequestId(request.getRequestId());
        callLog.setProviderId(request.getProviderId());
        return callLog;
    }
}
