package org.motechproject.whp.reports.webservice.service;


import org.motechproject.export.annotation.DataProvider;
import org.motechproject.export.annotation.ExcelDataSource;
import org.motechproject.export.annotation.Header;
import org.motechproject.whp.reports.date.WHPDate;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.motechproject.whp.reports.service.ProviderReminderCallLogService;
import org.motechproject.whp.reports.webservice.mapper.ProviderReminderCallLogSummaryMapper;
import org.motechproject.whp.reports.webservice.model.ProviderReminderCallLogSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.asList;
import static org.motechproject.util.DateUtil.today;

@Service
@Transactional(readOnly = true)
@ExcelDataSource(name = "providerReminderCallLogs")
public class ProviderReminderCallLogReportService {

    public static final int PAGE_SIZE = 10000;
    private ProviderReminderCallLogService providerReminderCallLogService;
    private ProviderReminderCallLogSummaryMapper providerReminderCallLogSummaryMapper;

    ProviderReminderCallLogReportService() {
        //for spring proxy
    }

    @Autowired
    public ProviderReminderCallLogReportService(ProviderReminderCallLogService providerReminderCallLogService, ProviderReminderCallLogSummaryMapper providerReminderCallLogSummaryMapper) {
        this.providerReminderCallLogService = providerReminderCallLogService;
        this.providerReminderCallLogSummaryMapper = providerReminderCallLogSummaryMapper;
    }

    @Header(span = 3)
    public List<String> summaryHeader() {
        return asList("Generated as on " + new WHPDate(today()).value());
    }

    @DataProvider
    public List<ProviderReminderCallLogSummary> summary(int pageNumber) {
       List<ProviderReminderCallLog> callLogs = providerReminderCallLogService.getAll(pageNumber, PAGE_SIZE);
       return  providerReminderCallLogSummaryMapper.map(callLogs);
    }
}
