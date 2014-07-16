package org.motechproject.whp.reports.export.query.model;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateRange {

    public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

    LocalDate from;
    LocalDate to;

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);

    public DateRange(String strFrom, String strTo) {
        boolean validFromDate = StringUtils.isNotEmpty(strFrom);
        boolean validToDate = StringUtils.isNotEmpty(strTo);
        if (validFromDate && validToDate) {
            this.from = parseDate(strFrom);
            this.to = parseDate(strTo).plusDays(1);
            return;
        }

        if (validFromDate) {
            this.from = parseDate(strFrom);
            this.to = this.from.plusDays(181);
            return;
        }

        if (validToDate) {
            this.to = parseDate(strTo).plusDays(1);
            this.from = this.to.minusDays(180);
            return;
        }
    }


    private LocalDate parseDate(String strDate) {
        return formatter.parseLocalDate(strDate);
    }

    public String getStartDateInSqlFormat() {
        return from.toString(SQL_DATE_FORMAT);
    }

    public String getEndDateInSqlFormat() {
        return to.toString(SQL_DATE_FORMAT);
    }

    public String getStartDate() {
        return from.toString(DEFAULT_DATE_FORMAT);
    }

    public String getEndDate() {
        return to.toString(DEFAULT_DATE_FORMAT);
    }

    public boolean hasValidRange() {
        return from != null && to != null;
    }
}
