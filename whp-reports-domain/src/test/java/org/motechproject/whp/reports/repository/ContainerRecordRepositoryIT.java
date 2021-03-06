package org.motechproject.whp.reports.repository;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.ContainerRecordBuilder;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.domain.measure.container.UserGivenPatientDetails;
import org.motechproject.whp.reports.domain.paging.ContainerRecordPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ContainerRecordRepositoryIT extends IntegrationTest {

    @Autowired
    ContainerRecordRepository containerRecordRepository;

    @Test
    public void shouldCreateSputumTrackingRecord() {
        Date submissionDate = new Date();
        ContainerRecord containerRecord = createContainerRecord(submissionDate, "containerId");

        containerRecordRepository.save(containerRecord);
        assertNotNull(containerRecord.getId());

        ContainerRecord containerRecordFromDB = containerRecordRepository.findOne(containerRecord.getId());
        assertThat(containerRecordFromDB.getContainerId(), is(containerRecord.getContainerId()));
        assertUserGivenPatientDetails(containerRecord, containerRecordFromDB);
    }

    @Test
    public void shouldFetchReasonForClosureInsideSputumTrackingRecord() {
        Date submissionDate = new Date();
        ContainerRecord containerRecord = createContainerRecord(submissionDate, "containerId");
        containerRecord.setReasonForClosureCode("1");

        containerRecordRepository.save(containerRecord);

        ContainerRecord containerRecordFromDB = containerRecordRepository.findOne(containerRecord.getId());
        assertNotNull(containerRecordFromDB.getReasonForClosure());
    }

    @Test
    public void shouldFetchAlternateDiagnosisInsideSputumTrackingRecord() {
        Date submissionDate = new Date();
        ContainerRecord containerRecord = createContainerRecord(submissionDate, "containerId");
        containerRecord.setAlternateDiagnosisCode("1027");

        containerRecordRepository.save(containerRecord);

        ContainerRecord containerRecordFromDB = containerRecordRepository.findOne(containerRecord.getId());
        assertNotNull(containerRecordFromDB.getAlternateDiagnosis());
    }

    @Test
    public void shouldUpdateSputumTrackingRecord() {
        Date submissionDate = new Date();
        ContainerRecord containerRecord = createContainerRecord(submissionDate, "containerId");

        containerRecordRepository.save(containerRecord);
        containerRecord.setStatus("Open");

        containerRecordRepository.save(containerRecord);

        ContainerRecord containerRecordFromDB = containerRecordRepository.findOne(containerRecord.getId());
        assertThat(containerRecordFromDB.getStatus(), is("Open"));
    }

    @Test
    public void shouldGetByContainerId(){
        Date submissionDate = new Date();
        String containerId = "containerId";
        ContainerRecord containerRecord = createContainerRecord(submissionDate, containerId);

        containerRecordRepository.save(containerRecord);

        ContainerRecord containerRecordFromDB = containerRecordRepository.findByContainerId(containerRecord.getContainerId());
        assertThat(containerRecordFromDB.getContainerId(), is(containerRecord.getContainerId()));
    }

    @Test
    public void shouldPageThroughContainerRecords() {
        ContainerRecord container1 = createContainerRecord(new LocalDate(2012, 9, 19).toDate(), "containerId1");
        ContainerRecord container2 = createContainerRecord(new LocalDate(2012, 9, 21).toDate(), "containerId2");
        ContainerRecord container3 = createContainerRecord(new LocalDate(2012, 9, 22).toDate(), "containerId3");
        ContainerRecord container4 = createContainerRecord(new LocalDate(2012, 9, 24).toDate(), "containerId4");
        ContainerRecord container5 = createContainerRecord(new LocalDate(2012, 9, 27).toDate(), "containerId5");

        containerRecordRepository.save(container2);
        containerRecordRepository.save(container3);
        containerRecordRepository.save(container5);
        containerRecordRepository.save(container4);
        containerRecordRepository.save(container1);

        int pageSize = 3;

        //page1
        Page<ContainerRecord> page1 = containerRecordRepository.findAll(new ContainerRecordPageRequest(1, pageSize));
        assertEquals(pageSize, page1.getSize());
        Iterator<ContainerRecord> iterator = page1.iterator();

        assertEquals(container1.getId(), iterator.next().getId());
        assertEquals(container2.getId(), iterator.next().getId());
        assertEquals(container3.getId(), iterator.next().getId());
        assertEquals(2, page1.getTotalPages());
        assertEquals(3, page1.getNumberOfElements());

        //page 2
        Page<ContainerRecord> page2 = containerRecordRepository.findAll(new ContainerRecordPageRequest(2, pageSize));
        iterator = page2.iterator();

        assertEquals(container4.getId(), iterator.next().getId());
        assertEquals(container5.getId(), iterator.next().getId());
        assertEquals(2, page2.getNumberOfElements());
    }

    private ContainerRecord createContainerRecord(Date submissionDate, String containerId) {
        return new ContainerRecordBuilder()
                .withContainerId(containerId)
                .withIssuedOnDate(submissionDate)
                .withProviderId("providerId")
                .withSubmittedBy("CmfAdmin")
                .withSubmitterId("admin")
                .withProviderDistrict("Patna")
                .withRegistrationInstance("Instance")
                .withChannel("IVR")
                .withPatientId("patient1")
                .withStatus("Close")
                .withReasonForClosureCode("0")
                .withAlternateDiagnosisCode("1027")
                .withUserGivenPatientDetails("patientId", "patientName", 99, "Female")
                .build();
    }

    private void assertUserGivenPatientDetails(ContainerRecord expected, ContainerRecord record) {
        UserGivenPatientDetails actualPatientDetails = record.getUserGivenPatientDetails();
        UserGivenPatientDetails expectedPatientDetails = expected.getUserGivenPatientDetails();

        assertEquals(expectedPatientDetails.getPatientName(), actualPatientDetails.getPatientName());
        assertEquals(expectedPatientDetails.getPatientId(), actualPatientDetails.getPatientId());
        assertEquals(expectedPatientDetails.getPatientAge(), actualPatientDetails.getPatientAge());
        assertEquals(expectedPatientDetails.getGender(), actualPatientDetails.getGender());
    }


    @After
    public void tearDown() {
        containerRecordRepository.deleteAll();
    }
}