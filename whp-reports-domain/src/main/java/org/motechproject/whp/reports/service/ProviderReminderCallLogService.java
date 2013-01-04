package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.contract.AdherenceSubmissionRequest;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.motechproject.whp.reports.domain.paging.MostRecentProviderReminderCallLog;
import org.motechproject.whp.reports.domain.paging.ProviderReminderCallLogPageRequest;
import org.motechproject.whp.reports.repository.ProviderReminderCallLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ProviderReminderCallLogService {

    private ProviderReminderCallLogRepository providerReminderCallLogRepository;

    ProviderReminderCallLogService() {
        //for spring proxy
    }

    @Autowired
    ProviderReminderCallLogService(ProviderReminderCallLogRepository providerReminderCallLogRepository) {
        this.providerReminderCallLogRepository = providerReminderCallLogRepository;
    }

    public void save(ProviderReminderCallLog callLog) {
        providerReminderCallLogRepository.save(callLog);
    }

    public ProviderReminderCallLog getMostRecentLogForProvider(String providerId, Timestamp asOf) {
        List<ProviderReminderCallLog> providerReminderCallLogs = providerReminderCallLogRepository.findByProviderIdAndAttemptTimeLessThan(providerId, asOf, new MostRecentProviderReminderCallLog());
        return providerReminderCallLogs.isEmpty() ? null : providerReminderCallLogs.get(0);
    }

    public void updateAdherenceStatus(AdherenceSubmissionRequest request) {
        if (!request.isWithinAdherenceWindow())
            return;

        ProviderReminderCallLog mostRecentLogForProvider = getMostRecentLogForProvider(request.getProviderId(), new Timestamp(request.getSubmissionDate().getTime()));
        if(mostRecentLogForProvider == null)
            return;

        mostRecentLogForProvider.setAdherenceReported(true);
        providerReminderCallLogRepository.save(mostRecentLogForProvider);
    }

    public List<ProviderReminderCallLog> getAll(int pageNumber, int pageSize) {
        ProviderReminderCallLogPageRequest pageRequest = new ProviderReminderCallLogPageRequest(pageNumber, pageSize);
        return providerReminderCallLogRepository.findAll(pageRequest).getContent();
    }
}
