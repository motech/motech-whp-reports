package org.motechproject.whp.reports.builder;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
import org.motechproject.whp.reports.contract.FlashingLogRequest;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

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
        java.util.Date endTime = callLogRequest.getEndTime();

        callLog.setCalledBy(callLogRequest.getCalledBy());
        callLog.setStartDate(new Date(startTime.getTime()));
        callLog.setStartDateTime(new Timestamp(startTime.getTime()));
        callLog.setEndDate(new Date(endTime.getTime()));
        callLog.setEndDateTime(new Timestamp(endTime.getTime()));
        callLog.setProviderId(callLogRequest.getProviderId());
        callLog.setTotalPatients(callLogRequest.getTotalPatients());
        callLog.setAdherenceCaptured(callLogRequest.getAdherenceCaptured());
        callLog.setAdherenceNotCaptured(callLogRequest.getAdherenceNotCaptured());
        callLog.setCallId(callLogRequest.getCallId());
        callLog.setCallStatus(callLogRequest.getCallStatus());

        callLog.setDuration(getDuration(startTime, endTime));
        return callLog;
    }

    public FlashingLog buildFlashingRequestLog(FlashingLogRequest flashingLogRequest) {
        FlashingLog flashingLog = new FlashingLog();
        flashingLog.setCallTime(new Timestamp(flashingLogRequest.getCallTime().getTime()));
        flashingLog.setMobileNumber(flashingLogRequest.getMobileNumber());
        flashingLog.setProviderId(flashingLogRequest.getProviderId());
        flashingLog.setCreationTime(new Timestamp(flashingLogRequest.getCreationTime().getTime()));
        return flashingLog;
    }

    public ContainerRegistrationCallLog mapContainerRegistrationCallLog(ContainerRegistrationCallLogRequest request) {
        ContainerRegistrationCallLog containerRegistrationCallLog = new ContainerRegistrationCallLog();
        java.util.Date startDateTime = request.getStartDateTime();
        java.util.Date endDateTime = request.getEndDateTime();

        containerRegistrationCallLog.setCallId(request.getCallId());
        containerRegistrationCallLog.setDisconnectionType(request.getDisconnectionType());
        containerRegistrationCallLog.setMobileNumber(request.getMobileNumber());
        containerRegistrationCallLog.setProviderId(request.getProviderId());
        containerRegistrationCallLog.setStartDateTime(new Timestamp(startDateTime.getTime()));
        containerRegistrationCallLog.setEndDateTime(new Timestamp(endDateTime.getTime()));
        containerRegistrationCallLog.setDuration(getDuration(startDateTime, endDateTime));
        return containerRegistrationCallLog;
    }

    private int getDuration(java.util.Date startTime, java.util.Date endTime) {
        return new Period(
                startTime.getTime(),
                endTime.getTime(),
                PeriodType.seconds()).getSeconds();
    }
}
