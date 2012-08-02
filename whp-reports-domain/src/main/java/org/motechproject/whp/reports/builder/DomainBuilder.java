package org.motechproject.whp.reports.builder;

import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;
import org.motechproject.whp.reports.contract.CallLogRequest;
import org.motechproject.whp.reports.domain.dimension.DateDimension;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;

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
            callLog.setStartTime(new DateDimension(callLogRequest.getStartTime()));
            callLog.setEndTime(new DateDimension(callLogRequest.getEndTime()));
            callLog.setProviderId(callLogRequest.getProviderId());
            return callLog;
    }

}
