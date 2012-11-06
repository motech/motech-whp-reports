package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

@RestResource(path = "_containerRegistrationCallLogs")
public interface ContainerRegistrationCallLogRepository  extends MotechJpaRepository<ContainerRegistrationCallLog> {

}
