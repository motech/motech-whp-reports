package org.motechproject.whp.reports.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WhpReportQueries {

    @Value("#{reportSQLQueries['adherence.record.data.query']}")
    private String adherenceDataReportQuery;

    @Value("#{reportSQLQueries['adherence.audit.data.query']}")
    private String adherenceAuditLogReportQuery;
    
}
