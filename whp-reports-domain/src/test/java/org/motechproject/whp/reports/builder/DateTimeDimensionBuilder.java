package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.motechproject.whp.reports.domain.dimension.DateTimeDimension;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class DateTimeDimensionBuilder {

    private Integer year;
    private Integer month;
    private Integer week;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer second;

    public DateTimeDimensionBuilder() {
        year = 2012;
        month = 10;
        day = 10;
        hour = 10;
        minute = 10;
        second = 10;
        week = new DateTime(year, month, day, hour, minute, second).getWeekyear();
    }

    public static DateTimeDimensionBuilder newDateTimeDimension() {
        return new DateTimeDimensionBuilder();
    }

    public DateTimeDimensionBuilder withDay(Integer day) {
        this.day = day;
        return this;
    }

    public DateTimeDimension build() {
        DateTimeDimension dimension = buildDimension();
        return dimension;
    }

    public DateTimeDimension create(final HibernateTemplate template, PlatformTransactionManager transactionManager) {
        final DateTimeDimension dimension = buildDimension();
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallback<DateTimeDimension>() {
            @Override
            public DateTimeDimension doInTransaction(TransactionStatus transactionStatus) {
                template.save(dimension);
                return dimension;
            }
        });
        return dimension;
    }

    private DateTimeDimension buildDimension() {
        DateTimeDimension dimension = new DateTimeDimension();
        dimension.setYear(year);
        dimension.setMonth(month);
        dimension.setWeek(week);
        dimension.setDay(day);
        dimension.setHour(hour);
        dimension.setMinute(minute);
        dimension.setSecond(second);
        return dimension;
    }
}
