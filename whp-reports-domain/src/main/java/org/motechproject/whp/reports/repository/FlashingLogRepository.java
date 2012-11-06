package org.motechproject.whp.reports.repository;


import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(path = "_flashingLogs")
public interface FlashingLogRepository  extends MotechJpaRepository<FlashingLog> {
}
