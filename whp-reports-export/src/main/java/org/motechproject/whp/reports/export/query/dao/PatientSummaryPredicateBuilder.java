package org.motechproject.whp.reports.export.query.dao;

import lombok.Data;
import org.motechproject.whp.reports.export.query.model.DateRange;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

import java.util.List;

import static java.util.Arrays.asList;

@Data
public class PatientSummaryPredicateBuilder implements PredicateBuilder {

    private PatientReportRequest patientReportRequest;

    public PatientSummaryPredicateBuilder(PatientReportRequest patientReportRequest) {
        this.patientReportRequest = patientReportRequest;
    }

    public List<String> getPredicates() {
        DateRange dateRange = new DateRange(patientReportRequest.getFrom(), patientReportRequest.getTo());
        return asList(String.format(" treatment.start_date between '%s' AND '%s'",
                dateRange.getStartDateInSqlFormat(),
                dateRange.getEndDateInSqlFormat()));
    }
}
