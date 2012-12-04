package org.motechproject.whp.reports.webservice.service;


import org.motechproject.export.annotation.DataProvider;
import org.motechproject.export.annotation.ExcelDataSource;
import org.motechproject.export.annotation.Header;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.service.ContainerRecordService;
import org.motechproject.whp.reports.webservice.mapper.ContainerSummaryMapper;
import org.motechproject.whp.reports.webservice.model.ContainerSummary;
import org.motechproject.whp.reports.webservice.util.WHPDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;
import static org.motechproject.util.DateUtil.today;

@Service
@Transactional(readOnly = true)
@ExcelDataSource(name = "containerTrackingReport")
public class ContainerSummaryReportService {

    public static final int PAGE_SIZE = 10000;
    private ContainerRecordService containerRecordService;
    private ContainerSummaryMapper containerSummaryMapper;

    ContainerSummaryReportService() {
        //for spring proxy
    }

    @Autowired
    public ContainerSummaryReportService(ContainerRecordService containerRecordService, ContainerSummaryMapper containerSummaryMapper) {
        this.containerRecordService = containerRecordService;
        this.containerSummaryMapper = containerSummaryMapper;
    }

    @Header(span = 3)
    public List<String> containerSummaryHeader() {
        return asList("Generated as on " + new WHPDate(today()).value());
    }

    @DataProvider
    public List<ContainerSummary> containerSummary(int pageNumber) {
       List<ContainerRecord> containerRecords= containerRecordService.getAll(pageNumber, PAGE_SIZE);
       return  containerSummaryMapper.map(containerRecords);
    }
}
