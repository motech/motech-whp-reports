package org.motechproject.whp.reports.export.query.model;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.motechproject.util.DateUtil;

public class DateRange {

    public static final String SQL_DATE_FORMAT = "yyyy-MM-dd";

    LocalDate from;
    LocalDate to;

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);

    public DateRange(String strFrom, String strTo) {

        if (strFrom != null && strTo != null) {
            this.from = parseDate(strFrom);
            this.to = parseDate(strTo);
            return;
        }

        if (strFrom != null) {
            this.from = parseDate(strFrom);
            this.to = this.from.plusDays(180);
            return;
        }

        if (strTo != null) {
            this.to = parseDate(strTo);
            this.from = this.to.minusDays(180);
            return;
        }

        this.to = DateUtil.today();
        this.from = this.to.minusDays(180);
    }

    private LocalDate parseDate(String strDate) {
        return formatter.parseLocalDate(strDate);
    }

    public String getStartDate() {
        return from.toString(SQL_DATE_FORMAT);
    }

    public String getEndDate() {
        return to.toString(SQL_DATE_FORMAT);
    }
}
