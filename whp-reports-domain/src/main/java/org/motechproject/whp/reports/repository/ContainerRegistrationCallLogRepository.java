package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRegistrationCallLogRepository  extends JpaRepository<ContainerRegistrationCallLog, Long> {

}
