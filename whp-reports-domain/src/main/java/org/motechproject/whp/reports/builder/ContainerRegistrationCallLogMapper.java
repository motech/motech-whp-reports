package org.motechproject.whp.reports.builder;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
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

    public ContainerRegistrationCallLog mapContainerRegistrationCallLog(ContainerRegistrationCallLogRequest request) {
        ContainerRegistrationCallLog containerRegistrationCallLog = containerRegistrationCallLogRepository.findByCallId(request.getCallId());
        if(containerRegistrationCallLog == null)
            containerRegistrationCallLog = new ContainerRegistrationCallLog();

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

    private int getDuration(java.util.Date startTime, java.util.Date endTime) {
        return new Period(
                startTime.getTime(),
                endTime.getTime(),
                PeriodType.seconds()).getSeconds();
    }

    private java.util.Date toDate(String date) {
        return dateTimeFormatter.parseDateTime(date).toDate();
    }
}
