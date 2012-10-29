package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.SputumTrackingBuilder;
import org.motechproject.whp.reports.domain.measure.SputumTrackingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AllSputumTrackingIT extends IntegrationTest {

    @Autowired
    AllSputumTrackingRecords allSputumTrackingRecords;

    @Test
    @Transactional
    public void shouldCreateSputumTrackingRecord() {
        Date submissionDate = new Date();
        SputumTrackingRecord sputumTrackingRecord = new SputumTrackingBuilder()
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

        allSputumTrackingRecords.save(sputumTrackingRecord);
        assertNotNull(sputumTrackingRecord.getId());

        SputumTrackingRecord sputumTrackingRecordFromDB = allSputumTrackingRecords.get(sputumTrackingRecord.getId());
        assertThat(sputumTrackingRecordFromDB, is(sputumTrackingRecord));

    }

    @Test
    @Transactional
    public void shouldUpdateSputumTrackingRecord() {
        Date submissionDate = new Date();
        SputumTrackingRecord sputumTrackingRecord = new SputumTrackingBuilder()
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

        allSputumTrackingRecords.save(sputumTrackingRecord);
        sputumTrackingRecord.setContainerStatus("Open");

        allSputumTrackingRecords.save(sputumTrackingRecord);

        SputumTrackingRecord sputumTrackingRecordFromDB = allSputumTrackingRecords.get(sputumTrackingRecord.getId());
        assertThat(sputumTrackingRecordFromDB, is(sputumTrackingRecord));
    }
}
