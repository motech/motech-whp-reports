package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherenceRecordRepository extends JpaRepository<AdherenceRecord, AdherenceRecordId> {

}
