package org.motechproject.whp.reports.export.query.dao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdherenceDataReportQueryBuilderTest {

    @Test
    public void shouldBuildAdherenceDataReportQuery() {
        AdherenceDataReportQueryBuilder adherenceDataReportQueryBuilder = new AdherenceDataReportQueryBuilder();
        assertThat(adherenceDataReportQueryBuilder.build(),
                is(AdherenceDataReportQueryBuilder.ADHERENCE_RECORD_SQL+
                        AdherenceDataReportQueryBuilder.WHERE +
                        adherenceDataReportQueryBuilder.getLIMIT_TO_THREE_MONTHS() +
                        AdherenceDataReportQueryBuilder.ORDER_BY +
                        AdherenceDataReportQueryBuilder.LIMIT));
    }

}
