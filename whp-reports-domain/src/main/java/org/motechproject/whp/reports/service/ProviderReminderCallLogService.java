package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.motechproject.whp.reports.repository.ProviderReminderCallLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProviderReminderCallLogService extends BaseService<ProviderReminderCallLogRepository, ProviderReminderCallLog> {


    ProviderReminderCallLogService(){
    }

    @Autowired
    public ProviderReminderCallLogService(ProviderReminderCallLogRepository providerReminderCallLogRepository) {
        super(providerReminderCallLogRepository);
    }
}
