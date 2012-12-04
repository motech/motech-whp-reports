package org.motechproject.whp.reports.webservice.service;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.whp.reports.builder.ContainerRecordBuilder;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.motechproject.whp.reports.webservice.model.ContainerSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/applicationContext.xml")
public class ContainerSummaryReportServiceIT {
    @Autowired
    ContainerSummaryReportService containerSummaryReportService;

    @Autowired
    ContainerRecordRepository containerRecordRepository;

    @Test
    public void shouldReturnContainerSummaryReportWithAlternateDiagnosisName() {
        String alternateDiagnosisForOtherBacterialDiseases = "1027";
        ContainerRecord container1 = createContainerRecord("containerId1", alternateDiagnosisForOtherBacterialDiseases);

        containerRecordRepository.save(container1);

        List<ContainerSummary> containerSummaries = containerSummaryReportService.containerSummary(1);

        assertThat(containerSummaries.size(), is(1));
        assertThat(containerSummaries.get(0).getAlternateDiagnosisName(), is("Other bacterial diseases,not elsewhere classified"));
    }

    @After
    public void tearDown()  {
        containerRecordRepository.deleteAll();
    }

    private ContainerRecord createContainerRecord(String containerId, String alternateDiagnosisCode) {
        return new ContainerRecordBuilder()
                .withDefaults()
                .withContainerId(containerId)
                .withAlternateDiagnosisCode(alternateDiagnosisCode)
                .build();
    }
}
