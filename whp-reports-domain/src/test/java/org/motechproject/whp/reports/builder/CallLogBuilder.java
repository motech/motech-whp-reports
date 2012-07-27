package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.motechproject.whp.reports.domain.measure.CallLog;

public class CallLogBuilder {

    private String provideId;
    private String calledBy;
    private DateTime from;
    private DateTime till;
    private int seconds;

    public static CallLogBuilder newCallLog() {
        return new CallLogBuilder();
    }

    public CallLogBuilder forProvider(String provideId) {
        this.provideId = provideId;
        return this;
    }

    public CallLogBuilder withNumber(String calledBy) {
        this.calledBy = calledBy;
        return this;
    }

    public CallLogBuilder starting(DateTime from) {
        this.from = from;
        return this;
    }

    public CallLogBuilder forSeconds(int seconds) {
        this.seconds = seconds;
        this.till = from.plusSeconds(seconds);
        return this;
    }

    public CallLog build() {
        CallLog callLog = new CallLog();
        callLog.setProviderId(provideId);
        callLog.setCalledBy(calledBy);
        callLog.setStartTime(from.toDate());
        callLog.setEndTime(till.toDate());
        callLog.setDurationInSeconds(seconds);
        return callLog;
    }

}
