package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallDetailsLogRequest;
import org.motechproject.whp.reports.contract.ContainerVerificationLogRequest;
import org.motechproject.whp.reports.contract.ProviderVerificationLogRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.repository.ContainerRegistrationCallLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ContainerRegistrationCallLogMapper {
    private final String DATE_TIME_FORMAT = "dd/MM/YYYY HH:mm:ss";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_TIME_FORMAT);
    private ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository;

    @Autowired
    public ContainerRegistrationCallLogMapper(ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository) {
        this.containerRegistrationCallLogRepository = containerRegistrationCallLogRepository;
    }

    public ContainerRegistrationCallLog mapFromCallDetails(ContainerRegistrationCallDetailsLogRequest request) {
        ContainerRegistrationCallLog containerRegistrationCallLog = getCallLogRecord(request.getCallId());

        java.util.Date startDateTime = toDate(request.getStartDateTime());
        java.util.Date endDateTime = toDate(request.getEndDateTime());

        containerRegistrationCallLog.setCallId(request.getCallId());
        containerRegistrationCallLog.setDisconnectionType(request.getDisconnectionType());
        containerRegistrationCallLog.setMobileNumber(request.getMobileNumber());
        containerRegistrationCallLog.setProviderId(request.getProviderId());
        containerRegistrationCallLog.setStartDateTime(new Timestamp(startDateTime.getTime()));
        containerRegistrationCallLog.setEndDateTime(new Timestamp(endDateTime.getTime()));
        containerRegistrationCallLog.setDuration(getDuration(startDateTime, endDateTime));
        return containerRegistrationCallLog;
    }

    public ContainerRegistrationCallLog mapFromProviderVerificationDetails(ProviderVerificationLogRequest request) {
        ContainerRegistrationCallLog containerRegistrationCallLog = getCallLogRecord(request.getCallId());
        containerRegistrationCallLog.setCallId(request.getCallId());
        containerRegistrationCallLog.setMobileNumber(request.getMobileNumber());
        containerRegistrationCallLog.setProviderVerificationTime(getDateTimeIfNotNull(request.getTime()));
        containerRegistrationCallLog.setProviderId(request.getProviderId());

        return containerRegistrationCallLog;
    }

    public ContainerRegistrationCallLog mapFromContainerVerificationDetails(ContainerVerificationLogRequest request) {
        ContainerRegistrationCallLog containerRegistrationCallLog = getCallLogRecord(request.getCallId());
        containerRegistrationCallLog.setCallId(request.getCallId());
        containerRegistrationCallLog.setMobileNumber(request.getMobileNumber());

        if (request.isValidContainer())
            containerRegistrationCallLog.setValidContainerVerificationAttempts(containerRegistrationCallLog.getValidContainerVerificationAttempts() + 1);
        else
            containerRegistrationCallLog.setInValidContainerVerificationAttempts(containerRegistrationCallLog.getInValidContainerVerificationAttempts() + 1);

        return containerRegistrationCallLog;
    }

    private ContainerRegistrationCallLog getCallLogRecord(String callId) {
        ContainerRegistrationCallLog containerRegistrationCallLog = containerRegistrationCallLogRepository.findByCallId(callId);
        if (containerRegistrationCallLog == null)
            containerRegistrationCallLog = new ContainerRegistrationCallLog();
        return containerRegistrationCallLog;
    }

    private int getDuration(java.util.Date startTime, java.util.Date endTime) {
        return new Period(
                startTime.getTime(),
                endTime.getTime(),
                PeriodType.seconds()).getSeconds();
    }

    private java.util.Date toDate(String date) {
        return dateTimeFormatter.parseDateTime(date).toDate();
    }

    private Timestamp getDateTimeIfNotNull(DateTime dateTime) {
        if (dateTime != null)
            return new Timestamp(dateTime.getMillis());
        return null;
    }
}
