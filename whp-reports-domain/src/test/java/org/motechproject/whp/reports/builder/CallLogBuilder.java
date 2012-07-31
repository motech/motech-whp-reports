package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.dimension.DateDimension;
import org.motechproject.whp.reports.domain.measure.CallLog;

public class CallLogBuilder {

    private String provideId;
    private String calledBy;
    private DateDimension from;

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

    public CallLogBuilder starting(DateDimension from) {
        this.from = from;
        return this;
    }

    public CallLog build() {
        CallLog callLog = new CallLog();
        callLog.setProviderId(provideId);
        callLog.setCalledBy(calledBy);
        callLog.setStartTime(from);
        return callLog;
    }
}
