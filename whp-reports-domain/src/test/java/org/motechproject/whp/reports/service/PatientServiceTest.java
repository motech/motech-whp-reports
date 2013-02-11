package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.patient.PatientDTO;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.mapper.PatientMapper;
import org.motechproject.whp.reports.repository.PatientRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PatientServiceTest {

    PatientService patientService;

    @Mock
    PatientRepository patientRepository;
    @Mock
    PatientMapper patientMapper;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    public void shouldGetPatientByPatientId() {
        String patientId = "patientId";
        Patient expectedPatient = mock(Patient.class);
        when(patientRepository.findByPatientId(patientId)).thenReturn(expectedPatient);

        Patient patient = patientService.getPatient(patientId);

        assertEquals(expectedPatient, patient);
    }

    @Test
    public void shouldUpdateExistingPatient() {
        PatientDTO patientDTO = mock(PatientDTO.class);
        Patient patient = mock(Patient.class);

        when(patientRepository.findByPatientId(patientDTO.getPatientId())).thenReturn(patient);

        patientService.update(patientDTO);

        verify(patientMapper).map(patientDTO, patient);
        verify(patientRepository).save(patient);
    }

    @Test
    public void shouldCreateNewPatient() {
        PatientDTO patientDTO = mock(PatientDTO.class);
        Patient patient = new Patient();

        when(patientRepository.findByPatientId(patientDTO.getPatientId())).thenReturn(null);

        patientService.update(patientDTO);

        verify(patientMapper).map(patientDTO, patient);
        verify(patientRepository).save(patient);
    }

}
