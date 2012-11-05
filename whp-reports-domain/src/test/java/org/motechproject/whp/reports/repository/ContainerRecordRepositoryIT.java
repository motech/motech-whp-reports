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

public class ContainerRecordRepositoryIT extends IntegrationTest {

    @Autowired
    ContainerRecordRepository containerRecordRepository;

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

        containerRecordRepository.save(containerRecord);
        assertNotNull(containerRecord.getId());

        ContainerRecord containerRecordFromDB = containerRecordRepository.findOne(containerRecord.getId());
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

        containerRecordRepository.save(containerRecord);
        containerRecord.setStatus("Open");

        containerRecordRepository.save(containerRecord);

        ContainerRecord containerRecordFromDB = containerRecordRepository.findOne(containerRecord.getId());
        assertThat(containerRecordFromDB, is(containerRecord));
    }

    @Test
    @Transactional
    public void shouldGetByContainerId(){
        Date submissionDate = new Date();
        String containerId = "containerId";
        ContainerRecord containerRecord = new SputumTrackingBuilder()
                .withContainerId(containerId)
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

        containerRecordRepository.save(containerRecord);
        containerRecord.setStatus("Open");

        containerRecordRepository.save(containerRecord);

        ContainerRecord containerRecordFromDB = containerRecordRepository.findByContainerId(containerRecord.getContainerId());
        assertThat(containerRecordFromDB,is(containerRecord));
    }
}
