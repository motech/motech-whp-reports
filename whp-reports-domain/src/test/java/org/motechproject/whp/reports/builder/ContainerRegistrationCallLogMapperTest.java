package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.repository.ContainerRegistrationCallLogRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ContainerRegistrationCallLogMapperTest {

    @Mock
    private ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository;
    private ContainerRegistrationCallLogMapper containerRegistrationCallLogMapper ;


    @Before
    public void setUp() {
        initMocks(this);
        containerRegistrationCallLogMapper = new ContainerRegistrationCallLogMapper(containerRegistrationCallLogRepository);
    }

    @Test
    public void shouldCreateContainerRegistrationCallLog() {
        ContainerRegistrationCallLogRequest request = new ContainerRegistrationCallLogRequest();
        DateTime now = new DateTime();
        DateTime startTime = now.minusMinutes(10);
        DateTime endTime = now;

        request.setCallId("callId");
        request.setDisconnectionType("disconnectionType");
        request.setEndDateTime("10/12/2012 12:33:35");
        request.setStartDateTime("10/12/2012 12:32:35");
        request.setProviderId("providerid");
        request.setMobileNumber("1234567890");


        ContainerRegistrationCallLog callLog = containerRegistrationCallLogMapper.mapContainerRegistrationCallLog(request);

        assertThat(callLog.getCallId(), is(request.getCallId()));
        assertThat(callLog.getDisconnectionType(), is(request.getDisconnectionType()));
        assertThat(callLog.getStartDateTime(), is(toDate(request.getStartDateTime())));
        assertThat(callLog.getEndDateTime(), is(toDate(request.getEndDateTime())));
        assertThat(callLog.getDuration(), is(60L));
        assertThat(callLog.getProviderId(), is(request.getProviderId()));
        assertThat(callLog.getMobileNumber(), is(request.getMobileNumber()));
    }

    @Test
    public void shouldUpdateExistingContainerRegistrationCallLog() {
        ContainerRegistrationCallLogRequest request = new ContainerRegistrationCallLogRequest();
        DateTime now = new DateTime();
        DateTime startTime = now.minusMinutes(10);
        DateTime endTime = now;

        request.setCallId("callId");
        request.setDisconnectionType("disconnectionType");
        request.setEndDateTime("10/12/2012 12:33:35");
        request.setStartDateTime("10/12/2012 12:32:35");
        request.setProviderId("providerid");
        request.setMobileNumber("1234567890");

        ContainerRegistrationCallLog expectedCallLog = new ContainerRegistrationCallLog();
        expectedCallLog.setCallId("callId");
        expectedCallLog.setId(1234L);
        when(containerRegistrationCallLogRepository.findByCallId("callId")).thenReturn(expectedCallLog);

        ContainerRegistrationCallLog actualCallLog = containerRegistrationCallLogMapper.mapContainerRegistrationCallLog(request);

        assertThat(actualCallLog.getId(), is(1234L));
        assertThat(actualCallLog.getCallId(), is(request.getCallId()));
        assertThat(actualCallLog.getDisconnectionType(), is(request.getDisconnectionType()));
        assertThat(actualCallLog.getStartDateTime(), is(toDate(request.getStartDateTime())));
        assertThat(actualCallLog.getEndDateTime(), is(toDate(request.getEndDateTime())));
        assertThat(actualCallLog.getDuration(), is(60L));
        assertThat(actualCallLog.getProviderId(), is(request.getProviderId()));
        assertThat(actualCallLog.getMobileNumber(), is(request.getMobileNumber()));
    }

    private java.util.Date toDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/YYYY HH:mm:ss");
        return dateTimeFormatter.parseDateTime(date).toDate();
    }

}
