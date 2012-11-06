package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRecordRepository extends JpaRepository<ContainerRecord, Long>{
    ContainerRecord findByContainerId(String containerId);
}