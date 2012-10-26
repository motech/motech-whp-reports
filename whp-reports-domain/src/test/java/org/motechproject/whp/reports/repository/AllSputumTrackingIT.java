package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.SputumTrackingBuilder;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.domain.measure.SputumTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AllSputumTrackingIT extends IntegrationTest {

    @Autowired
    AllSputumTrackings allSputumTrackings;

    @Test
    @Transactional
    public void shouldCreateSputumTrackingRecord() {
        Date submissionDate = new Date();
        SputumTracking sputumTracking = new SputumTrackingBuilder()
                .withContainerId("containerId")
                .issuedOn(submissionDate)
                .assignedToProvider("providerId")
                .submittedBy("CmfAdmin")
                .havingSubmitterId("admin")
                .onLocationId("Patna")
                .havingInstance("Instance")
                .submittedThroughChannel("IVR")
                .forPatientId("patient1")
                .havingStatus("Close")
                .withReasonForClosure("For Fun")
                .withAlternateDiagnosisCode("666")
                .build();

        allSputumTrackings.save(sputumTracking);
        assertNotNull(sputumTracking.getId());

        SputumTracking sputumTrackingFromDB = allSputumTrackings.get(sputumTracking.getId());
        assertThat(sputumTrackingFromDB, is(sputumTracking));

    }

    @Test
    @Transactional
    public void shouldUpdateSputumTrackingRecord() {
        Date submissionDate = new Date();
        SputumTracking sputumTracking = new SputumTrackingBuilder()
                .withContainerId("containerId")
                .issuedOn(submissionDate)
                .assignedToProvider("providerId")
                .submittedBy("CmfAdmin")
                .havingSubmitterId("admin")
                .onLocationId("Patna")
                .havingInstance("Instance")
                .submittedThroughChannel("IVR")
                .forPatientId("patient1")
                .havingStatus("Close")
                .withReasonForClosure("For Fun")
                .withAlternateDiagnosisCode("666")
                .build();

        allSputumTrackings.save(sputumTracking);
        sputumTracking.setContainerStatus("Open");

        allSputumTrackings.save(sputumTracking);

        SputumTracking sputumTrackingFromDB = allSputumTrackings.get(sputumTracking.getId());
        assertThat(sputumTrackingFromDB, is(sputumTracking));
    }
}
