package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.motechproject.whp.reports.domain.dimension.DateDimension;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class DateDimensionBuilder {

    private Integer year;
    private Integer month;
    private Integer week;
    private Integer day;

    public DateDimensionBuilder() {
        year = 2012;
        month = 10;
        day = 10;
        week = new DateTime(year, month, day, 0, 0, 0, 0).getWeekyear();
    }

    public static DateDimensionBuilder newDateTimeDimension() {
        return new DateDimensionBuilder();
    }

    public DateDimensionBuilder withDay(Integer day) {
        this.day = day;
        return this;
    }

    public DateDimension build() {
        DateDimension dimension = buildDimension();
        return dimension;
    }

    public DateDimension create(final HibernateTemplate template, PlatformTransactionManager transactionManager) {
        final DateDimension dimension = buildDimension();
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallback<DateDimension>() {
            @Override
            public DateDimension doInTransaction(TransactionStatus transactionStatus) {
                template.save(dimension);
                return dimension;
            }
        });

        return dimension;
    }

    private DateDimension buildDimension() {
        DateDimension dimension = new DateDimension();
        dimension.setYear(year);
        dimension.setMonth(month);
        dimension.setWeek(week);
        dimension.setDay(day);
        return dimension;
    }
}
