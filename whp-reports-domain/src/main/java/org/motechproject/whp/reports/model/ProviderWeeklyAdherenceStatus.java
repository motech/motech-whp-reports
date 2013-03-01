package org.motechproject.whp.reports.model;

import lombok.Data;
import org.joda.time.LocalDate;

import java.util.List;

@Data
public class ProviderWeeklyAdherenceStatus {
    private List<AdherenceStatus> adherenceStatuses;
    private LocalDate from;
    private LocalDate to;

    public ProviderWeeklyAdherenceStatus(List<AdherenceStatus> adherenceStatuses, LocalDate from, LocalDate to) {
        this.adherenceStatuses = adherenceStatuses;
        this.from = from;
        this.to = to;
    }
}
