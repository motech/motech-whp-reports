package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;

@Data
public class ProviderAdherenceSummary {

    public ProviderAdherenceSummary() {
    }

    public ProviderAdherenceSummary(String providerId, String primaryMobile, String secondaryMobile, String tertiaryMobile, boolean adherenceGiven, int adherenceMissingWeeks) {
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
    private int adherenceMissingWeeks;

    public void incrementAdherenceMissingWeeks() {
        adherenceMissingWeeks ++;
    }
}
