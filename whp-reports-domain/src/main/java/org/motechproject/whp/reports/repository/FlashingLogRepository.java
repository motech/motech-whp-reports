package org.motechproject.whp.reports.repository;


import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashingLogRepository  extends JpaRepository<FlashingLog, Long> {
}
