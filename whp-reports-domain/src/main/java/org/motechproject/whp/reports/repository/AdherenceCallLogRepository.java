package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(path = "_adherenceCallLogs")
public interface AdherenceCallLogRepository extends MotechJpaRepository<AdherenceCallLog> {

}
