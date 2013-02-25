package org.motechproject.whp.reports.date;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.motechproject.util.DateUtil;

import java.util.Date;

public class WHPDateTest {

    @Test
    public void shouldConvertToSQLDate() {
        LocalDate today = DateUtil.today();
        long currentTimeInMillis = today.toDate().getTime();
        Assert.assertEquals(new Date(currentTimeInMillis), WHPDate.toSqlDate(today));
    }

}
