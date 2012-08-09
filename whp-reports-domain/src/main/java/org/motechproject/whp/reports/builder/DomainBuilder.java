package org.motechproject.whp.reports.builder;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;
import org.motechproject.whp.reports.contract.CallLogRequest;
import org.motechproject.whp.reports.domain.dimension.DateDimension;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;

import java.sql.Date;
import java.sql.Timestamp;

import static org.springframework.beans.BeanUtils.copyProperties;

public class DomainBuilder {

    public static PatientAdherenceSubmission buildAdherenceSubmission(AdherenceCaptureRequest adherenceCaptureRequest) {
        PatientAdherenceSubmission submission = new PatientAdherenceSubmission();
        copyProperties(adherenceCaptureRequest, submission);
        return submission;
    }

    public static CallLog buildCallLog(CallLogRequest callLogRequest){
            CallLog callLog = new CallLog();
            callLog.setCalledBy(callLogRequest.getCalledBy());
            callLog.setStartDate(new Date(callLogRequest.getStartTime().getTime()));
            callLog.setStartDateTime(new Timestamp(callLogRequest.getStartTime().getTime()));
            callLog.setEndDate(new Date(callLogRequest.getEndTime().getTime()));
            callLog.setEndDateTime(new Timestamp(callLogRequest.getEndTime().getTime()));
            callLog.setProviderId(callLogRequest.getProviderId());
            callLog.setTotalPatients(callLogRequest.getTotalPatients());
            callLog.setAdherenceCaptured(callLogRequest.getAdherenceCaptured());
            callLog.setAdherenceNotCaptured(callLogRequest.getAdherenceNotCaptured());
            callLog.setCallId(callLogRequest.getCallId());

            Period period = new Period(
                    callLogRequest.getStartTime().getTime(),
                    callLogRequest.getEndTime().getTime(),
                    PeriodType.seconds());

            callLog.setDuration(period.getSeconds());
            return callLog;
    }

}
