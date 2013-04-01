package org.motechproject.whp.reports.dao;

import org.junit.After;
import org.junit.Test;
import org.motechproject.donotcall.domain.DoNotCallEntry;
import org.motechproject.donotcall.repository.DoNotCallEntryRepository;
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
    DoNotCallEntryRepository doNotCallEntryRepository;
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

        Patient expectedPatient2 = new PatientBuilder()
                .withDefaults()
                .withPatientId("expectedPatient2")
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

        patientRepository.save(asList(expectedPatient, expectedPatient2, patientWithNoMobileNumber, patientWithNoAdherenceMissing, inactivePatient));

        List<PatientAdherenceSummary> patientAdherenceSummaries = patientQueryDAO.findActivePatientsWithMissingAdherenceAndAMobileNumber(0, 5);

        assertThat(patientAdherenceSummaries.size(), is(2));
        assertThat(patientAdherenceSummaries.get(0).getMobileNumber(), is(expectedPatient.getPhoneNumber()));
        assertThat(patientAdherenceSummaries.get(0).getPatientId(), is(expectedPatient.getPatientId()));
        assertThat(patientAdherenceSummaries.get(0).getMissingWeeks(), is(expectedPatient.getPatientAlerts().getAdherenceMissingWeeks()));
        assertThat(patientAdherenceSummaries.get(1).getPatientId(), is(expectedPatient2.getPatientId()));
    }

    @Test
    public void shouldNotFetchPatientsInDoNotCallList() {

        Patient expectedPatient1 = new PatientBuilder()
                .withDefaults()
                .withPatientId("patient1")
                .withAdherenceMissingWeeks(2)
                .withMobileNumber("1234567890")
                .build();
        Patient patientInDoNotCallList = new PatientBuilder()
                .withDefaults()
                .withPatientId("patient2")
                .withAdherenceMissingWeeks(2)
                .withMobileNumber("1234567890")
                .build();
        Patient patientWithDifferentPhoneNumber = new PatientBuilder()
                .withDefaults()
                .withPatientId("patient3")
                .withAdherenceMissingWeeks(2)
                .withMobileNumber("0987654321")
                .build();
        patientRepository.save(asList(expectedPatient1, patientInDoNotCallList, patientWithDifferentPhoneNumber));

        createDoNotCallEntry("Patient", "patient2", "1234567890");
        createDoNotCallEntry("Patient", "patient3", "1234567890");

        List<PatientAdherenceSummary> patientAdherenceSummaries = patientQueryDAO.findActivePatientsWithMissingAdherenceAndAMobileNumber(0, 5);

        assertThat(patientAdherenceSummaries.size(), is(2));
        assertThat(patientAdherenceSummaries.get(0).getMobileNumber(), is(expectedPatient1.getPhoneNumber()));
        assertThat(patientAdherenceSummaries.get(0).getPatientId(), is(expectedPatient1.getPatientId()));assertThat(patientAdherenceSummaries.get(0).getMobileNumber(), is(expectedPatient1.getPhoneNumber()));
        assertThat(patientAdherenceSummaries.get(1).getMobileNumber(), is(patientWithDifferentPhoneNumber.getPhoneNumber()));
        assertThat(patientAdherenceSummaries.get(1).getPatientId(), is(patientWithDifferentPhoneNumber.getPatientId()));

    }

    private void createDoNotCallEntry(String entity, String entityId, String mobileNumber) {
        DoNotCallEntry doNotCallPatient1 = new DoNotCallEntry();
        doNotCallPatient1.setEntity(entity);
        doNotCallPatient1.setEntityId(entityId);
        doNotCallPatient1.setMobileNumber(mobileNumber);
        doNotCallEntryRepository.save(doNotCallPatient1);
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
        doNotCallEntryRepository.deleteAll();
    }
}

