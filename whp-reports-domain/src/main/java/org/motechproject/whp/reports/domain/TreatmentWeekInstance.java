package org.motechproject.whp.reports.domain;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import static org.motechproject.util.DateUtil.today;

@Component
public class TreatmentWeekInstance {


    public static TreatmentWeek currentAdherenceCaptureWeek() {
        return week(today());
    }

    private static TreatmentWeek previousAdherenceCaptureWeek() {
        return week(today().minusWeeks(1));
    }

    public static TreatmentWeek week(LocalDate dateInWeek){
        return new TreatmentWeek(dateInWeek.minusDays(6));
    }
}
