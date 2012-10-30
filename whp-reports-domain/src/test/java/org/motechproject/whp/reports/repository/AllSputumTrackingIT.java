package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.SputumTrackingBuilder;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
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
        ContainerRecord containerRecord = new SputumTrackingBuilder()
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

        allSputumTrackingRecords.save(containerRecord);
        assertNotNull(containerRecord.getId());

        ContainerRecord containerRecordFromDB = allSputumTrackingRecords.get(containerRecord.getId());
        assertThat(containerRecordFromDB, is(containerRecord));

    }

    @Test
    @Transactional
    public void shouldUpdateSputumTrackingRecord() {
        Date submissionDate = new Date();
        ContainerRecord containerRecord = new SputumTrackingBuilder()
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

        allSputumTrackingRecords.save(containerRecord);
        containerRecord.setContainerStatus("Open");

        allSputumTrackingRecords.save(containerRecord);

        ContainerRecord containerRecordFromDB = allSputumTrackingRecords.get(containerRecord.getId());
        assertThat(containerRecordFromDB, is(containerRecord));
    }
}
