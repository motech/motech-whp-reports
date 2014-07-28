package org.motechproject.whp.reports.export.query.builder;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.motechproject.whp.reports.export.query.model.DateRange;
import org.motechproject.whp.reports.export.query.model.ProviderReminderCallLogSummary;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderReportBuilder {

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME = "/xls/templates/providerReminderCallLogReport.xls";
	public static final String TEMPLATE_RESULT_KEY = "patients";
	public static final String TEMPLATE_CALL_LOGS = "callLogs";

	public static final String FROM_DATE = "fromDate";
	public static final String TO_DATE = "toDate";
	public static final String PROVIDER_DISTRICT = "providerDistrict";
	public static final String TOTAL_ROWS = "totalRows";

	private final ReportQueryService reportQueryService;
	private WhpExcelReportBuilder whpExcelReportBuilder;

	@Autowired
	public ProviderReportBuilder(ReportQueryService reportQueryService,
			WhpExcelReportBuilder whpExcelReportBuilder) {
		this.reportQueryService = reportQueryService;
		this.whpExcelReportBuilder = whpExcelReportBuilder;
	}

	public void buildProviderReminderCallLogReport(
			ProviderReportRequest providerReportRequest,
			OutputStream outputStream) {
		whpExcelReportBuilder.build(outputStream,
				getCallLogReportData(providerReportRequest),
				PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME);
	}

	private Map<String, Object> getCallLogReportData(
			ProviderReportRequest providerReportRequest) {
		List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries = reportQueryService
				.getProviderReminderCallLogSummaries(providerReportRequest);
		return setCallLogReportParameters(providerReportRequest,
				providerReminderCallLogSummaries);
	}

	private Map<String, Object> setCallLogReportParameters(
			ProviderReportRequest providerReportRequest,
			List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries) {
		Map<String, Object> params = new HashMap<>();

		DateRange dateRange = new DateRange(providerReportRequest.getFrom(),
				providerReportRequest.getTo(), true);
		if (dateRange.hasValidRange()) {
			params.put(FROM_DATE, dateRange.getStartDate());
			params.put(TO_DATE, dateRange.getEndDate());
		}

		params.put(TEMPLATE_CALL_LOGS, providerReminderCallLogSummaries);
		params.put(PROVIDER_DISTRICT, providerReportRequest.getDistrict());
		params.put(TOTAL_ROWS, providerReminderCallLogSummaries.size());
		return params;
	}
}
