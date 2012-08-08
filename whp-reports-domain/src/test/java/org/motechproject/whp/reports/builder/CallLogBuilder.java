package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.measure.CallLog;

import java.sql.Timestamp;
import java.util.Date;

public class CallLogBuilder {

    private String providerId;
    private String calledBy;
    private Date from;
    private int totalPatients;
    private int adherenceCaptured;
    private int adherenceNotCaptured;
    private String callId;

    public static CallLogBuilder newCallLog() {
        return new CallLogBuilder();
    }

    public CallLogBuilder forProvider(String provideId) {
        this.providerId = provideId;
        return this;
    }

    public CallLogBuilder withNumber(String calledBy) {
        this.calledBy = calledBy;
        return this;
    }

    public CallLogBuilder starting(Date from) {
        this.from = from;
        return this;
    }

    public CallLog build() {
        CallLog callLog = new CallLog();
        callLog.setProviderId(providerId);
        callLog.setCalledBy(calledBy);
        if(from != null){
            callLog.setStartDateTime(new Timestamp(from.getTime()));
            callLog.setStartDate(new java.sql.Date(from.getTime()));
        }
        callLog.setCallId(callId);
        callLog.setTotalPatients(totalPatients);
        callLog.setAdherenceCaptured(adherenceCaptured);
        callLog.setAdherenceNotCaptured(adherenceNotCaptured);
        return callLog;
    }

    public CallLogBuilder withTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
        return this;
    }

    public CallLogBuilder withAdherenceCaptured(int adherenceCaptured) {
        this.adherenceCaptured = adherenceCaptured;
        return this;
    }

    public CallLogBuilder withAdherenceNotCaptured(int adherenceNotCaptured) {
        this.adherenceNotCaptured = adherenceNotCaptured;
        return this;
    }

    public CallLogBuilder withCallId(String callId) {
        this.callId = callId;
        return this;
    }
}
