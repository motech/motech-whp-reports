package org.motechproject.whp.reports.calllog.mapper;

import org.junit.Test;
import org.motechproject.whp.reports.calllog.builder.CallLogBuilder;
import org.motechproject.whp.reports.calllog.domain.CallLog;
import org.motechproject.whp.reports.calllog.request.CallLogRequest;
import org.motechproject.whp.reports.calllog.request.OutboundDetails;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CallLogMapperTest {
    @Test
    public void shouldMapCallLogRequestToCallLog() {
        Timestamp endDateTime = new Timestamp(new Date().getTime());
        Timestamp startDateTime = new Timestamp(new Date().getTime());
        Timestamp attemptDatetime = new Timestamp(new Date().getTime());

        CallLogRequest callLogRequest = new CallLogRequest();
        callLogRequest.setCallId("callId");
        callLogRequest.setDisposition("disposition");
        callLogRequest.setEndDateTime(endDateTime);
        callLogRequest.setStartDateTime(startDateTime);
        callLogRequest.setPhoneNumber("1234567890");
        callLogRequest.setErrorMessage("errorMessage");

        OutboundDetails outboundDetails = new OutboundDetails();
        outboundDetails.setAttemptDatetime(attemptDatetime);
        outboundDetails.setAttemptNumber("3");
        outboundDetails.setCallType("patientAlerts");
        outboundDetails.setRequestId("requestId");
        callLogRequest.setOutboundDetails(outboundDetails);

        HashMap<String, String> customData = new HashMap<>();
        customData.put("myData1", "myValue1");
        callLogRequest.setCustomData(customData);

        HashMap<String, String> callEvents = new HashMap<>();
        callEvents.put("myEvent1", "myValue1");
        callLogRequest.setCallEvents(callEvents);

        CallLog actualCallLog = new CallLogMapper().map(callLogRequest);

        CallLog expectedCallLog = new CallLogBuilder()
                .withDefaults()
                .withStartDateTime(startDateTime)
                .withEndDateTime(endDateTime)
                .withAttemptDateTime(attemptDatetime).build();

        assertThat(actualCallLog, is(expectedCallLog));
    }

    @Test
    public void shouldNotMapOutboundDetailsIfItIsNotThere() {
        Timestamp endDateTime = new Timestamp(new Date().getTime());
        Timestamp startDateTime = new Timestamp(new Date().getTime());

        CallLogRequest callLogRequest = new CallLogRequest();
        callLogRequest.setCallId("callId");
        callLogRequest.setDisposition("disposition");
        callLogRequest.setEndDateTime(endDateTime);
        callLogRequest.setStartDateTime(startDateTime);
        callLogRequest.setPhoneNumber("1234567890");
        callLogRequest.setErrorMessage("errorMessage");

        HashMap<String, String> customData = new HashMap<>();
        customData.put("myData1", "myValue1");
        callLogRequest.setCustomData(customData);

        HashMap<String, String> callEvents = new HashMap<>();
        callEvents.put("myEvent1", "myValue1");
        callLogRequest.setCallEvents(callEvents);

        CallLog actualCallLog = new CallLogMapper().map(callLogRequest);

        CallLog expectedCallLog = new CallLogBuilder()
                .withDefaults()
                .withNullOutboundDetails()
                .withStartDateTime(startDateTime)
                .withEndDateTime(endDateTime)
                .build();

        assertThat(actualCallLog, is(expectedCallLog));
    }
}
