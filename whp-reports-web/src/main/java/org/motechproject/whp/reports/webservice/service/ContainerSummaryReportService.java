package org.motechproject.whp.reports.webservice.service;


import org.motechproject.export.annotation.DataProvider;
import org.motechproject.export.annotation.ExcelDataSource;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.service.ContainerRecordService;
import org.motechproject.whp.reports.webservice.mapper.ContainerSummaryMapper;
import org.motechproject.whp.reports.webservice.model.ContainerSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ExcelDataSource(name = "container")
public class ContainerSummaryReportService {

    public static final int PAGE_SIZE = 10000;
    private final ContainerRecordService containerRecordService;
    private final ContainerSummaryMapper containerSummaryMapper;

    @Autowired
    public ContainerSummaryReportService(ContainerRecordService containerRecordService, ContainerSummaryMapper containerSummaryMapper) {
        this.containerRecordService = containerRecordService;
        this.containerSummaryMapper = containerSummaryMapper;
    }

    @DataProvider
    public List<ContainerSummary> containerSummary(int pageNumber) {
       List<ContainerRecord> containerRecords= containerRecordService.getAll(pageNumber, PAGE_SIZE);
       return  containerSummaryMapper.map(containerRecords);
    }
}
