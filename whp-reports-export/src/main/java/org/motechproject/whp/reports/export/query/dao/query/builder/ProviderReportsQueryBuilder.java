package org.motechproject.whp.reports.export.query.dao.query.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.motechproject.whp.reports.export.query.dao.ProviderReportType;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;

public class ProviderReportsQueryBuilder {

	//TODO Update query
	public static final String PROVIDER_CALLLOG_SELECT_SQL = "SELECT call_log.call_id, call_log.provider_id, call_log.start_date_time,"
			+ " call_log.end_date_time, call_log.attempt_date_time, call_log.attempt,"
			+ " call_log.disconnection_type, call_log.reminder_type, call_log.call_answered, call_log.mobile_number,"
			+ " call_log.adherence_reported, provider.district FROM whp_reports.provider_reminder_call_log call_log"
			+ " inner join whp_reports.provider on provider.provider_id = call_log.provider_id";
	public static final String PROVIDER_SUMMARY_SORT_SQL = " order by treatment_start_date ";
	public static final String LIMIT_ROWS = " limit 65000";
	public static final String WHERE_CLAUSE = " where";
	private static final String DEFAULT_TEST_DISTRICT_FILTER = " provider.district != 'TestDistrict'";
	public static final String PROVIDER_CALL_LOG_SUMMARY_SORT_SQL = " order by call_log.attempt_date_time";

	private ProviderReportRequest providerReportRequest;

	public ProviderReportsQueryBuilder(ProviderReportRequest providerReportRequest) {
		this.providerReportRequest = providerReportRequest;
	}

	public String build() {
		if (providerReportRequest.getReportType() != null
				&& providerReportRequest.getReportType().equals(
						ProviderReportType.REMINDER_CALL_LOG))
			return PROVIDER_CALLLOG_SELECT_SQL + buildPredicateForCallLogs()
					+ PROVIDER_CALL_LOG_SUMMARY_SORT_SQL;
		else
			return "";
	}

	private String buildPredicateForCallLogs() {
		List<String> predicates = new ArrayList<>();

		predicates.add(DEFAULT_TEST_DISTRICT_FILTER);

		if (StringUtils.isNotEmpty(providerReportRequest.getDistrict())) {
			predicates.add(String.format(" provider.district = '%s'",
					providerReportRequest.getDistrict()));
		}
		PredicateBuilder predicateBuilder = new PredicateBuilderFactory()
				.getBuilder(providerReportRequest);
		if (predicateBuilder != null)
			predicates.addAll(predicateBuilder.getPredicates());
		String clause = StringUtils.join(predicates, " AND");
		if (clause.length() > 1) {
			return WHERE_CLAUSE + clause;
		} else
			return "";
	}
}
