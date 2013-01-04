package org.motechproject.whp.reports.webservice.mapper;

import org.joda.time.DateTime;
import org.motechproject.whp.reports.contract.enums.YesNo;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.motechproject.whp.reports.webservice.model.ProviderReminderCallLogSummary;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.joda.time.Seconds.secondsBetween;

@Component
public class ProviderReminderCallLogSummaryMapper {

    public List<ProviderReminderCallLogSummary> map(List<ProviderReminderCallLog> callLogs) {
        List<ProviderReminderCallLogSummary> callLogSummaries = new ArrayList<>();
        for(ProviderReminderCallLog callLog : callLogs) {
               callLogSummaries.add(map(callLog));
        }
        return callLogSummaries;
    }

    private ProviderReminderCallLogSummary map(ProviderReminderCallLog callLog) {
        ProviderReminderCallLogSummary summary = new ProviderReminderCallLogSummary();
        summary.setCallId(callLog.getCallId());
        summary.setProviderId(callLog.getProviderId());
        summary.setAttempt(callLog.getAttempt());
        summary.setDisconnectionType(callLog.getDisconnectionType());
        summary.setReminderType(callLog.getReminderType());
        summary.setStartTime(callLog.getStartTime());
        summary.setAttemptTime(callLog.getAttemptTime());
        summary.setDuration(getDuration(callLog.getStartTime(), callLog.getEndTime()));
        YesNo yesNo = YesNo.value(callLog.getAdherenceReported());
        if(yesNo != null)
            summary.setAdherenceReported(yesNo.getText());
        summary.setCallAnswered(callLog.getCallAnswered());
        if(callLog.getAttemptTime() != null)
            summary.setReminderDay(WHPDateTime.dayOfWeek(new DateTime(callLog.getAttemptTime())).name());
        return summary;
    }

    private Integer getDuration(Timestamp startTime, Timestamp endTime) {
        if(startTime == null)
            return null;
        if(endTime == null)
            return null;
        return secondsBetween(new DateTime(startTime), new DateTime(endTime)).getSeconds();
    }
}
