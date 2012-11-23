package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

public interface ContainerRecordRepository extends MotechJpaRepository<ContainerRecord>{
    ContainerRecord findByContainerId(String containerId);
}
