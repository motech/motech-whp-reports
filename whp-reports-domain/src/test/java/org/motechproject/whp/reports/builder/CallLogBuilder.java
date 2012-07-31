package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.dimension.DateTimeDimension;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;

public class CallLogBuilder {

    private String provideId;
    private String calledBy;
    private DateTimeDimension from;
    private DateTimeDimension till;
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

    public CallLogBuilder starting(DateTimeDimension from) {
        this.from = from;
        return this;
    }

    public CallLogBuilder forSeconds(int seconds) {
        this.seconds = seconds;
        this.till = new DateTimeDimension(from.toDateTime().plusSeconds(seconds).toDate());
        return this;
    }

    public CallLog build() {
        CallLog callLog = new CallLog();
        callLog.setProviderId(provideId);
        callLog.setCalledBy(calledBy);
        callLog.setStartTime(from);
        callLog.setEndTime(till);
        callLog.setDurationInSeconds(seconds);
        return callLog;
    }
}
