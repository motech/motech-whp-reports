package org.motechproject.whp.reports.dao;

import org.junit.After;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.contract.query.PatientAdherenceSummary;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PatientQueryDAOIT extends IntegrationTest {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientQueryDAO patientQueryDAO;

    @Test
    public void shouldFindActivePatientsWithMissingAdherenceAndAMobileNumber() {
        Patient expectedPatient = new PatientBuilder()
                .withDefaults()
                .withPatientId("expectedPatient")
                .withAdherenceMissingWeeks(2)
                .withMobileNumber("1234567890")
                .build();

        Patient patientWithNoMobileNumber = new PatientBuilder()
                .withDefaults()
                .withPatientId("patientWithNoMobileNumber")
                .withAdherenceMissingWeeks(2)
                .withMobileNumber(null)
                .build();

        Patient patientWithNoAdherenceMissing = new PatientBuilder()
                .withDefaults()
                .withPatientId("patientWithNoAdherenceMissing")
                .withAdherenceMissingWeeks(0)
                .withMobileNumber(null)
                .build();

        Patient inactivePatient = new PatientBuilder()
                .withDefaults()
                .withPatientId("inactive")
                .withAdherenceMissingWeeks(2)
                .withMobileNumber(null)
                .withInactiveStatus()
                .build();

        patientRepository.save(asList(expectedPatient, patientWithNoMobileNumber, patientWithNoAdherenceMissing, inactivePatient));

        List<PatientAdherenceSummary> patientAdherenceSummaries = patientQueryDAO.findActivePatientsWithMissingAdherenceAndAMobileNumber(0, 1);

        assertThat(patientAdherenceSummaries.size(), is(1));
        assertThat(patientAdherenceSummaries.get(0).getMobileNumber(), is(expectedPatient.getPhoneNumber()));
        assertThat(patientAdherenceSummaries.get(0).getPatientId(), is(expectedPatient.getPatientId()));
        assertThat(patientAdherenceSummaries.get(0).getMissingWeeks(), is(expectedPatient.getPatientAlerts().getAdherenceMissingWeeks()));

        assertThat(patientQueryDAO.findActivePatientsWithMissingAdherenceAndAMobileNumber(1, 1).size(), is(0));
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }
}

