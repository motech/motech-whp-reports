package org.motechproject.whp.reports.query;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.motechproject.whp.reports.date.WHPDate;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.springframework.core.io.Resource;

public class CustomDataSetLoader extends AbstractDataSetLoader {
    protected IDataSet createDataSet(Resource resource) throws Exception {

        LocalDate currentDate = new LocalDate();
        LocalDate currentTreatmentWeek = currentDate.minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY);

        ReplacementDataSet replacementDataSet = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(resource.getURL()));
        replacementDataSet.setStrictReplacement(true);
        replacementDataSet.addReplacementObject("[CURRENT_TREATMENT_WEEK]", WHPDate.toSqlDate(currentTreatmentWeek));
        replacementDataSet.addReplacementObject("[CURRENT_TREATMENT_MINUS_1]", WHPDate.toSqlDate(currentTreatmentWeek.minusWeeks(1)));
        replacementDataSet.addReplacementObject("[CURRENT_TREATMENT_MINUS_2]", WHPDate.toSqlDate(currentTreatmentWeek.minusWeeks(2)));
        replacementDataSet.addReplacementObject("[CURRENT_TREATMENT_MINUS_3]", WHPDate.toSqlDate(currentTreatmentWeek.minusWeeks(3)));
        replacementDataSet.addReplacementObject("[CURRENT_TREATMENT_MINUS_7]", WHPDate.toSqlDate(currentTreatmentWeek.minusWeeks(7)));
        replacementDataSet.addReplacementObject("[CURRENT_TREATMENT_MINUS_9]", WHPDate.toSqlDate(currentTreatmentWeek.minusWeeks(9)));

        replacementDataSet.addReplacementObject("[CURRENT_DATE_TMSTP]", WHPDateTime.toSqlTimestamp(currentDate));
        replacementDataSet.addReplacementObject("[CURRENT_TREATMENT_WEEK_TMSTP]", WHPDateTime.toSqlTimestamp(currentTreatmentWeek));
        replacementDataSet.addReplacementObject("[CURRENT_TREATMENT_MINUS_1_TMSTP]", WHPDateTime.toSqlTimestamp(currentTreatmentWeek.minusWeeks(1)));
        replacementDataSet.addReplacementObject("[NULL]", null);
        return replacementDataSet;

    }
}