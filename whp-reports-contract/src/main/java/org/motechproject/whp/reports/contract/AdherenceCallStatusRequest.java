package org.motechproject.whp.reports.contract;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.format.DateTimeFormatter;
import org.motechproject.whp.reports.contract.validation.DateTimeFormat;

import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.util.Date;

import static java.lang.Integer.parseInt;
import static org.joda.time.format.DateTimeFormat.forPattern;

@Data
public class AdherenceCallStatusRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String providerId;
    @NotBlank
    private String callId;
    @NotBlank
    private String flashingCallId;
    @DateTimeFormat(allowBlank = true)
    private String startTime;
    @DateTimeFormat(allowBlank = true)
    private String endTime;
    @DateTimeFormat(allowBlank = true)
    private String attemptTime;
    @NotBlank
    @Digits(message = "should be a number", integer = 10, fraction = 0)
    private String totalPatients;
    @NotBlank
    @Digits(message = "should be a number", integer = 10, fraction = 0)
    private String adherenceCaptured;
    @NotBlank
    @Digits(message = "should be a number", integer = 10, fraction = 0)
    private String adherenceNotCaptured;
    @NotBlank
    private String callAnswered;
    private String callStatus;
    private String disconnectionType;

    public AdherenceCallLogRequest toCallLogRequest() {
        AdherenceCallLogRequest callLogRequest = new AdherenceCallLogRequest();
        setAdherenceDetails(callLogRequest);
        setTimeDetails(callLogRequest);
        setProviderDetails(callLogRequest);
        setCallDetails(callLogRequest);
        return callLogRequest;
    }

    private AdherenceCallLogRequest setAdherenceDetails(AdherenceCallLogRequest callLogRequest) {
        callLogRequest.setTotalPatients(parseInt(this.totalPatients));
        callLogRequest.setAdherenceCaptured(parseInt(this.adherenceCaptured));
        callLogRequest.setAdherenceNotCaptured(parseInt(this.adherenceNotCaptured));
        return callLogRequest;
    }

    private AdherenceCallLogRequest setTimeDetails(AdherenceCallLogRequest callLogRequest) {
        callLogRequest.setStartTime(asDate(this.startTime));
        callLogRequest.setEndTime(asDate(this.endTime));
        callLogRequest.setAttemptTime(asDate(this.attemptTime));
        return callLogRequest;
    }

    private AdherenceCallLogRequest setProviderDetails(AdherenceCallLogRequest callLogRequest) {
        callLogRequest.setProviderId(this.getProviderId());
        return callLogRequest;
    }

    private AdherenceCallLogRequest setCallDetails(AdherenceCallLogRequest callLogRequest) {
        callLogRequest.setDisconnectionType(this.getDisconnectionType());
        callLogRequest.setCallId(this.getCallId());
        callLogRequest.setCallStatus(this.getCallStatus());
        callLogRequest.setCallAnswered(this.getCallAnswered());
        callLogRequest.setFlashingCallId(this.getFlashingCallId());
        return callLogRequest;
    }

    private Date asDate(String startTime) {
        DateTimeFormatter formatter = forPattern("dd/MM/YYYY HH:mm:ss");
        return formatter.parseDateTime(startTime).toDate();
    }
}
