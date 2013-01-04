package org.motechproject.whp.reports.builder;

import org.joda.time.Period;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;
import org.motechproject.whp.reports.contract.FlashingLogRequest;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

import static org.joda.time.PeriodType.seconds;
import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class DomainMapper {

    public PatientAdherenceSubmission mapAdherenceSubmission(AdherenceCaptureRequest adherenceCaptureRequest) {
        PatientAdherenceSubmission submission = new PatientAdherenceSubmission();
        copyProperties(adherenceCaptureRequest, submission);
        return submission;
    }

    public AdherenceCallLog mapAdherenceCallLog(AdherenceCallLogRequest callLogRequest) {
        AdherenceCallLog callLog = new AdherenceCallLog();
        java.util.Date startTime = callLogRequest.getStartTime();
        java.util.Date attemptTime = callLogRequest.getAttemptTime();
        java.util.Date endTime = callLogRequest.getEndTime();

        callLog.setDisconnectionType(callLogRequest.getDisconnectionType());
        callLog.setStartDate(date(startTime));
        callLog.setStartDateTime(timeStamp(startTime));
        callLog.setEndDate(date(endTime));
        callLog.setEndDateTime(timeStamp(endTime));
        callLog.setAttemptTime(timeStamp(attemptTime));
        callLog.setProviderId(callLogRequest.getProviderId());
        callLog.setTotalPatients(callLogRequest.getTotalPatients());
        callLog.setAdherenceCaptured(callLogRequest.getAdherenceCaptured());
        callLog.setCallAnswered(callLogRequest.getCallAnswered());
        callLog.setAdherenceNotCaptured(callLogRequest.getAdherenceNotCaptured());
        callLog.setCallId(callLogRequest.getCallId());
        callLog.setCallStatus(callLogRequest.getCallStatus());
        callLog.setFlashingCallId(callLogRequest.getFlashingCallId());
        callLog.setDuration(getDuration(startTime, endTime));
        return callLog;
    }

    public FlashingLog buildFlashingRequestLog(FlashingLogRequest flashingLogRequest) {
        FlashingLog flashingLog = new FlashingLog();
        flashingLog.setCallTime(timeStamp(flashingLogRequest.getCallTime()));
        flashingLog.setFlashingCallId(flashingLogRequest.getFlashingCallId());
        flashingLog.setMobileNumber(flashingLogRequest.getMobileNumber());
        flashingLog.setProviderId(flashingLogRequest.getProviderId());
        flashingLog.setCreationTime(timeStamp(flashingLogRequest.getCreationTime()));
        return flashingLog;
    }

    private Timestamp timeStamp(java.util.Date time) {
        return (null == time) ? null : new Timestamp(time.getTime());
    }

    private Date date(java.util.Date date) {
        return (null == date) ? null : new Date(date.getTime());
    }

    private int getDuration(java.util.Date startTime, java.util.Date endTime) {
        if (null == startTime || null == endTime) {
            return 0;
        } else {
            return new Period(startTime.getTime(), endTime.getTime(), seconds()).getSeconds();
        }
    }
}
