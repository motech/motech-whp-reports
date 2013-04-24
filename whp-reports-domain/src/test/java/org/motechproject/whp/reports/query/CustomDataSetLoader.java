package org.motechproject.whp.reports.query;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.joda.time.LocalDate;
import org.motechproject.whp.reports.date.WHPDate;
import org.springframework.core.io.Resource;

import java.sql.Date;

public class CustomDataSetLoader extends AbstractDataSetLoader {
    protected IDataSet createDataSet(Resource resource) throws Exception {

        Date currentDate = WHPDate.toSqlDate(new LocalDate());
        ReplacementDataSet replacementDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(resource.getURL()));

        replacementDataSet.addReplacementObject("[CURRENT_DATE]", currentDate);
        return replacementDataSet;

    }
}