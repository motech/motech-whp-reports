package org.motechproject.whp.reports.contract.adherence;

import lombok.Data;

import java.util.List;

@Data
public class ProviderAdherenceSummaries {
    private String district;
    private List<ProviderAdherenceSummary> adherenceGivenList;
    private List<ProviderAdherenceSummary> adherencePendingList;
}
