package org.motechproject.whp.reports.export.query.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.dao.ReportQueryDAO;
import org.motechproject.whp.reports.export.query.model.*;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReportQueryServiceTest {

    ReportQueryService reportQueryService;

    @Mock
    ReportQueryDAO reportQueryDAO;

    @Before
    public void setUp() {
        initMocks(this);
        reportQueryService = new ReportQueryService(reportQueryDAO);
    }

    @Test
    public void shouldReturnListOfPatientSummaries() {
        List expectedPatientSummaries = mock(List.class);
        PatientReportRequest patientReportRequest = mock(PatientReportRequest.class);
        when(reportQueryDAO.getPatientSummaries(patientReportRequest)).thenReturn(expectedPatientSummaries);

        List<PatientSummary> patientSummaries = reportQueryService.getPatientSummaries(patientReportRequest);

        assertEquals(expectedPatientSummaries, patientSummaries);
        verify(reportQueryDAO).getPatientSummaries(patientReportRequest);
    }

    @Test
    public void shouldReturnListOfAdherenceAuditLogSummaries(){
        List expectedAdherenceAuditLogSummaries = mock(List.class);
        when(reportQueryDAO.getAdherenceAuditLogSummaries()).thenReturn(expectedAdherenceAuditLogSummaries);

        List<AdherenceAuditLogSummary> auditLogSummaries = reportQueryService.getAdherenceAuditLogSummaries();

        assertEquals(expectedAdherenceAuditLogSummaries, auditLogSummaries);
        verify(reportQueryDAO).getAdherenceAuditLogSummaries();
    }

    @Test
    public void shouldReturnListOfAdherenceRecordSummaries() {
        List expectedAdherenceRecordSummaries = mock(List.class);
        when(reportQueryDAO.getAdherenceRecordSummaries()).thenReturn(expectedAdherenceRecordSummaries);

        List<AdherenceRecordSummary> adherenceRecordSummaries = reportQueryService.getAdherenceRecordSummaries();

        assertEquals(expectedAdherenceRecordSummaries, adherenceRecordSummaries);
        verify(reportQueryDAO).getAdherenceRecordSummaries();
    }

    @Test
    public void shouldProviderReminderCallLogSummaries() {
        List expectedProviderReminderCallLogSummaries = mock(List.class);
        when(reportQueryDAO.getProviderReminderCallLogSummaries()).thenReturn(expectedProviderReminderCallLogSummaries);

        List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries = reportQueryService.getProviderReminderCallLogSummaries();

        assertEquals(expectedProviderReminderCallLogSummaries, providerReminderCallLogSummaries);
        verify(reportQueryDAO).getProviderReminderCallLogSummaries();
    }
}
