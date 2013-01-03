package org.motechproject.whp.reports.repository;


import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface ProviderReminderCallLogRepository extends MotechJpaRepository<ProviderReminderCallLog>{
    List<ProviderReminderCallLog> findByProviderIdAndAttemptTimeLessThan(String providerId, Timestamp timestamp, Pageable pageable);
}
