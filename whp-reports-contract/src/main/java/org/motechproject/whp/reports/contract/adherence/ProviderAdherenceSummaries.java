package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;

import java.util.List;

@Data
public class ProviderAdherenceSummaries {
    private String district;
    private List<ProviderAdherenceSummary> adherenceSummaryList;

    public ProviderAdherenceSummaries() {
    }

    public ProviderAdherenceSummaries(String district, List<ProviderAdherenceSummary> adherenceSummaryList) {
        this.district = district;
        this.adherenceSummaryList = adherenceSummaryList;
    }
}
