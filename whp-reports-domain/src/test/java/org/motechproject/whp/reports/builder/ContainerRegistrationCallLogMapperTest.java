package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallDetailsLogRequest;
import org.motechproject.whp.reports.contract.ContainerVerificationLogRequest;
import org.motechproject.whp.reports.contract.ProviderVerificationLogRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.repository.ContainerRegistrationCallLogRepository;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
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
        ContainerRegistrationCallDetailsLogRequest request = new ContainerRegistrationCallDetailsLogRequest();

        request.setCallId("callId");
        request.setDisconnectionType("disconnectionType");
        request.setEndDateTime("10/12/2012 12:33:35");
        request.setStartDateTime("10/12/2012 12:32:35");
        request.setProviderId("providerid");
        request.setMobileNumber("1234567890");


        ContainerRegistrationCallLog callLog = containerRegistrationCallLogMapper.mapFromCallDetails(request);

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
        ContainerRegistrationCallDetailsLogRequest request = new ContainerRegistrationCallDetailsLogRequest();

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

        ContainerRegistrationCallLog actualCallLog = containerRegistrationCallLogMapper.mapFromCallDetails(request);

        assertThat(actualCallLog.getId(), is(1234L));
        assertThat(actualCallLog.getCallId(), is(request.getCallId()));
        assertThat(actualCallLog.getDisconnectionType(), is(request.getDisconnectionType()));
        assertThat(actualCallLog.getStartDateTime(), is(toDate(request.getStartDateTime())));
        assertThat(actualCallLog.getEndDateTime(), is(toDate(request.getEndDateTime())));
        assertThat(actualCallLog.getDuration(), is(60L));
        assertThat(actualCallLog.getProviderId(), is(request.getProviderId()));
        assertThat(actualCallLog.getMobileNumber(), is(request.getMobileNumber()));
    }

    @Test
    public void shouldCreateContainerRegistrationCallLogFromProviderVerificationRequest() {
        ProviderVerificationLogRequest request = new ProviderVerificationLogRequest();

        request.setCallId("callId");
        request.setTime(DateTime.now());
        request.setMobileNumber("1234567890");
        request.setProviderId("raj");

        ContainerRegistrationCallLog actualCallLog = containerRegistrationCallLogMapper.mapFromProviderVerificationDetails(request);

        assertThat(actualCallLog.getCallId(), is(request.getCallId()));
        assertThat(actualCallLog.getProviderVerificationTime(), is(new Timestamp(request.getTime().getMillis())));
        assertThat(actualCallLog.getProviderId(), is(request.getProviderId()));
        assertThat(actualCallLog.getMobileNumber(), is(request.getMobileNumber()));
    }

    @Test
    public void shouldUpdateExistingContainerRegistrationCallLogFromProviderVerificationRequest() {
        ProviderVerificationLogRequest request = new ProviderVerificationLogRequest();

        request.setCallId("callId");
        request.setTime(DateTime.now());
        request.setMobileNumber("1234567890");
        request.setProviderId("raj");

        ContainerRegistrationCallLog expectedLog = new ContainerRegistrationCallLog();
        when(containerRegistrationCallLogRepository.findByCallId("callId")).thenReturn(expectedLog);

        ContainerRegistrationCallLog actualCallLog = containerRegistrationCallLogMapper.mapFromProviderVerificationDetails(request);

        assertThat(expectedLog.getProviderVerificationTime(), is(new Timestamp(request.getTime().getMillis())));
        assertThat(expectedLog.getProviderId(), is(request.getProviderId()));
        assertThat(expectedLog.getMobileNumber(), is(request.getMobileNumber()));
        assertEquals(expectedLog, actualCallLog);
    }

    @Test
    public void shouldCreateContainerRegistrationCallLogFromContainerVerificationRequest() {
        ContainerVerificationLogRequest request = new ContainerVerificationLogRequest();

        request.setCallId("callId");
        request.setMobileNumber("1234567890");
        request.setValidContainer(true);

        ContainerRegistrationCallLog actualCallLog = containerRegistrationCallLogMapper.mapFromContainerVerificationDetails(request);

        assertThat(actualCallLog.getCallId(), is(request.getCallId()));
        assertThat(actualCallLog.getMobileNumber(), is(request.getMobileNumber()));
        assertThat(actualCallLog.getValidContainerVerificationAttempts(), is(1));
        assertThat(actualCallLog.getInValidContainerVerificationAttempts(), is(0));

        request.setValidContainer(false);

        ContainerRegistrationCallLog actualCallLog2 = containerRegistrationCallLogMapper.mapFromContainerVerificationDetails(request);

        assertThat(actualCallLog2.getCallId(), is(request.getCallId()));
        assertThat(actualCallLog2.getMobileNumber(), is(request.getMobileNumber()));
        assertThat(actualCallLog2.getValidContainerVerificationAttempts(), is(0));
        assertThat(actualCallLog2.getInValidContainerVerificationAttempts(), is(1));
    }

    @Test
    public void shouldUpdateExistingContainerRegistrationCallLogFromContainerVerificationRequest() {
        ContainerVerificationLogRequest request = new ContainerVerificationLogRequest();

        request.setCallId("callId");
        request.setMobileNumber("1234567890");
        request.setValidContainer(true);

        ContainerRegistrationCallLog expectedLog = new ContainerRegistrationCallLog();
        expectedLog.setValidContainerVerificationAttempts(1);
        when(containerRegistrationCallLogRepository.findByCallId("callId")).thenReturn(expectedLog);

        ContainerRegistrationCallLog actualCallLog = containerRegistrationCallLogMapper.mapFromContainerVerificationDetails(request);

        assertThat(expectedLog.getMobileNumber(), is(request.getMobileNumber()));
        assertThat(expectedLog.getValidContainerVerificationAttempts(), is(2));
        assertEquals(expectedLog, actualCallLog);
    }

    private java.util.Date toDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/YYYY HH:mm:ss");
        return dateTimeFormatter.parseDateTime(date).toDate();
    }
}
