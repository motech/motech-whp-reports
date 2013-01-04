package org.motechproject.whp.reports.date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.motechproject.model.DayOfWeek;

import java.sql.Timestamp;

public class WHPDateTime {

    public static final String DATE_TIME_FORMAT = "dd/MM/YYYY HH:mm:ss";

    private DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_TIME_FORMAT);

    private DateTime date;

    public WHPDateTime() {
    }

    public WHPDateTime(DateTime date) {
        this.date = date;
    }

    public WHPDateTime(String date) {
        if (StringUtils.isNotBlank(date))
            this.date = formatter.parseDateTime(date);
    }

    public static WHPDateTime datetime(String date) {
        return new WHPDateTime(date);
    }

    public static WHPDateTime date(DateTime date) {
        return new WHPDateTime(date);
    }

    public String value() {
        if (null == date) {
            return "";
        }
        return date.toString(DATE_TIME_FORMAT);
    }

    public DateTime date() {
        return date;
    }

    public Timestamp time() {
        return new Timestamp(date.toDate().getTime());
    }

    public static DayOfWeek dayOfWeek(DateTime dateTime) {
        return DayOfWeek.getDayOfWeek(dateTime.toLocalDate());
    }
}
