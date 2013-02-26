package org.motechproject.whp.reports.export.query.dao;

import lombok.Getter;
import org.joda.time.DateTime;
import org.motechproject.whp.reports.date.WHPDateTime;

public class AdherenceDataReportQueryBuilder {

    public static final String ADHERENCE_RECORD_SQL = "select patient_id, tb_id, pill_date as adherence_date, " +
            "pill_status as adherence_value, district" +
            " from whp_reports.adherence_record ";
    public static final String WHERE = " where ";
    public static final String ORDER_BY = " order by pill_date DESC ";
    public static final String LIMIT = " limit 65000 ";

    @Getter
    public String LIMIT_TO_THREE_MONTHS = " pill_date >='" + WHPDateTime.toSqlDate(DateTime.now().minusMonths(3))+"' ";

    public String build() {
        return ADHERENCE_RECORD_SQL + WHERE + LIMIT_TO_THREE_MONTHS + ORDER_BY + LIMIT;
    }
}
