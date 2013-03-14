package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;

public interface ContainerRecordRepository extends MotechJpaRepository<ContainerRecord>{
    ContainerRecord findByContainerId(String containerId);
}
