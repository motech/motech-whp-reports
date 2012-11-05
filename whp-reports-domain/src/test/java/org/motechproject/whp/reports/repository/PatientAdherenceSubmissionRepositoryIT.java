package org.motechproject.whp.reports.repository;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;
import static org.motechproject.whp.reports.builder.PatientAdherenceSubmissionBuilder.newSubmission;

public class PatientAdherenceSubmissionRepositoryIT extends IntegrationTest<PatientAdherenceSubmission> {

    @Autowired
    PatientAdherenceSubmissionRepository patientAdherenceSubmissionRepository;

    PatientAdherenceSubmission submission;

    @Before
    public void setup() {
        submission = newSubmission()
                .forPatient("patientId")
                .withDuration(20)
                .through("IVR")
                .as("SKIPPED")
                .by("PROVIDER")
                .withProviderId("providerId")
                .onCall("callId")
                .isValid(true)
                .build();
    }

    @Test
    public void shouldCreateAdherenceSubmission() {
        patientAdherenceSubmissionRepository.save(submission);
        assertNotNull(submission.getId());
    }
}
