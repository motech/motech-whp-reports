package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;

@Data
public class ProviderAdherenceSummary {

    public ProviderAdherenceSummary() {
    }

    public ProviderAdherenceSummary(String providerId, String primaryMobile, String secondaryMobile, String tertiaryMobile, boolean adherenceGiven, Integer adherenceMissingWeeks) {
        this.providerId = providerId;
        this.primaryMobile = primaryMobile;
        this.secondaryMobile = secondaryMobile;
        this.tertiaryMobile = tertiaryMobile;
        this.adherenceGiven = adherenceGiven;
        this.adherenceMissingWeeks = adherenceMissingWeeks;
    }

    private String providerId;
    private String primaryMobile;
    private String secondaryMobile;
    private String tertiaryMobile;
    private boolean adherenceGiven;
    private Integer adherenceMissingWeeks;
}
