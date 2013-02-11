package org.motechproject.whp.reports.export.query.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.dao.ReportQueryDAO;
import org.motechproject.whp.reports.export.query.model.PatientSummary;

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
        when(reportQueryDAO.getPatientSummaries()).thenReturn(expectedPatientSummaries);

        List<PatientSummary> patientSummaries = reportQueryService.getPatientSummaries();

        assertEquals(expectedPatientSummaries, patientSummaries);
        verify(reportQueryDAO).getPatientSummaries();
    }
}
