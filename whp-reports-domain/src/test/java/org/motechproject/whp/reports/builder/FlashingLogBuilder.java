package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.domain.measure.FlashingLog;

import java.sql.Timestamp;
import java.util.Date;

public class FlashingLogBuilder {

    private String providerId;
    private Date callTime;
    private String mobileNumber;
    private String flashingCallId;

    public static FlashingLogBuilder newFlashingLog() {
        return new FlashingLogBuilder();
    }

    public FlashingLogBuilder forProvider(String provideId) {
        this.providerId = provideId;
        return this;
    }

    public FlashingLogBuilder withMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public FlashingLogBuilder withCallTime(Date callTime) {
        this.callTime = callTime;
        return this;
    }

    public FlashingLog build() {
        FlashingLog flashingLog = new FlashingLog();
        flashingLog.setCallTime(new Timestamp(callTime.getTime()));
        flashingLog.setMobileNumber(this.mobileNumber);
        flashingLog.setProviderId(this.providerId);
        flashingLog.setFlashingCallId(this.flashingCallId);
        return flashingLog;
    }

    public FlashingLogBuilder withFlashingCallId(String flashingCallId) {
        this.flashingCallId = flashingCallId;
        return this;
    }
}
