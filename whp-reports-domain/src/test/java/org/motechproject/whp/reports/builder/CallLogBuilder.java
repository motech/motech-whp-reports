package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.measure.CallLog;

import java.sql.Timestamp;
import java.util.Date;

public class CallLogBuilder {

    private String provideId;
    private String calledBy;
    private Date from;

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

    public CallLogBuilder starting(Date from) {
        this.from = from;
        return this;
    }

    public CallLog build() {
        CallLog callLog = new CallLog();
        callLog.setProviderId(provideId);
        callLog.setCalledBy(calledBy);
        callLog.setStartDateTime(new Timestamp(from.getTime()));
        callLog.setStartDate(new java.sql.Date(from.getTime()));
        return callLog;
    }
}
