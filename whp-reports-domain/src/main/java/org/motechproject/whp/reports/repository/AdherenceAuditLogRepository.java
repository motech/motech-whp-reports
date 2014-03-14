package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;

import java.util.List;

public interface AdherenceAuditLogRepository extends MotechJpaRepository<AdherenceAuditLog>{

   public List<AdherenceAuditLog> findByPatientIdAndTbId(String patientId, String tbId);
}
