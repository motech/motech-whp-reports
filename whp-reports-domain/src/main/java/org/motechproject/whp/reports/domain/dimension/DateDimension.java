package org.motechproject.whp.reports.domain.dimension;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "date_dimension", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class DateDimension {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "dt_year")
    private Integer year;

    @Column(name = "dt_month")
    private Integer month;

    @Column(name = "dt_week")
    private Integer week;

    @Column(name = "dt_day")
    private Integer day;

    public DateDimension() {
    }

    public DateDimension(Date date) {
        DateTime dateTime = new DateTime(date);
        year = dateTime.getYear();
        month = dateTime.getMonthOfYear();
        week = dateTime.getWeekOfWeekyear();
        day = dateTime.getDayOfMonth();
    }
}
