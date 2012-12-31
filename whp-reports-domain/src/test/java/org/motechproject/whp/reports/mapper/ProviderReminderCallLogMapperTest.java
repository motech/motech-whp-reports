package org.motechproject.whp.reports.mapper;

import org.junit.Test;
import org.motechproject.whp.reports.contract.ProviderReminderCallLogRequest;
import org.motechproject.whp.reports.contract.enums.AnswerStatus;
import org.motechproject.whp.reports.contract.enums.ReminderDisconnectionType;
import org.motechproject.whp.reports.contract.enums.ReminderType;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.motechproject.whp.reports.date.WHPDateTime.datetime;

public class ProviderReminderCallLogMapperTest {

    @Test
    public void shouldMapCallLogRequestToCallLog() {
        ProviderReminderCallLogRequest request = new ProviderReminderCallLogRequest();
        request.setCallId(UUID.randomUUID().toString().substring(0, 32));
        request.setRequestId(UUID.randomUUID().toString().substring(0, 32));
        request.setDisconnectionType("disconnectionType");
        request.setAttempt("2");
        request.setStartTime("10/12/2012 12:32:35");
        request.setEndTime("10/12/2012 12:33:35");
        request.setAttemptTime("10/12/2012 12:34:35");
        request.setMsisdn("+911234567890");
        request.setReminderType(ReminderType.ADHERENCE_NOT_REPORTED.name());
        request.setDisconnectionType(ReminderDisconnectionType.CALL_COMPLETE.name());
        request.setProviderId("providerId");
        request.setCallAnswered(AnswerStatus.YES.name());

        ProviderReminderCallLogMapper mapper = new ProviderReminderCallLogMapper();
        ProviderReminderCallLog callLog = mapper.map(request);


        assertEquals(request.getAttempt(), String.valueOf(callLog.getAttempt()));
        assertEquals(request.getCallId(), callLog.getCallId());
        assertEquals(request.getCallAnswered(), callLog.getCallAnswered());
        assertEquals(request.getDisconnectionType(), callLog.getDisconnectionType());
        assertEquals("1234567890", callLog.getMobileNumber());
        assertEquals(datetime(request.getAttemptTime()).time(), callLog.getAttemptTime());
        assertEquals(datetime(request.getEndTime()).time(), callLog.getEndTime());
        assertEquals(datetime(request.getStartTime()).time(), callLog.getStartTime());
        assertEquals(request.getReminderType(), callLog.getReminderType());
        assertEquals(request.getRequestId(), callLog.getRequestId());
        assertEquals(request.getProviderId(), callLog.getProviderId());

    }
}
