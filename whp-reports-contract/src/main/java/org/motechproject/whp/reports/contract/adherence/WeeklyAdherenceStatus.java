package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;
import org.joda.time.LocalDate;

@Data
public class WeeklyAdherenceStatus {
    private LocalDate from;
    private LocalDate to;
    private boolean adherenceGiven;

    public WeeklyAdherenceStatus(LocalDate from, LocalDate to, boolean adherenceGiven) {
        this.from = from;
        this.to = to;
        this.adherenceGiven = adherenceGiven;
    }
}
