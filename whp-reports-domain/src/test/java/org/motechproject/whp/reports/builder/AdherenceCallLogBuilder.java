package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.measure.calllog.AdherenceCallLog;

import java.sql.Timestamp;
import java.util.Date;

public class AdherenceCallLogBuilder {

    private String providerId;
    private Date from;
    private int totalPatients;
    private int adherenceCaptured;
    private int adherenceNotCaptured;
    private String callId;
    private int duration;
    private String flashingCallId;

    public static AdherenceCallLogBuilder newCallLog() {
        return new AdherenceCallLogBuilder();
    }

    public AdherenceCallLogBuilder forProvider(String provideId) {
        this.providerId = provideId;
        return this;
    }

    public AdherenceCallLogBuilder withNumber() {
        return this;
    }

    public AdherenceCallLogBuilder starting(Date from) {
        this.from = from;
        return this;
    }

    public AdherenceCallLog build() {
        AdherenceCallLog callLog = new AdherenceCallLog();
        callLog.setProviderId(providerId);
        if(from != null){
            callLog.setStartDateTime(new Timestamp(from.getTime()));
            callLog.setStartDate(new java.sql.Date(from.getTime()));
        }
        callLog.setCallId(callId);
        callLog.setTotalPatients(totalPatients);
        callLog.setAdherenceCaptured(adherenceCaptured);
        callLog.setAdherenceNotCaptured(adherenceNotCaptured);
        callLog.setDuration(duration);
        callLog.setFlashingCallId(flashingCallId);
        return callLog;
    }

    public AdherenceCallLogBuilder withTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
        return this;
    }

    public AdherenceCallLogBuilder withAdherenceCaptured(int adherenceCaptured) {
        this.adherenceCaptured = adherenceCaptured;
        return this;
    }

    public AdherenceCallLogBuilder withAdherenceNotCaptured(int adherenceNotCaptured) {
        this.adherenceNotCaptured = adherenceNotCaptured;
        return this;
    }

    public AdherenceCallLogBuilder withCallId(String callId) {
        this.callId = callId;
        return this;
    }

    public AdherenceCallLogBuilder withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public AdherenceCallLogBuilder withFlashingCallId(String flashingCallId) {
        this.flashingCallId = flashingCallId;
        return this;
    }
}
