package org.motechproject.whp.reports.export.query.dao;

import lombok.Getter;
import org.joda.time.DateTime;
import org.motechproject.whp.reports.date.WHPDateTime;

public class AdherenceAuditLogReportQueryBuilder {


    public static final String ADHERENCE_AUDIT_LOG_SQL = "select patient_id, a.provider_id, tb_id, creation_time," +
            " dose_date, user_id, doses_taken as number_of_doses_taken, pill_status, " +
            "channel as Source_of_Change, is_given_by_provider, district" +
            " from whp_reports.adherence_audit_log a" +
            " join whp_reports.provider provider on provider.provider_id=a.provider_id " ;

    @Getter
    public String LIMIT_TO_THREE_MONTHS = "creation_time >='" + WHPDateTime.toSqlTimestamp(DateTime.now().minusMonths(3))+"'";

    public static final String WHERE_CLAUSE = "where ";

    public static final String ORDER_BY = "order by creation_time DESC ";
    public static final String LIMIT = "LIMIT 65000";


    public String build() {
        return ADHERENCE_AUDIT_LOG_SQL + WHERE_CLAUSE + LIMIT_TO_THREE_MONTHS + ORDER_BY + LIMIT;
    }
}
