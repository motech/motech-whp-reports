package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.query.PatientAdherenceSummary;
import org.motechproject.whp.reports.dao.PatientQueryDAO;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PatientAdherenceDataServiceTest {

    PatientAdherenceDataService patientAdherenceDataService;

    @Mock
    PatientQueryDAO patientQueryDAO;

    @Before
    public void setUp() {
        initMocks(this);
        patientAdherenceDataService = new PatientAdherenceDataService(patientQueryDAO);
    }

    @Test
    public void shouldReturnPatientAdherenceInfo() {
        int limit=1;
        int skip=0;

        List expectedPatientAdherenceList = mock(List.class);

        when(patientQueryDAO.findActivePatientsWithMissingAdherenceAndAMobileNumber(skip, limit)).thenReturn(expectedPatientAdherenceList);

        List<PatientAdherenceSummary> patientAdherenceSummaryList = patientAdherenceDataService.getPatientsWithMissingAdherence(skip, limit);

        assertEquals(expectedPatientAdherenceList, patientAdherenceSummaryList);
        verify(patientQueryDAO).findActivePatientsWithMissingAdherenceAndAMobileNumber(skip, limit);
    }
}
