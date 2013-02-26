package org.motechproject.whp.reports.model;

import lombok.Data;

@Data
public class AdherenceStatus {
    private String providerId;
    private boolean adherenceGiven;

    public AdherenceStatus() {
    }

    public AdherenceStatus(String providerId, boolean adherenceGiven) {
        this.providerId = providerId;
        this.adherenceGiven = adherenceGiven;
    }
}
