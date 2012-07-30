package org.motechproject.whp.reports.domain.dimension;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

// Dimension to represent day units (day of the week, week of the month, month of the year)
@Entity
@Data
@Table(name = "date_time", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class DateTimeDimension implements Comparable<DateTimeDimension> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "dt_year")
    private Integer year;

    @Column(name = "dt_month")
    private Integer month;

    @Column(name = "dt_week")
    private Integer week;

    @Column(name = "dt_day")
    private Integer day;

    @Column(name = "dt_hour")
    Integer hour;

    @Column(name = "dt_minute")
    Integer minute;

    @Column(name = "dt_second")
    Integer second;

    public DateTimeDimension() {
    }

    public DateTimeDimension(Date date) {
        DateTime dateTime = new DateTime(date);
        year = dateTime.getYear();
        month = dateTime.getMonthOfYear();
        week = dateTime.getWeekOfWeekyear();
        day = dateTime.getDayOfMonth();
        hour = dateTime.getHourOfDay();
        minute = dateTime.getMinuteOfHour();
        second = dateTime.getSecondOfMinute();
    }

    @Override
    public int compareTo(DateTimeDimension target) {
        DateTime self = toDateTime(this);
        DateTime other = toDateTime(target);
        return self.compareTo(other);
    }

    public long differenceInSeconds(DateTimeDimension target) {
        long seconds = (toDateTime(this).getMillis() - toDateTime(target).getMillis()) / 1000;
        return seconds < 0 ? seconds * -1 : seconds;
    }

    private DateTime toDateTime(DateTimeDimension target) {
        return new DateTime(target.year, target.month, target.day, target.hour, target.minute, target.second);
    }

    public DateTime toDateTime() {
        return toDateTime(this);
    }

}
