package org.motechproject.whp.reports.webservice.util;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Matcher;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.sql.Timestamp;

public class WHPDate {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/YYYY HH:mm:ss";

    private DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);

    private LocalDate date;

    public WHPDate() {
    }

    public WHPDate(LocalDate date) {
        this.date = date;
    }

    public WHPDate(String date) {
        if (StringUtils.isNotBlank(date))
            this.date = formatter.parseLocalDate(date);
    }

    public static WHPDate date(String date) {
        return new WHPDate(date);
    }

    public static WHPDate date(LocalDate date) {
        return new WHPDate(date);
    }

    public String value() {
        if (null == date) {
            return "";
        }
        return date.toString(DATE_FORMAT);
    }

    public LocalDate date() {
        return date;
    }

    public static WHPDate date(Date date) {
        return new WHPDate(new LocalDate(date.getTime()));
    }

    public static WHPDate date(Timestamp timestamp) {
        return new WHPDate(new LocalDate(timestamp.getTime()));
    }
}
