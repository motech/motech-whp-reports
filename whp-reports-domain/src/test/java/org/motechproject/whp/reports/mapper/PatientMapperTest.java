package org.motechproject.whp.reports.mapper;

import org.junit.Test;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.builder.PatientRequestBuilder;
import org.motechproject.whp.reports.contract.patient.PatientDTO;
import org.motechproject.whp.reports.domain.patient.Patient;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PatientMapperTest {

    @Test
    public void shouldMapPatient() {
        PatientDTO patientDTO = new PatientRequestBuilder().withDefaults().build();
        Patient patient = new Patient();

        new PatientMapper().map(patientDTO, patient);

        Patient expectedPatient = new PatientBuilder().withDefaults().build();

        assertThat(patient, is(expectedPatient));
    }

}
